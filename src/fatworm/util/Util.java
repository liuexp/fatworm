package fatworm.util;

import static fatworm.parser.FatwormParser.SELECT;
import static fatworm.parser.FatwormParser.SELECT_DISTINCT;

import java.util.ArrayList;
import java.util.List;

import org.antlr.runtime.tree.BaseTree;
import org.antlr.runtime.tree.Tree;

import fatworm.absyn.BinaryExpr;
import fatworm.absyn.BinaryOp;
import fatworm.absyn.BoolLiteral;
import fatworm.absyn.Expr;
import fatworm.absyn.FloatLiteral;
import fatworm.absyn.FuncCall;
import fatworm.absyn.Id;
import fatworm.absyn.InCall;
import fatworm.absyn.IntLiteral;
import fatworm.absyn.QueryCall;
import fatworm.absyn.StringLiteral;
import fatworm.absyn.ExistCall;
import fatworm.absyn.AnyCall;
import fatworm.field.FLOAT;
import fatworm.field.Field;
import fatworm.field.INT;
import fatworm.logicplan.Distinct;
import fatworm.logicplan.FetchTable;
import fatworm.logicplan.Group;
import fatworm.logicplan.Join;
import fatworm.logicplan.One;
import fatworm.logicplan.Order;
import fatworm.logicplan.Plan;
import fatworm.logicplan.Project;
import fatworm.logicplan.Rename;
import fatworm.logicplan.RenameTable;
import fatworm.logicplan.Select;
import fatworm.parser.FatwormParser;

public class Util {

	public Util() {
	}
	
	public static String getAttr(Tree t) {
		return t.getText().equals(".")?
				t.getChild(0).getText() + "."+ t.getChild(1).getText():
					t.getText();
	}

	public static Expr getExpr(Tree t) {
		switch(t.getType()){
		case FatwormParser.SELECT:
		case FatwormParser.SELECT_DISTINCT:
			return new QueryCall(Util.transSelect((BaseTree) t));
		case FatwormParser.SUM:
		case FatwormParser.AVG:
		case FatwormParser.COUNT:
		case FatwormParser.MAX:
		case FatwormParser.MIN:
			return new FuncCall(t.getType(), getAttr(t.getChild(0)));
		case FatwormParser.INTEGER_LITERAL:
			return new IntLiteral(Integer.parseInt(t.getText()));
		case FatwormParser.FLOAT_LITERAL:
			return new FloatLiteral(Float.parseFloat(t.getText()));
		case FatwormParser.STRING_LITERAL:
			return new StringLiteral(strip(t.getText()));
		case FatwormParser.TRUE:
		case FatwormParser.FALSE:
			return new BoolLiteral(t.getType() == FatwormParser.TRUE);
		case FatwormParser.AND:
			return new BinaryExpr(getExpr(t.getChild(0)), BinaryOp.AND, getExpr(t.getChild(1)));
		case FatwormParser.OR:
			return new BinaryExpr(getExpr(t.getChild(0)), BinaryOp.OR, getExpr(t.getChild(1)));
		case FatwormParser.EXISTS:
		case FatwormParser.NOT_EXISTS:
			if(!(t.getChild(0) instanceof BaseTree))return null;
			return new ExistCall(transSelect((BaseTree) t.getChild(0)), t.getType() == FatwormParser.NOT_EXISTS);
		case FatwormParser.IN:
			// FIXME according to Fatowrm.g only subquery is allowed
			// FIXME according to Fatowrm.g there's no NOT_IN
			return new InCall(transSelect((BaseTree)t.getChild(1)), getExpr(t.getChild(0)), t.getType() != FatwormParser.IN);
		case FatwormParser.ANY:
		case FatwormParser.ALL:
			// FIXME according to Fatowrm.g only subquery is allowed
			return new AnyCall(transSelect((BaseTree) t.getChild(2)), getExpr(t.getChild(0)), getBinaryOp(t.getChild(1).getText()), t.getType() == FatwormParser.ALL);
			default:
				BinaryOp op = getBinaryOp(t.getText());
				if(op!=null && t.getChildCount() == 2){
					return new BinaryExpr(getExpr(t.getChild(0)), op, getExpr(t.getChild(1)));
				} else if(op!=null){
					Expr tmp = getExpr(t.getChild(0));
					if(tmp instanceof IntLiteral){
						INT f = (INT)((IntLiteral)tmp).i;
						return new IntLiteral(-f.v);
					}else if(tmp instanceof FloatLiteral){
						FLOAT f = (FLOAT)((FloatLiteral)tmp).i;
						return new FloatLiteral(-f.v);
					}else
						return new BinaryExpr(new IntLiteral(0), op, tmp);
				}else
					return new Id(getAttr(t));
		}
	}

	public static boolean toBoolean(Field c) {
		return toBoolean(c.toString());
	}

	public static boolean toBoolean(String s) {
		s = trim(s);
		if(s.equalsIgnoreCase("false") || s.equals("0"))
			return false;
		return true;
	}
	
