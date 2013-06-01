package fatworm.main;

import java.nio.ByteBuffer;
import java.sql.ResultSet;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

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
import fatworm.page.Page;
import fatworm.util.Util;

public class Main {

	public Main() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param args
	 * @throws Throwable 
	 */
	public static void main(String[] args) throws Throwable {
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
//		TreeSet<Long> q= new TreeSet<Long> (new Comparator<Long>(){
//			public int compare(Long a, Long b){
//				return a.compareTo(b);
//			}
//		});
//		q.add(System.currentTimeMillis());
//		for(int i=0;i<10000;i++)q.first();
//		q.add(System.currentTimeMillis());
//		System.out.println(q.pollFirst());
//		System.out.println(q.pollFirst());
//		byte[] bbb = new byte[8];
//		ByteBuffer buf = ByteBuffer.wrap(bbb);
//		System.out.println(bbb.length);
//		buf.putInt(1);
//		buf.putInt(2);
//		buf.rewind();
//		buf.getInt();
//		System.out.println(buf.slice().array().length);
//		Byte[] tmpb = new Byte[3];
//		tmpb[0] = 1;
//		List<Byte> z = Arrays.asList(tmpb);
//		Collections.reverse(z);
//		System.out.println(tmpb[0]);
//		BinaryExpr ee = new BinaryExpr(new Id("a.ch"), BinaryOp.PLUS, new BinaryExpr(new Id("b.ch"), BinaryOp.PLUS, new Id("c.ch")));
//		System.out.println(ee.toString());
		System.out.print(">");
		db.open("/tmp/meow");
		while (in.hasNextLine()) {
				try {
					String tmp = in.nextLine();
					if(tmp.equalsIgnoreCase("quit")||tmp.equalsIgnoreCase("exit")||tmp.equalsIgnoreCase("\\q"))break;
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
