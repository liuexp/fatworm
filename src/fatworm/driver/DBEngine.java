package fatworm.driver;


import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.antlr.runtime.ANTLRStringStream;
import org.antlr.runtime.CharStream;
import org.antlr.runtime.CommonTokenStream;
import org.antlr.runtime.RecognitionException;
import org.antlr.runtime.tree.BaseTree;
import org.antlr.runtime.tree.CommonTree;
import org.antlr.runtime.tree.Tree;

import static fatworm.parser.FatwormParser.CREATE_DATABASE;
import static fatworm.parser.FatwormParser.CREATE_INDEX;
import static fatworm.parser.FatwormParser.CREATE_TABLE;
import static fatworm.parser.FatwormParser.CREATE_UNIQUE_INDEX;
import static fatworm.parser.FatwormParser.DELETE;
import static fatworm.parser.FatwormParser.DROP_DATABASE;
import static fatworm.parser.FatwormParser.DROP_INDEX;
import static fatworm.parser.FatwormParser.DROP_TABLE;
import static fatworm.parser.FatwormParser.INSERT_COLUMNS;
import static fatworm.parser.FatwormParser.INSERT_SUBQUERY;
import static fatworm.parser.FatwormParser.INSERT_VALUES;
import static fatworm.parser.FatwormParser.SELECT;
import static fatworm.parser.FatwormParser.SELECT_DISTINCT;
import static fatworm.parser.FatwormParser.UPDATE;
import static fatworm.parser.FatwormParser.USE_DATABASE;
import fatworm.absyn.Expr;
import fatworm.io.BufferManager;
import fatworm.io.File;
import fatworm.logicplan.None;
import fatworm.logicplan.Plan;
import fatworm.parser.FatwormLexer;
import fatworm.parser.FatwormParser;
import fatworm.util.Env;
import fatworm.util.Util;

// It's true that byte code is much easier for (low-level) optimization, e.g. instruction re-order/elimination
// but a direct interpreter is much easier to write.
public class DBEngine {

	private static final long maxMemSize = 1000 * 1024;
	
	public Map<String, Database> dbList = new HashMap<String, Database>();
//	public Map<Integer, List<Index>> indexList = new HashMap<Integer, List<Index>>();
	private static DBEngine instance;
	private Database db;
	private String metaFile;
	public BufferManager btreeManager;
	public BufferManager recordManager;
	public final boolean turnOnIndex = true;
	public final boolean turnOnDebug = false;

	public static synchronized DBEngine getInstance() {
		if (instance == null)
			instance = new DBEngine();
		return instance;
	}
	public DBEngine() {
		// TODO Auto-generated constructor stub
	}
	
