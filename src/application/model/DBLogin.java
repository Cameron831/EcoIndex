package application.model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class DBLogin implements DBHandler {
	// singleton essentials
	private static DBLogin singleInstance = new DBLogin();

	private DBLogin() {};

	public static DBLogin getSingle() {
		return singleInstance;
	}
	
	private File database = new File("UserDB.txt");
	
	// make sure to set private
	private String readDB() {
		
		// TODO have to search through for correct username
		try {
			FileReader fRead = new FileReader(database);
			BufferedReader bRead = new BufferedReader(fRead);
			String test = bRead.readLine();
			bRead.close();
			fRead.close();
			System.out.println(test);
			return test;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "";
		}
	}
	
	private User compactToUser() {
		String readInfo = readDB();
		String info[] = readInfo.split("//");
		return new User(info[0],info[1],info[2],info[3]);
	}
	
	public User getUser() {
		return compactToUser();
	}
}
