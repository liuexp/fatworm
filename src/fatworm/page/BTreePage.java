package fatworm.page;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import fatworm.driver.DBEngine;
import fatworm.io.BKey;
import fatworm.io.BTree;
import fatworm.io.File;
import fatworm.page.BTreePage.BCursor;
import fatworm.util.Util;

// 4096 = 4 * 5 + 4 * 510 + 4 * 509 (INT key)
// 4096 > 4 * 5 + 4 * 340 + 8 * 339 (Long key)
// FIXME Everytime anything of this page is modified, remember to set dirty = true....
public class BTreePage extends RawPage {
	private static final int ROOTNODE = 1;
	private static final int INTERNALNODE = 2;
	private static final int LEAFNODE = 3;
	private static final int ROOTLEAFNODE = 4;
	public static final int LongSize = Long.SIZE / Byte.SIZE;
	public static final int IntSize = Integer.SIZE / Byte.SIZE;
	// Page structure:
	// Page/Node type: ROOTNODE for root node, etc.
	// for string data, size, cnt, every segment is an int for the length, and then the string.
	// (deprecated)for root, #children, children list, key list.
	// (deprecated)for internal node, parent, #children, children list, key list.
	// now for EVERY (INT-key) node, parent, prev, next, #children, 510 x children list, 509 x key list.
	
	// for duplicate keys without bucketing:
	// null key to indicate you should just skip this key while searching(from left to right) as this is just a duplicate of the previous entries.
	// FIXME to allow this, I should change the index from a<x<=b to be a<=x<b
	// of course another way is to use bucket
	// wait a second, if I dont reclaim space after deletion, it would be much better if I use bucketing...
	private int nodeType;
	private Integer parentPageID;
	public ArrayList<Integer> children;
	public ArrayList<BKey> key;
	public int keyType;
	public BTree btree;
	public int fanout;
	public BTreePage(BTree btree, File f, int pageid, int keyType, boolean create) throws Throwable {
		lastTime = System.currentTimeMillis();
		dataFile = f;
		pageID = pageid;
		byte [] tmp = new byte[(int) File.recordPageSize];
		this.keyType = keyType;
		this.btree = btree;
		fanout = fanoutSize(keyType);
		if(!create){
			if(pageID<0){
				Util.warn("BTree pageID < 0!!");
			}
			dataFile.read(tmp, pageID);
			children = new ArrayList<Integer>();
			key = new ArrayList<BKey>();
			fromBytes(tmp);
		}else{
			nextPageID = -1;
			prevPageID = -1;
			parentPageID = -1;
			children = new ArrayList<Integer>();
			key = new ArrayList<BKey>();
			dirty = true;
			children.add(-1);
			nodeType=ROOTLEAFNODE;
			buf = ByteBuffer.wrap(tmp);
		}
	}

	@Override
	public void fromBytes(byte[] b) throws Throwable {
		ByteBuffer buf = ByteBuffer.wrap(b);
		nodeType = buf.getInt();
		parentPageID = buf.getInt();
		prevPageID = buf.getInt();
		nextPageID = buf.getInt();
		int cntChild = buf.getInt();
		for(int i=0;i<cntChild;i++){
			children.add(buf.getInt());
		}
		for(int i=0;i<cntChild-1;i++){
			if(keySize(keyType) == IntSize)
				key.add(btree.getBKey(buf.getInt(), keyType));
			else
				key.add(btree.getBKey(buf.getLong(), keyType));
		}
		this.buf = buf;
	}
	
	@Override
	public byte[] toBytes() throws Throwable {
		buf.position(0);
		buf.putInt(nodeType);
		buf.putInt(parentPageID);
		buf.putInt(prevPageID);
		buf.putInt(nextPageID);
		buf.putInt(children.size());
		for(int i=0;i<children.size();i++){
			buf.putInt(children.get(i));
		}
		for(int i=0;i<key.size();i++){
			buf.put(key.get(i).toByte());
		}
		return buf.array();
	}
	
	public boolean isLeaf(){
		return nodeType == LEAFNODE || nodeType == ROOTLEAFNODE;
	}
	public boolean isRoot(){
		return nodeType == ROOTNODE || nodeType == ROOTLEAFNODE;
	}
	public boolean isInternal(){
		return nodeType == INTERNALNODE;
	}
	public boolean isFull(){
		assert fanout >= children.size();
		return fanout == children.size();
	}
	public static int keySize(int type){
		switch(type){
		case java.sql.Types.BOOLEAN:
		case java.sql.Types.INTEGER:
		case java.sql.Types.FLOAT:
		case java.sql.Types.CHAR:
		case java.sql.Types.VARCHAR:
		case java.sql.Types.DECIMAL:
			return Integer.SIZE / Byte.SIZE;
		case java.sql.Types.DATE:
		case java.sql.Types.TIMESTAMP:
			return Long.SIZE / Byte.SIZE;
			default:
				Util.error("meow@BTreePage");
		}
		return 4;
	}
	public static int fanoutSize(int type){
		switch(type){
		case java.sql.Types.BOOLEAN:
		case java.sql.Types.INTEGER:
		case java.sql.Types.FLOAT:
			return 510;
		case java.sql.Types.CHAR:
		case java.sql.Types.VARCHAR:
		case java.sql.Types.DECIMAL:
			return 510;		// for now we take its key value as encoded INT, may change to 340 if necessary
		case java.sql.Types.DATE:
		case java.sql.Types.TIMESTAMP:
			return 340;
			default:
				Util.error("meow@BTreePage");
		}
		return 340;
	}
	
