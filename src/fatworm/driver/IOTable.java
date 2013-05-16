package fatworm.driver;

import java.util.ArrayList;
import java.util.List;

import org.antlr.runtime.tree.CommonTree;
import org.antlr.runtime.tree.Tree;

import fatworm.absyn.Expr;
import fatworm.driver.Database.Index;
import fatworm.field.INT;
import fatworm.field.NULL;
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
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void addRecord(Record r) {
		SimpleCursor c = open();
		//TODO
	}

	@Override
	public void deleteAll() {
		// TODO 
		
	}
	
	// FIXME watch it here as simple cursor is doubly linked list.
	public class SimpleCursor implements Cursor {

		Integer pageID;
		int offset;
		List<Record> cache = new ArrayList<Record>();
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
		
		@Override
		public void prev() {
			// FIXME who will ever call me here at all?
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
			return pageID >= 0 ;
		}
		
	}
	
	public class IndexCursor implements Cursor {

		BCursor bc;
		
		
		@Override
		public void reset() {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void next() {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void prev() {
			// TODO Auto-generated method stub
			
		}

		@Override
		public Object fetch(String col) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public Object[] fetch() {
			// TODO Auto-generated method stub
			return null;
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
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public Record fetchRecord() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public Integer getIdx() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public boolean hasThis() {
			// TODO Auto-generated method stub
			return false;
		}
	}
}
