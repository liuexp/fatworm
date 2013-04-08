package fatworm.parser;

import java.util.Scanner;

import org.antlr.runtime.ANTLRStringStream;
import org.antlr.runtime.CharStream;
import org.antlr.runtime.CommonTokenStream;
import org.antlr.runtime.RecognitionException;
import org.antlr.runtime.tree.CommonTree;
//TODO actually now I think rewriting a lexer&parser from scratch in antlr4 might be much simpler and much less painful.

public class Tester {

	public static void main(String[] args) throws RecognitionException {
		Scanner in = new Scanner(System.in);
		while (in.hasNextLine()) {
			parse(in.nextLine());
		}
	}
	
	private static void parse(String sql) throws RecognitionException {
		FatwormLexer lexer = new FatwormLexer(new ANTLRStringStream(sql));
		CommonTokenStream tokens = new CommonTokenStream(lexer);
		FatwormParser parser = new FatwormParser(tokens);
		FatwormParser.statement_return rs = parser.statement();
		CommonTree t = (CommonTree) rs.getTree();
		System.out.println(t.toStringTree());
	}
}
