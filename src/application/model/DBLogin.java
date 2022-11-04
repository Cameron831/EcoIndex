package application.model;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class DBLogin implements DBHandler {
	// singleton essentials
	private static DBLogin singleInstance = new DBLogin();

	private DBLogin() {
	};

	public static DBLogin getSingle() {
		return singleInstance;
	}

	private File database = new File("UserDB.txt");

	private String searchUsername(String un) {
		try {
			Scanner reader = new Scanner(database);
			String ret;
			String compare;

			do {
				ret = reader.nextLine();
				compare = ret.substring(0, un.length());
			} while (!compare.equals(un) && reader.hasNext());

			reader.close();
			return ret;
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
	}

	private User compactToUser(String un) {
		String readInfo = searchUsername(un);
		String info[] = readInfo.split("//");
		return new User(info[0], info[1], info[2], info[3], info[4]);
	}

	public User getUser(String un) {
		return compactToUser(un);
	}
	
	
	
	public User createNewUser(String un, String pw, String sq, String sqA) {
		
		User create = new User(un, pw, sq, sqA);
		String form = un + "//" + pw + "//" + sq + "//" + sqA + "//";
		writeToDB(form);
		return create;
	}
	
	
	private void writeToDB(String toWrite) {
		try {
			FileWriter fw = new FileWriter(database, true);
			fw.write(String.format("%n") + toWrite);
			fw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
}
