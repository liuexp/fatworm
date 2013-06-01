package fatworm.driver;

import java.util.ArrayList;
import java.util.List;

import org.antlr.runtime.tree.CommonTree;
import org.antlr.runtime.tree.Tree;

import fatworm.absyn.Expr;
import fatworm.driver.Database.Index;
import fatworm.field.Field;
import fatworm.field.INT;
import fatworm.field.NULL;
import fatworm.io.BKey;
import fatworm.io.BTree;
import fatworm.io.BufferManager;
import fatworm.io.Cursor;
import fatworm.page.RecordPage;
import fatworm.page.BTreePage.BCursor;
import fatworm.util.Env;
import fatworm.util.Util;

public class IOTable extends Table {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7673588089635019327L;
	public static final Integer MODOOFFSET = 512;

	public IOTable() {
		firstPageID = -1;
	}
	public IOTable(CommonTree t) {
		this();
		schema = new Schema(t);
		// build index on primaryKey
		if(schema.primaryKey != null){
//			Index pindex = new Index(Util.getPKIndexName(schema.primaryKey.name), this, schema.primaryKey);
			// XXX I'm going to hack it this way
			// FIXME this table hasn't got into database's lists
			DBEngine.getInstance().getDatabase().createIndexWithTable(Util.getPKIndexName(schema.primaryKey.name), schema.primaryKey.name, true, this);
		}
	}

	public SimpleCursor open() {
		return new SimpleCursor();
	}
	@Override
	public int update(List<String> colName, List<Expr> expr, Expr e) {
		SimpleCursor c = open();
		int ret = 0;
		try {
			for(;c.hasThis();c.next()){
				Record r = c.fetchRecord();
				Env env = new Env();
				env.appendFromRecord(r);
				if(e!=null && !e.evalPred(env))continue;
				for(int i=0;i<expr.size();i++){
					Field res = expr.get(i).eval(env);
					r.cols.set(r.schema.findIndex(colName.get(i)), res);
					env.put(colName.get(i), res);
				}
				c.updateWithRecord(r);
				ret++;
			}
		} catch (Throwable e1) {
			e1.printStackTrace();
		}
		return 0;
	}

	@Override
	public void addRecord(Record r) {
		SimpleCursor c = open();
		try {
			if(!c.hasThis()){
				BufferManager bm = DBEngine.getInstance().recordManager;
				firstPageID = bm.newPage();
				RecordPage thisp = bm.getRecordPage(firstPageID, true);
				thisp.tryAppendRecord(r);
				thisp.beginTransaction();
				thisp.dirty = true;
				thisp.nextPageID = thisp.prevPageID = thisp.getID();
				thisp.commit();
				return;
			}
			c.prev();
			c.appendThisPage(r);
			createIndexForRecord(r, c);
		} catch (Throwable e) {
//			Util.error(e.getMessage());
			e.printStackTrace();
		}
	}
	private void createIndexForRecord(Record r, SimpleCursor c) {
		for(Index idx : tableIndex){
			try {
				BTree b = new BTree(DBEngine.getInstance().btreeManager, idx.pageID, idx.column.type);
				Database.createIndexForRecord(idx, b, c, r);
			} catch (Throwable e) {
				Util.error(e.getMessage());
			}
		}
	}

	@Override
	public void deleteAll() {
		SimpleCursor c = open();
		while(c.hasThis()){
			try {
				c.delete();
			} catch (Throwable e) {
				Util.error(e.getMessage());
			}
		}
	}
	
	// FIXME watch it here as simple cursor is doubly linked list.
	public class SimpleCursor implements Cursor {

		Integer pageID;
		int offset;
		List<Record> cache = new ArrayList<Record>();
		boolean reachedEnd;
		public SimpleCursor(){
			reset();
		}
		@Override
		public void reset() {
			pageID = IOTable.this.firstPageID;
			offset = 0;
			cache = DBEngine.getInstance().recordManager.getRecords(pageID, schema);
		}

		@Override
		public void next() throws Throwable {
			if(offset < cache.size() - 1)offset ++;
			else{
				reachedEnd = !hasNext();
				pageID = getNextPage();
				offset = 0;
				cache = getRecords(pageID);
			}
		}

		private List<Record> getRecords(Integer pid) {
			return DBEngine.getInstance().recordManager.getRecords(pid, schema);
		}
		
		private Integer getNextPage() throws Throwable{
			return DBEngine.getInstance().recordManager.getNextPage(pageID);
		}

