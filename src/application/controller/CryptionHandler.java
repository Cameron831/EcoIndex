package application.controller;

import edu.sjsu.yazdankhah.crypto.util.PassUtil;

class CryptionHandler {
	private PassUtil passUtil = new PassUtil();

	String encrypt(String toEncrypt) {
		return passUtil.encrypt(toEncrypt);
	}

	String decrypt(String toDecrypt) {
		return passUtil.decrypt(toDecrypt);
	}
}
