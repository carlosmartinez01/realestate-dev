package com.maverik.realestate.utils;

import java.security.NoSuchAlgorithmException;

import org.springframework.security.authentication.encoding.Md5PasswordEncoder;

import com.maverik.commons.encrypt.Encryption;

public class PasswordEncryption {
	
	private PasswordEncryption() {
		
	}
	
	public static String md5Password(String password) throws NoSuchAlgorithmException {
		return Encryption.md5PasswordWithSalt(password, "");
	}
	
	/**
	 * Takes a string and encrypts it to MD5 using Spring classes
	 * 
	 * @param password
	 * @return
	 */
	public static String md5Encoder(String password) {
		Md5PasswordEncoder encoder = new Md5PasswordEncoder();
		return encoder.encodePassword(password, null);
	}
	
	public static boolean isPasswordValid(String password, String rawPassword) {
		Md5PasswordEncoder encoder = new Md5PasswordEncoder();
		return encoder.isPasswordValid(password, rawPassword, null);
	}
}
