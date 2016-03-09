package com.maverik.realestate.utils;

import java.io.StringWriter;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class RandomPasswordGenerator {	
	
	private static final Logger LOGGER = LoggerFactory.getLogger(RandomPasswordGenerator.class);
	
	private static final List<Integer> VALID_PWD_CHARS = new ArrayList<>();
	
	private RandomPasswordGenerator() {
		
	}
	
	static {
	    IntStream.rangeClosed('0', '9').forEach(VALID_PWD_CHARS::add);    // 0-9
	    IntStream.rangeClosed('A', 'Z').forEach(VALID_PWD_CHARS::add);    // A-Z
	    IntStream.rangeClosed('a', 'z').forEach(VALID_PWD_CHARS::add);    // a-z
	    //IntStream.rangeClosed('!', '*').forEach(VALID_PWD_CHARS::add);    // !-*
	}
	
	
	/**
	 * This method works only with Java 1.8 and above
	 * 
	 * @return String
	 */
	public static String generateKey() {
		LOGGER.info("Generate Random Password");
		
		int passwordLength = 10;
		
		SecureRandom key = new SecureRandom();
		IntStream result = key.ints(passwordLength, 0, VALID_PWD_CHARS.size()).map(VALID_PWD_CHARS::get);
		
		return result.collect(StringWriter::new, StringWriter::write, (swl, swr) -> swl.write(swr.toString())).toString();
	}
}
