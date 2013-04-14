package fatworm.main;

import java.sql.ResultSet;
import java.util.Scanner;

import fatworm.driver.DBEngine;

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