	@Override
	public int headerSize(){
		return 5 * Byte.SIZE;
	}
	
	public synchronized void add(Integer idx, BKey k, int val) {
		key.add(idx, k);
		assert(val>=0);
		children.add(idx+1, val);
		dirty = true;
	}
	
	public synchronized void insert(Integer idx, BKey k, int val) throws Throwable {
		dirty = true;
		if(!isFull())add(idx, k, val);
		else {
			boolean isLeaf = isLeaf();
			boolean isRoot = isRoot();
			beginTransaction();
			BTreePage newPage = btree.bm.getBTreePage(btree, btree.bm.newPage(), keyType, true);
			newPage.beginTransaction();
			newPage.nodeType = nodeType = isLeaf ? LEAFNODE : INTERNALNODE;
			newPage.parentPageID = parentPageID;
			newPage.nextPageID = nextPageID;
			newPage.prevPageID = pageID;
			if(hasNext()){
				BTreePage p = next();
				p.prevPageID = newPage.getID();
				p.dirty = true;
				p = null;
			}
			nextPageID = newPage.pageID;
			newPage.dirty = true;
			dirty = true;
			
//			int mid = (int) Math.ceil(key.size()/2.0 - (1e-3));
			int mid = (int) Math.ceil(fanout/2.0 - (1e-3));
			int tmpidx = indexOf(k);
			key.add(tmpidx, k);
			children.add(tmpidx+1, val);
			
			if(DBEngine.getInstance().turnOnDebug){
				for(int i=0;i<key.size()-1;i++){
					assert key.get(i).compareTo(key.get(i+1)) < 0;
				}
			}
			
			ArrayList<BKey> newlist1 = new ArrayList<BKey> ();
			ArrayList<BKey> newlist2 = new ArrayList<BKey> ();
			BKey toParent = key.get(mid);
			if(isLeaf){
				for(int i=0;i<mid;i++)
					newlist1.add(key.get(i));
				for(int i=mid;i<key.size();i++)
					newlist2.add(key.get(i));
			}else {
				for(int i=0;i<mid-1;i++)
					newlist1.add(key.get(i));
				for(int i=mid;i<key.size();i++)
					newlist2.add(key.get(i));
			}
			key = newlist1;
			newPage.key = newlist2;
			
			ArrayList<Integer> newchild1 = new ArrayList<Integer> ();
			ArrayList<Integer> newchild2 = new ArrayList<Integer> ();
			if(isLeaf){
				for(int i=0;i<mid+1;i++)
					newchild1.add(children.get(i));
				newchild2.add(-1);
				for(int i=mid+1;i<children.size();i++){
					Integer cpid = children.get(i);
					newchild2.add(cpid);
					//FIXME leaf node's child is value
//					BTreePage cp = getPage(cpid);
//					cp.beginTransaction();
//					cp.parentPageID = newPage.pageID;
//					cp.commit();
				}
			}else {
				for(int i=0;i<mid;i++)
					newchild1.add(children.get(i));
				for(int i=mid;i<children.size();i++){
					Integer cpid = children.get(i);
					newchild2.add(cpid);
					BTreePage cp = getPage(cpid);
					cp.beginTransaction();
					cp.parentPageID = newPage.pageID;
					cp.commit();
				}
			}
			children = newchild1;
			newPage.children = newchild2;
//			Util.warn("BTree splitting:"+pageID+" to "+newPage.pageID);
			if(!isRoot){
				commit();
				newPage.commit();
				int pidx = -1;
				BTreePage parent = parent();
				for(int i=0;i<parent.children.size();i++){
					if(pageID.equals(parent.children.get(i))){
						pidx = i;
						break;
					}
				}
				parent.beginTransaction();
				parent.insert(pidx, toParent, newPage.pageID);
				parent.commit();
			} else {
				BTreePage newRoot = btree.bm.getBTreePage(btree, btree.bm.newPage(), keyType, true);
				parentPageID = newRoot.pageID;
				commit();
				newPage.parentPageID = newRoot.pageID;
				newPage.commit();
				newRoot.beginTransaction();
				newRoot.nodeType = ROOTNODE;
				newRoot.key.add(toParent);
				newRoot.children = new ArrayList<Integer>();
				newRoot.children.add(pageID);
				newRoot.children.add(newPage.pageID);
				DBEngine.getInstance().announceBTreeNewRoot(btree.root.getID(), newRoot.getID());
//				Util.warn("BTree changing root!oldRoot="+btree.root.getID()+", newRoot="+newRoot.getID());
				btree.root = newRoot;
				newRoot.dirty = true;
				newRoot.commit();
			}
		}
	}

