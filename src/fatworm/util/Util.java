package fatworm.util;

import org.antlr.runtime.tree.BaseTree;
import org.antlr.runtime.tree.Tree;

import fatworm.parser.FatwormParser;

public class Util {

	public Util() {
		// TODO Auto-generated constructor stub
	}
	/*
	static public boolean isTau(Tree tree){
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
	static public String getAttr(Tree t) {
		return t.getText().equals(".")?
				t.getChild(0).getText() + "."+ t.getChild(1).getText():
					t.getText();
	}
}
