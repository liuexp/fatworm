package fatworm.main;

import java.sql.ResultSet;
import java.util.Scanner;

import fatworm.driver.DBEngine;
import fatworm.field.DATE;
import fatworm.logicplan.Plan;

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
		System.out.print(">");
		
		while (in.hasNextLine()) {
				try {
					String tmp = in.nextLine();
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
	}

}
