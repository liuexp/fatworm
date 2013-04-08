package fatworm.util;

import org.antlr.runtime.tree.BaseTree;
import org.antlr.runtime.tree.Tree;

import fatworm.absyn.Expr;
import fatworm.absyn.QueryCall;
import fatworm.driver.DBEngine;
import fatworm.field.Field;
import fatworm.parser.FatwormParser;
import static java.sql.Types.*;
import fatworm.field.BOOL;

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
			return new QueryCall(DBEngine.transSelect((BaseTree) t));
		}
		return null;
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
}
