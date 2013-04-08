package fatworm.util;

import org.antlr.runtime.tree.BaseTree;
import org.antlr.runtime.tree.Tree;

import fatworm.absyn.Expr;
import fatworm.parser.FatwormParser;

public class Util {

	public Util() {
		// TODO Auto-generated constructor stub
	}
	/*
	public static boolean isTau(Tree tree){
		switch (tree.getType()) {
		case FatwormParser.AND:
		case FatwormParser.OR:
		case FatwormParser.EXISTS:
		case FatwormParser.NOT_EXISTS:
		case FatwormParser.IN:
		case FatwormParser.ANY:
		case FatwormParser.ALL:
			return false;
		default:
			//FIXME: evaluate binary expr
			return true;
		}
		return true;
	}*/
	
	public static String getAttr(Tree t) {
		return t.getText().equals(".")?
				t.getChild(0).getText() + "."+ t.getChild(1).getText():
					t.getText();
	}
	
	public static boolean findAggr(Tree t) {
		switch(t.getType()){
		case FatwormParser.AVG:
		case FatwormParser.COUNT:
		case FatwormParser.MAX:
		case FatwormParser.MIN:
		case FatwormParser.SUM:
			return true;
		}
		if(t.getChildCount() == 0)return false;
		for(Object v : ((BaseTree) t).getChildren()){
			if(findAggr((Tree) v))return true;
		}
		return false;
	}

	public static Expr getExpr(Tree t) {
		// TODO Auto-generated method stub
		return null;
	}
}
