/**
 * 
 */
package com.maverik.realestate.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author jorge
 *
 */
public class DateFormatter {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(DateFormatter.class);
	
	private static DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");
	
	private DateFormatter() {
		
	}
	
	public static void setFormat(String format) {
		LOGGER.info("Setting the format: " + format);
		String customFormat = format;
		if(format == null) {
			customFormat = "yyyy-MM-dd HH:mm";
		}
		df = new SimpleDateFormat(customFormat);
	}
	
	public static String formatDate(Date date) {
		LOGGER.info("Formatting the date: " + date);
		if(date == null) {
			return null;
		}
		
		return df.format(date);
	}

}
