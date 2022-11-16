package application.model;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TextDB_Handler implements DBHandler {
	// singleton essentials
	private static TextDB_Handler singleInstance = new TextDB_Handler();

	private TextDB_Handler() {
	};

	public static TextDB_Handler getSingle() {
		return singleInstance;
	}

	private File database = new File("UserDB.txt");

	// fetch info from database based on username
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
			System.out.println("could not find user");
			return "";
		}
	}

	// compact database info to a User
	private User compactToUser(String un) {
		String readInfo = searchUsername(un);
		String[] info = readInfo.split("//");

		if (info.length == 5)
			return new User(info[0], info[1], info[2], info[3], info[4]);
		else
			return new User(info[0], info[1], info[2], info[3]);
	}

	public User getUser(String un) {
		return compactToUser(un);
	}

	// public append new user and info
	// todo: may change parameters to be more flexible
	public User createNewUser(String un, String pw, String sq, String sqA) {
		User create = new User(un, pw, sq, sqA);
		writeNewUser(convert(create));
		return create;
	}

	public void updateUser(User u, String pw) {
		u.setPassword(pw);
		overwriteDB(u);
	}

	// convert User info to database format
	private String convert(User u) {
		String ret = u.getUsername() + "//" + u.getPassword() + "//" + u.getSecurityQuestion() + "//"
				+ u.getSecurityQuestionAnswer() + "//";
		for (Course c : u.getCourses())
			ret += (c.getName() + ",");
		return ret;
	}

	// append info string to database
	private void writeNewUser(String toWrite) {
		try {
			FileWriter fw = new FileWriter(database, true);
			fw.write(String.format("%n") + toWrite);
			fw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	void overwriteDB(User u) {
		overwrite(u);
	}

	// update user u information
	private void overwrite(User u) {
		try {
			// initialize vars
			List<String> temporary = new ArrayList<>();
			Scanner reader = new Scanner(database);
			String userToFind = u.getUsername();
			int substringLength = userToFind.length();
			String read = reader.nextLine();
			String compare = read.substring(0, substringLength);

			// add all users to not write over and skip over the user to overwrite
			while (reader.hasNext() && !compare.equals(userToFind)) {
				temporary.add(read);
				read = reader.nextLine();
				compare = read.substring(0, substringLength);
			}

			// found user to overwrite; add everyone else
			while (reader.hasNext())
				temporary.add(reader.nextLine());

			reader.close();

			// newest changes pushed to top
			FileWriter fw = new FileWriter(database);
			fw.write(convert(u));
			for (String i : temporary)
				fw.write(String.format("%n") + i);

			fw.close();

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
