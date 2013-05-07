package fatworm.main;

import java.sql.ResultSet;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

import fatworm.absyn.BinaryExpr;
import fatworm.absyn.BinaryOp;
import fatworm.absyn.Id;
import fatworm.driver.DBEngine;
import fatworm.driver.Record;
import fatworm.driver.Schema;
import fatworm.field.DATE;
import fatworm.field.Field;
import fatworm.field.INT;
import fatworm.field.TIMESTAMP;
import fatworm.logicplan.Plan;
import fatworm.util.Util;

public class Main {

	public Main() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		DBEngine db = DBEngine.getInstance();
		Scanner in = new Scanner(System.in);
		//DATE zz = new DATE("2013-4-16 19:42:30");
		//System.out.println(zz.toString());
//		Field a = new INT(1);
//		Field b = new INT(1);
//		Record ra = new Record(new Schema("meow"));
//		ra.cols.add(a);
//		Record rb = new Record(new Schema("meow2"));
//		rb.cols.add(b);
//		System.out.println(a.equals(b));
//		System.out.println(Util.deepEquals(ra.cols, rb.cols));
//		Set<Record> meow = new HashSet<Record>();
//		meow.add(ra);
//		meow.add(rb);
//		System.out.println(Util.deepToString(meow));
//		TIMESTAMP x = new TIMESTAMP("2013-04-20 14:50:27.108");
//		TIMESTAMP y = new TIMESTAMP("2013-04-20 14:50:28.118");
//		System.out.println(x.applyWithComp(BinaryOp.LESS, y));
//		System.out.println(new BinaryExpr(new Id("a"), BinaryOp.AND, new Id("b")).isAnd());
		System.out.print(">");
		db.open("/tmp/meow");
		while (in.hasNextLine()) {
				try {
					String tmp = in.nextLine();
					if(tmp.equalsIgnoreCase("quit"))break;
					@SuppressWarnings("unused")
					fatworm.driver.ResultSet result = db.execute(tmp);
					Plan plan = result.plan;
					if(!plan.hasNext()) 
						System.out.println("no results");
					while(plan.hasNext()){
						System.out.println(plan.next().toString());
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
				System.out.print("\r\n>");
		}
		in.close();
		db.close();
	}

}
