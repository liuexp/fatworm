package fatworm.driver;

import java.util.ArrayList;
import java.util.List;

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
import fatworm.absyn.*;
import fatworm.logicplan.Distinct;
import fatworm.logicplan.FetchTable;
import fatworm.logicplan.Join;
import fatworm.logicplan.Node;
import fatworm.logicplan.One;
import fatworm.logicplan.RenameTable;
import fatworm.logicplan.Select;
import fatworm.parser.FatwormLexer;
import fatworm.parser.FatwormParser;
import fatworm.util.Traverse;
import fatworm.util.Util;


public class DBEngine {

	public DBEngine() {
		// TODO Auto-generated constructor stub
	}
	
	public ResultSet execute(String sql) throws Exception {
		CommonTree t = parse(sql);
		Node x = buildPlan(t);
		x.eval();
		return new ResultSet(x);
	}

	private static Node buildPlan(CommonTree t) {
		switch(t.getType()){
		case SELECT:
		case SELECT_DISTINCT:
			return transSelect(t);
		default:
				throw new RuntimeException("not implemented.");
		}
	}

	private static Node transSelect(BaseTree t) {
		// TODO
		// Plan order:
		// Distinct $ Order $ Rename $ Group $ Select $ source
		Node ret=null,src=null;
		Expr pred=null;
		Expr having=null;
		String groupBy = null;
		
		List<Integer> orderType = new ArrayList<Integer>();
		List<String> orderField = new ArrayList<String>();
		
		// extract FROM, WHERE, GROUPBY, HAVING, ORDERBY first
		for(Object x : t.getChildren()){
			Tree y = (Tree) x;
			switch(y.getType()){
			case FatwormParser.FROM:
				src = transFrom((BaseTree)y);
				break;
			case FatwormParser.WHERE:
				pred = Util.getExpr(y.getChild(0));
				break;
			case FatwormParser.GROUP:
				groupBy = Util.getAttr(y.getChild(0));
				break;
			case FatwormParser.HAVING:
				having = Util.getExpr(y.getChild(0));
				break;
			case FatwormParser.ORDER:
				for(Object zzz : ((BaseTree) y).getChildren()){
					Tree zz = (Tree) zzz;
					orderType.add(zz.getType() == FatwormParser.DESC?
							zz.getType():
								FatwormParser.ASC);
					
					orderField.add(zz.getType() == FatwormParser.DESC||zz.getType() == FatwormParser.ASC?
							Util.getAttr(zz.getChild(0)):
								Util.getAttr(zz));
				}
				break;
			}
		}
		
		if(src == null)src = new One();
		ret = src;
		if(pred != null)ret = new Select(src, pred);
		boolean hasAggr = !(groupBy == null&&having == null);
		
		// next do projection
		List<Expr> expr = new ArrayList<Expr>();
		List<String> alias = new ArrayList<String>();
		for(Object x : t.getChildren()){
			Tree y = (Tree) x;
			if(y.getType() == FatwormParser.FROM)
				break;
			if(y.getText().equals("*") && y.getChildCount() == 0)
				break;
			if(y.getType() == FatwormParser.AS){
				expr.add(Util.getExpr(y.getChild(0)));
				alias.add(y.getChild(1).getText());
			}else{
				Expr tmp = Util.getExpr(y);
				expr.add(tmp);
				alias.add(tmp.toString());
			}
			hasAggr |= Util.findAggr(y);
		}
		
		if(!alias.isEmpty()){
			if(!hasAggr){
				
			}
		}
		
		if(t.getType() == SELECT_DISTINCT)ret = new Distinct(ret);
		return ret;
	}

	private static void traverse(CommonTree t, Traverse traverse) {
		// TODO Auto-generated method stub
		
	}

	protected static Node transFrom(BaseTree t) {
		Node ret = null;
		String as = null;
		for(Object x : t.getChildren()){
			Tree y = (Tree) x;
			String table = null;
			Node src = null;
			if(y.getType() == FatwormParser.AS){
				if(y.getChild(0).getType() == SELECT || y.getChild(0).getType() == SELECT_DISTINCT){
					src = transSelect((BaseTree) y.getChild(0));
				}else table = y.getChild(0).getText();
				as = y.getChild(1).getText();
			} else table = y.getText();
			if(src == null)
				src = new FetchTable(table);
			if(as != null)
				src = new RenameTable(src, as);
			ret = ret == null ? src : new Join(ret,src);
		}
		return ret;
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