	public static String trim(String s){
		if(((s.startsWith("'") && s.endsWith("'"))||(s.startsWith("\"") && s.endsWith("\""))) && s.length() >= 2)
			return s.substring(1,s.length()-1);
		else 
			return s;
	}
	
	public static String strip(String s){
		return s.substring(1, s.length() - 1);
	}

	public static Plan transSelect(BaseTree t) {
		// Note: Group can't do projection at the same time as order might need those abandoned fields.
		//			Rename must go after group
		//			order must go after rename
		// Plan order:
		// hasAggr:		Distinct $ Project $ Order $ Rename $ Group $ Select $ source
		// !hasAggr:	Distinct $ Project $ Order $ Rename $ Select $ source
		if(t.getType()!=FatwormParser.SELECT && t.getType()!=FatwormParser.SELECT_DISTINCT)
			return null;
		Plan ret=null,src=null;
		Expr pred=null;
		Expr having=null;
		String groupBy = null;
		
		List<Integer> orderType = new ArrayList<Integer>();
		List<String> orderField = new ArrayList<String>();
		boolean hasOrder = false;
		
		// extract FROM, WHERE, GROUPBY, HAVING, ORDERBY first
		for(Object x : t.getChildren()){
			Tree y = (Tree) x;
			switch(y.getType()){
			case FatwormParser.FROM:
				src = transFrom((BaseTree)y);
				break;
			case FatwormParser.WHERE:
				pred = getExpr(y.getChild(0));
				break;
			case FatwormParser.GROUP:
				groupBy = getAttr(y.getChild(0));
				break;
			case FatwormParser.HAVING:
				having = getExpr(y.getChild(0));
				break;
			case FatwormParser.ORDER:
				for(Object zzz : ((BaseTree) y).getChildren()){
					Tree zz = (Tree) zzz;
					orderType.add(zz.getType() == FatwormParser.DESC?
							zz.getType():
								FatwormParser.ASC);
					
					orderField.add(zz.getType() == FatwormParser.DESC||zz.getType() == FatwormParser.ASC?
							getAttr(zz.getChild(0)):
								getAttr(zz));
					hasOrder = true;
				}
				break;
			}
		}
		
		if(src == null)src = new One();
		ret = src;
		if(pred != null)ret = new Select(src, pred);
		boolean hasAggr = !(groupBy == null&&having == null);
		boolean hasRename = false;
		
		// next prepare projection and re-check global aggregation
		List<Expr> expr = new ArrayList<Expr>();
		List<String> alias = new ArrayList<String>();
		for(Object x : t.getChildren()){
			Tree y = (Tree) x;
			if(y.getType() == FatwormParser.FROM)
				break;
			if(y.getText().equals("*") && y.getChildCount() == 0)
				break;
			
			if(y.getType() == FatwormParser.AS){
				Expr tmp = getExpr(y.getChild(0));
				expr.add(tmp);
				alias.add(y.getChild(1).getText());
				hasRename = true;
				hasAggr |= tmp.hasAggr;
			}else{
				Expr tmp = getExpr(y);
				expr.add(tmp);
				alias.add(tmp.toString());
				hasAggr |= tmp.hasAggr;
			}
		}
		
		if(hasAggr)
			ret = new Group(ret, expr, groupBy, having);
		if(hasRename)
			ret = new Rename(ret, alias);
		if(hasOrder)
			ret = new Order(ret, orderField, orderType);
		if(!expr.isEmpty()) //hasProject
			ret = new Project(ret, expr);
		if(t.getType() == SELECT_DISTINCT) //hasDistinct
			ret = new Distinct(ret);
		
		return ret;
	}

	public static Plan transFrom(BaseTree t) {
		Plan ret = null;
		String as = null;
		for(Object x : t.getChildren()){
			Tree y = (Tree) x;
			String table = null;
			Plan src = null;
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

	public static String getFuncName(int func) {
		switch(func){
		case FatwormParser.SUM:
			return "sum";
		case FatwormParser.AVG:
			return "avg";
		case FatwormParser.COUNT:
			return "count";
		case FatwormParser.MAX:
			return "max";
		case FatwormParser.MIN:
			return "min";
		}
		return "MEOWFunc";
	}
	
	public static BinaryOp getBinaryOp(String ops){
		BinaryOp op = null;
		if(ops.equals("+"))
			op = BinaryOp.PLUS;
		else if(ops.equals("-"))
			op = BinaryOp.MINUS;
		else if(ops.equals("*"))
			op = BinaryOp.MULTIPLY;
		else if(ops.equals("/"))
			op = BinaryOp.DIVIDE;
		else if(ops.equals("%"))
			op = BinaryOp.MODULO;
		else if(ops.equals("="))
			op = BinaryOp.EQ;
		else if(ops.equals("<"))
			op = BinaryOp.LESS;
		else if(ops.equals(">"))
			op = BinaryOp.GREATER;
		else if(ops.equals("<="))
			op = BinaryOp.LESS_EQ;
		else if(ops.equals(">="))
			op = BinaryOp.GREATER_EQ;
		else if(ops.equals("<>"))
			op = BinaryOp.NEQ;
		return op;
	}
}