		private Integer getPrevPage() throws Throwable {
			return DBEngine.getInstance().recordManager.getPrevPage(pageID);
		}
		
		public boolean appendThisPage(Record r){
			try {
				return DBEngine.getInstance().recordManager.getRecordPage(pageID, false).tryAppendRecord(r);
			} catch (Throwable e) {
				e.printStackTrace();
				return false;
			}
		}
		@Override
		public void prev() throws Throwable {
			if(offset > 0 )offset --;
			else {
				pageID = getPrevPage();
				cache = getRecords(pageID);
				offset = cache.size() - 1;
			}
		}

		@Override
		public Object fetch(String col) {
			return fetchRecord().getCol(col);
		}

		@Override
		public Object[] fetch() {
			return fetchRecord().cols.toArray();
		}

		@Override
		public void delete() throws Throwable {
			RecordPage rp = DBEngine.getInstance().recordManager.getRecordPage(pageID, false);
			rp.delRecord(schema, offset);
			if(offset >= cache.size() - 1){
				reachedEnd = !hasNext();
				pageID = getNextPage();
				offset = 0;
				cache = getRecords(pageID);
			}else{
				cache.remove(offset);
			}
			rp.tryReclaim();
		}
		
		public void updateWithRecord(Record r) throws Throwable {
			DBEngine.getInstance().recordManager.getRecordPage(pageID, false).delRecord(schema, offset);
			DBEngine.getInstance().recordManager.getRecordPage(pageID, false).addRecord(r, offset);
		}

		@Override
		public void close() {
		}

		@Override
		public boolean hasNext() throws Throwable {
			return !getNextPage().equals(firstPageID);
		}

		@Override
		public Record fetchRecord() {
			return cache.get(offset);
		}
		@Override
		public Integer getIdx() {
			return pageID * MODOOFFSET + offset;
		}
		@Override
		public boolean hasThis() {
			return pageID >= 0 && !reachedEnd && offset < cache.size();
		}
		
	}
	
	// TODO use index to accelerate
	public class IndexCursor implements Cursor {
		BTree btree;
		BCursor bc;
		BCursor head;
		BCursor last;
		Index index;
		int idx;
		
		public IndexCursor(Index index, BTree btree) throws Throwable{
			this.btree = btree;
			this.index = index;
			reset();
		}
		@Override
		public void reset() throws Throwable {
			if(head == null)head = btree.root.head();
			if(last == null)last = btree.root.last();
			bc = head;
			idx = 0;
		}

		@Override
		public void next() throws Throwable {
			if(index.unique){
				if(bc.hasNext())
					bc = bc.next();
				else
					bc = null;
			}else {
				idx++;
				if(idx >= index.buckets.get(bc.getValue()).size()){
					if(bc.hasNext()){
						bc = bc.next();
						idx = 0;
					}else
						bc = null;
				}
			}
		}

		@Override
		public void prev() throws Throwable {
			if(index.unique){
				if(bc.hasPrev())
					bc = bc.prev();
				else
					bc = null;
			}else {
				idx--;
				if(idx < 0){
					if(bc.hasPrev()){
						bc = bc.prev();
						idx = index.buckets.get(bc.getValue()).size() - 1;
					}else
						bc = null;
				}
			}
		}

		@Override
		public Object fetch(String col) {
			return fetchRecord().getCol(col);
		}

		@Override
		public Object[] fetch() {
			return fetchRecord().cols.toArray();
		}

		@Override
		public void delete() throws Throwable {
			// TODO how to do this at all
		}

		@Override
		public void close() {
		}

		@Override
		public boolean hasNext() {
			return bc!=null && (!index.unique && idx < index.buckets.get(bc.getValue()).size()-1) || bc.hasNext();
		}

		@Override
		public Record fetchRecord() {
			Integer encodedOffset = index.unique ? bc.getValue() : index.buckets.get(bc.getValue()).get(idx);
			Integer pid = encodedOffset / MODOOFFSET;
			Integer offset = encodedOffset % MODOOFFSET;
			return getRecords(pid).get(offset);
		}
		
		private List<Record> getRecords(Integer pid) {
			return DBEngine.getInstance().recordManager.getRecords(pid, schema);
		}

		@Override
		public Integer getIdx() {
			// shouldnt reach here
			assert false;
			return null;
		}

		@Override
		public boolean hasThis() {
			return bc != null;
		}
	}
}