	public int indexOf(BKey k){
//		int idx = 0;
////		while(idx < key.size() && (key.get(idx) == null || k.compareTo(key.get(idx)) >= 0)) idx++;
//		while(idx < key.size()){
//			if(key.get(idx) != null && k.compareTo(key.get(idx))<=0)
//				return idx;
//			idx++;
//		}
//		return idx;
		int idx = Collections.binarySearch(key, k, new Comparator<BKey>(){
			@Override
			public int compare(BKey arg0, BKey arg1) {
				return arg0.compareTo(arg1);
			}
		});
		return idx<0?(-1)-idx:idx;
	}
	public BCursor lookup(BKey k) throws Throwable{
		int idx = indexOf(k);
		return isLeaf()? new BCursor(idx) : getPage(children.get(idx)).lookup(k);
	}

	public synchronized void remove(Integer idx) throws Throwable {
		beginTransaction();
		dirty = true;
		key.remove(idx);
		children.remove(idx);
		commit();
	}

	public boolean hasNext() {
		return nextPageID != -1;
	}
	
	public boolean hasPrev() {
		return prevPageID != -1;
	}

	public synchronized BCursor head() throws Throwable {
		if(isLeaf())
			return new BCursor(0);
		return getPage(children.get(0)).head();
	}

	public synchronized BCursor last() throws Throwable {
		if(isLeaf())
			return new BCursor(key.size());
		return getPage(children.get(key.size())).last();
	}
	
	public synchronized BTreePage getPage(Integer pid) throws Throwable {
		return btree.bm.getBTreePage(btree, pid, keyType, false);
	}

	public synchronized BTreePage next() throws Throwable {
		return getPage(nextPageID);
	}
	
	public synchronized BTreePage prev() throws Throwable {
		return getPage(prevPageID);
	}
	
	public synchronized BTreePage parent() throws Throwable {
		if(parentPageID<0)
			Util.warn("meow");
		return getPage(parentPageID);
	}

	public void delete() throws Throwable {
		if(!isLeaf()){
			for(int i=0;i<children.size();i++){
				getPage(children.get(i)).delete();
			}
		}
		for(int i=0;i<key.size();i++){
			key.get(i).delete();
		}
		btree.bm.releasePage(pageID);
	}

	// Index for key: 0<= idx < key.size()
	// idx < 0 or idx >= key.size() indicate notFound
	// to get the children/value of that key, it's associated with children[idx+1]
	public final class BCursor {
		private final Integer idx;
		
		public BCursor(int idx){
			this.idx = idx;
		}
		
		public BKey getKey(){
			assert valid();
			return key.get(idx);
		}
		
		@Override
		public BCursor clone(){
			return new BCursor(idx);
		}
		
		public int getValue() {
			assert valid();
			return children.get(idx+1);
		}
		
		public boolean hasNext(){
			return idx + 1 < key.size() || BTreePage.this.hasNext();
		}
		
		public BCursor next() throws Throwable{
			assert valid();
			if(idx + 1 < key.size())
				return new BCursor(idx+1);
			else return BTreePage.this.next().head();
		}
		
		public boolean hasPrev(){
			return idx > 0 || BTreePage.this.hasPrev();
		}
		
		public BCursor prev() throws Throwable{
//			assert valid();
			if(idx > 0)
				return new BCursor(idx-1);
			else return BTreePage.this.prev().last().prev();	// In case of empty pages...I don't reclaim space here.
		}
		
		public void insert(BKey k, int val) throws Throwable{
			assert valid() || idx == key.size();
			BTreePage.this.beginTransaction();
			BTreePage.this.insert(idx, k, val);
			BTreePage.this.commit();
		}
		
		public void remove() throws Throwable{
			assert valid();
			BTreePage.this.beginTransaction();
			BTreePage.this.remove(idx);
			BTreePage.this.commit();
		}
		
		public boolean valid(){
			return idx >=0 && idx < key.size();
		}

		public BCursor adjust() throws Throwable {
			if(valid())return this;
			if(idx.equals(key.size()) && BTreePage.this.hasNext()){
				return BTreePage.this.next().head();
			}
			return null;
		}

		public BCursor adjustLeft() {
			if(valid())return this;
			if(idx.equals(key.size()) && idx > 0){
				return new BCursor(idx-1);
			}
			return null;
		}
		
		@Override
		public boolean equals(Object o){
			if(o instanceof BCursor){
				BCursor b = (BCursor) o;
				return getPageID().equals(b.getPageID()) && idx.equals(b.idx);
			}
			return false;
		}

		private Integer getPageID() {
			return BTreePage.this.getID();
		}
	}

}
