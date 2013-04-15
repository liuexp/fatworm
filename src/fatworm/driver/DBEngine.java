package fatworm.driver;


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
import fatworm.absyn.*;
import fatworm.logicplan.Plan;
import fatworm.parser.FatwormLexer;
import fatworm.parser.FatwormParser;
import fatworm.util.Traverse;
import fatworm.util.Util;

// It's true that byte code is much easier for (low-level) optimization, e.g. instruction re-order/elimination
// but a direct interpreter is much easier to write.
public class DBEngine {

	public DBEngine() {
		// TODO Auto-generated constructor stub
	}
	
	public ResultSet execute(String sql) throws Exception {
		CommonTree t = parse(sql);
		Plan x = buildPlan(t);
		x.eval();
		return new ResultSet(x);
	}

	private static Plan buildPlan(CommonTree t) {
		switch(t.getType()){
		case SELECT:
		case SELECT_DISTINCT:
			return Util.transSelect(t);
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

}
