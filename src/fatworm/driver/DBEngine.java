package fatworm.driver;


import java.sql.SQLException;
import java.util.Map;

import org.antlr.runtime.ANTLRStringStream;
import org.antlr.runtime.CharStream;
import org.antlr.runtime.CommonTokenStream;
import org.antlr.runtime.RecognitionException;
import org.antlr.runtime.tree.CommonTree;

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
import fatworm.logicplan.Plan;
import fatworm.parser.FatwormLexer;
import fatworm.parser.FatwormParser;
import fatworm.util.Env;
import fatworm.util.Util;

// It's true that byte code is much easier for (low-level) optimization, e.g. instruction re-order/elimination
// but a direct interpreter is much easier to write.
public class DBEngine {

	public String name;
	public Map<String, Table> dbList;
	private static DBEngine instance;
	private Database db;

	public static synchronized DBEngine getInstance() {
		if (instance == null)
			instance = new DBEngine();
		return instance;
	}
	public DBEngine() {
		// TODO Auto-generated constructor stub
	}
	
	public ResultSet execute(String sql) throws Exception {
		CommonTree t = parse(sql);
		System.out.println(t.toStringTree());
		return execute(t);
	}

	private ResultSet execute(CommonTree t) throws SQLException {
		switch(t.getType()){
		case SELECT:
		case SELECT_DISTINCT:
			Plan x = Util.transSelect(t);
			System.out.println(x.toString());
			x.eval(new Env());
			if(x.hasNext())
				System.out.println(x.next().toString());
			else 
				System.out.println("no results");
			return new ResultSet(x);
		case CREATE_DATABASE:
		case USE_DATABASE:
		case DROP_DATABASE:
		case CREATE_TABLE:
		case DROP_TABLE:
		case INSERT_VALUES:
		case INSERT_COLUMNS:
		case INSERT_SUBQUERY:
		case DELETE:
		case UPDATE:
		case CREATE_INDEX:
		case CREATE_UNIQUE_INDEX:
		case DROP_INDEX:
			//TODO
		default:
				throw new RuntimeException("not implemented.");
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
		// TODO Auto-generated method stub
		
	}

}
