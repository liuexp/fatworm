package fatworm.main;

import java.sql.ResultSet;
import java.util.Scanner;

import fatworm.driver.DBEngine;
import fatworm.field.DATE;

public class Main {

	public Main() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		DBEngine db = new DBEngine();
		Scanner in = new Scanner(System.in);
		//DATE zz = new DATE("2013-4-16 19:42:30");
		//System.out.println(zz.toString());
		System.out.print(">");
		
		while (in.hasNextLine()) {
				try {
					String tmp = in.nextLine();
					@SuppressWarnings("unused")
					ResultSet result = db.execute(tmp);
				} catch (Exception e) {
					e.printStackTrace();
				}
				System.out.print("\r\n>");
		}
		in.close();
	}

}
