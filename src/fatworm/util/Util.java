package fatworm.util;

import org.antlr.runtime.tree.BaseTree;
import org.antlr.runtime.tree.Tree;

import fatworm.absyn.Expr;
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
		// TODO Auto-generated method stub
		return null;
	}
}