	@SuppressWarnings("unchecked")
	public void open(String file) throws IOException, ClassNotFoundException {
		
		metaFile = Util.getMetaFile(file);
		try {
			ObjectInputStream in = new ObjectInputStream(new BufferedInputStream(new FileInputStream(metaFile)));
			dbList = (HashMap<String, Database>)in.readObject();
			in.close();
		} catch (FileNotFoundException e) {
			dbList = new HashMap<String, Database>();
			ObjectOutputStream out = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(metaFile)));
			out.writeObject(dbList);
			out.close();
		}
		recordManager = new BufferManager(Util.getRecordFile(file), File.RECORDFILE);
		btreeManager = new BufferManager(Util.getBTreeFile(file), File.BTREEFILE);
	}
	
	public ResultSet execute(String sql) {
		try {
			CommonTree t = parse(sql);
			//System.out.println(t.toStringTree());
			return execute(t);
		} catch (Throwable e) {
//			Util.error(e.getMessage());
			e.printStackTrace();
			return new ResultSet(None.getInstance());
		}
	}

	private ResultSet execute(CommonTree t) throws Throwable {
		String name = null;
		Expr e = null;
		List<String> colName = new ArrayList<String>();
		List<Expr> expr = new ArrayList<Expr>();
		Table table = null;
		Plan plan;
		switch(t.getType()){
		case SELECT:
		case SELECT_DISTINCT:
			plan = Util.transSelect(t, true);
			//System.out.println(plan.toString());
			plan.eval(new Env());
			return new ResultSet(plan);
		case USE_DATABASE:
			name = t.getChild(0).getText().toLowerCase();
			db = dbList.get(name);
			return new ResultSet(None.getInstance());
		case CREATE_DATABASE:
			name = t.getChild(0).getText().toLowerCase();
			dbList.put(name, new Database(name));
			return new ResultSet(None.getInstance());
		case DROP_DATABASE:
			name = t.getChild(0).getText().toLowerCase();
			dbList.remove(name);
			if(db!=null && db.name.equals(name))
				db = null;
			return new ResultSet(None.getInstance());
		case CREATE_TABLE:
			name = t.getChild(0).getText().toLowerCase();
			db.addTable(name, new IOTable(t));
//			db.addTable(name, new MemTable(t));
			return new ResultSet(None.getInstance());
		case DROP_TABLE:
			name = t.getChild(0).getText().toLowerCase();
			db.delTable(name);
			return new ResultSet(None.getInstance());
		case DELETE:
			name = t.getChild(0).getText().toLowerCase();
			e = t.getChildCount() == 1 ? null : Util.getExpr(t.getChild(1).getChild(0));
			db.getTable(name).delete(e);
			return new ResultSet(None.getInstance());
		case UPDATE:
			name = t.getChild(0).getText().toLowerCase();
			for(int i=1;i<t.getChildCount();i++){
				Tree c = t.getChild(i);
				if(c.getType() == FatwormParser.UPDATE_PAIR){
					colName.add(c.getChild(0).getText());
					expr.add(Util.getExpr(c.getChild(1)));
				}else {
					e = Util.getExpr(c.getChild(0));
				}
			}
			db.getTable(name).update(colName, expr, e);
			return new ResultSet(None.getInstance());
		case INSERT_VALUES:
			name = t.getChild(0).getText().toLowerCase();
			db.getTable(name).insert(t.getChild(1));
			return new ResultSet(None.getInstance());
		case INSERT_COLUMNS:
			name = t.getChild(0).getText().toLowerCase();
			db.getTable(name).insert(t, t.getChild(t.getChildCount()-1));
			return new ResultSet(None.getInstance());
		case INSERT_SUBQUERY:
			name = t.getChild(0).getText().toLowerCase();
			table = db.getTable(name);
			plan = Util.transSelect((BaseTree) t.getChild(1), true);
			plan.eval(new Env());
			//FIXME this should be put onto disk
			List<Record> tmpTable = new LinkedList<Record>();
			while(plan.hasNext()){
				Record r = plan.next();
				r.schema = table.schema;
				tmpTable.add(r);
			}
			for(Record r:tmpTable){
				table.addRecord(r);
			}
			plan.close();
			return new ResultSet(None.getInstance());
		case CREATE_INDEX:
		case CREATE_UNIQUE_INDEX:
			db.createIndex(t.getChild(0).getText().toLowerCase(),
					t.getChild(1).getText().toLowerCase(),
					t.getChild(2).getText().toLowerCase(),
					t.getType() == CREATE_UNIQUE_INDEX);
			return new ResultSet(None.getInstance());
		case DROP_INDEX:
			db.dropIndex(t.getChild(0).getText().toLowerCase());
			return new ResultSet(None.getInstance());
		default:
				Util.error("not implemented.");
				return new ResultSet(None.getInstance());
		}
	}

	private static CommonTree parse(String sql) throws RecognitionException {
		CharStream cs = new ANTLRStringStream(sql);
		FatwormLexer lexer = new FatwormLexer(cs);
		CommonTokenStream tokens = new CommonTokenStream(lexer);
		FatwormParser parser = new FatwormParser(tokens);
		FatwormParser.statement_return r = parser.statement();
		return (CommonTree) r.getTree();
	}
	
	public void close() {
		Util.warn("closing connection");
		try {
			ObjectOutputStream out = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(metaFile)));
			out.writeObject(dbList);
			out.close();
			recordManager.close();
			btreeManager.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Override
	protected void finalize() throws Throwable {
		close();
	}

	public Table getTable(String tbl){
		return db.getTable(tbl.toLowerCase());
	}
	
	public Database getDatabase(){
		return db;
	}
	public boolean nearOOM() {
		return btreeManager.pages.size() * File.btreePageMult+ recordManager.pages.size()* File.recordPageMult >= maxMemSize;
	}
	public void fireOther(BufferManager me) throws Throwable{
		if(me == btreeManager){
			if(!recordManager.fireMeOne())
				btreeManager.fireMeOne();
			Util.warn("[BTreeBufferManager]I'm btree, I just fired one record!");
		}else{
			if(!btreeManager.fireMeOne())
				recordManager.fireMeOne();
			Util.warn("[RecordBufferManager]I'm record, I just fired one btree!");
		}
	}
	
//	public synchronized void announceBTreeNewRoot(int oldRoot, int newRoot){
//		List<Index> idxs = indexList.remove(oldRoot);
//		if(idxs == null)
//			return;
//		for(Index idx : idxs){
//			synchronized(idx){
//				assert idx.pageID.equals(oldRoot);
//				idx.pageID = newRoot;
//			}
//		}
//		indexList.put(newRoot, idxs);
//	}
//	public synchronized void addIndex(Index index) {
//		List<Index> idxs = indexList.remove(index.pageID);
//		if(idxs==null)
//			idxs = new LinkedList<Index>();
//		else{
//			Util.warn("Another index with same pageID detected!me="+index.toString()+", them="+Util.deepToString(idxs));
//		}
//		idxs.add(index);
//		indexList.put(index.pageID, idxs);
//	}
//	public synchronized void removeIndex(Index index) {
//		List<Index> idxs = indexList.remove(index.pageID);
//		if(idxs==null)
//			return;
//		idxs.remove(index);
//		if(!idxs.isEmpty())
//			indexList.put(index.pageID, idxs);
//	}
}
