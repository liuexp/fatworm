package fatworm.util;

import java.sql.ResultSet;
import java.util.Scanner;

import fatworm.absyn.BinaryExpr;
import fatworm.absyn.BinaryOp;
import fatworm.absyn.Expr;
import fatworm.absyn.IntLiteral;
import fatworm.driver.DBEngine;

public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		DBEngine db = new DBEngine();
		Scanner in = new Scanner(System.in);
		
		Expr ex = new BinaryExpr(new IntLiteral(1), BinaryOp.PLUS, new IntLiteral(2));
		System.out.println(ex.eval(new Env()));
		System.out.print(">");
		while (in.hasNextLine()) {
				try {
					String tmp = in.nextLine();
				} catch (Exception e) {
					e.printStackTrace();
				}
				System.out.print("\r\n>");
		}
		in.close();
	}

}
