package fatworm.driver;

import java.util.ArrayList;
import java.util.List;

import org.antlr.runtime.tree.CommonTree;
import org.antlr.runtime.tree.Tree;

import fatworm.absyn.Expr;
import fatworm.driver.Database.Index;
import fatworm.field.INT;
import fatworm.field.NULL;
import fatworm.io.BKey;
import fatworm.io.BTree;
import fatworm.io.Cursor;
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
	}
	public IOTable(CommonTree t) {
		this();
		schema = new Schema(t);
		// build index on primaryKey
		if(schema.primaryKey != null){
//			Index pindex = new Index(Util.getPKIndexName(schema.primaryKey.name), this, schema.primaryKey);
			// XXX I'm going to hack it this way
			DBEngine.getInstance().getDatabase().createIndex(Util.getPKIndexName(schema.primaryKey.name), schema.tableName, schema.primaryKey.name, true);
		}
	}

	public SimpleCursor open() {
		return new SimpleCursor();
	}
	@Override
	public int update(List<String> colName, List<Expr> expr, Expr e) {
		SimpleCursor c = open();
		// TODO 
		return 0;
	}

	@Override
	public void addRecord(Record r) {
		SimpleCursor c = open();
		try {
			c.prev();
			//TODO
			
			//FIXME extract this and combine with those create index
			for(Index idx : tableIndex){
				try {
					BTree b = new BTree(DBEngine.getInstance().btreeManager, idx.pageID, idx.column.type);
					BKey key = b.newBKey(r.getCol(idx.column.name));
					BCursor bc = b.root.lookup(key);
					if(idx.unique){
						bc.insert(key, c.getIdx());
					}else{
						if(bc.getKey().equals(key)){
							idx.buckets.get(bc.getValue()).add(c.getIdx());
						}else{
							List<Integer> tmp = new ArrayList<Integer>();
							tmp.add(c.getIdx());
							bc.insert(key, idx.buckets.size());
							idx.buckets.add(tmp);
						}
					}
				} catch (Throwable e) {
					Util.error(e.getMessage());
				}
			}
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void deleteAll() {
		SimpleCursor c = open();
		// TODO 
		
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
			cache = DBEngine.getInstance().recordManager.getRecords(pageID);
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
			return DBEngine.getInstance().recordManager.getRecords(pid);
		}
		
		private Integer getNextPage() throws Throwable{
			return DBEngine.getInstance().recordManager.getNextPage(pageID);
		}

		private Integer getPrevPage() throws Throwable {
			return DBEngine.getInstance().recordManager.getPrevPage(pageID);
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
			// TODO how to do this at all
		}

		@Override
		public void close() {
		}

		@Override
		public boolean hasNext() throws Throwable {
			return getNextPage() != firstPageID;
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
			return pageID >= 0 && !reachedEnd;
		}
		
	}
	
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
			return DBEngine.getInstance().recordManager.getRecords(pid);
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
