/**
 * 
 */
package com.maverik.realestate.test;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Assert;
import org.junit.Test;

import com.maverik.realestate.utils.DateFormatter;

/**
 * @author jorge
 *
 */
public class DateFormatterTest {
    
    private static DateFormat df = new SimpleDateFormat("MM/dd/yy");
    
    @Test
    public void testFormatDate() {
	DateFormatter.setFormat("MM/dd/yy");
	String todaysDate = DateFormatter.formatDate(new Date(System.currentTimeMillis()));
	Assert.assertNotNull(todaysDate);
	Assert.assertEquals(df.format(new Date(System.currentTimeMillis())), todaysDate);
    }
    
    @Test
    public void testFormatDateNullFormat() {
	DateFormatter.setFormat(null);
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm"); //this is the default format when you send a null string
	String todaysDate = DateFormatter.formatDate(new Date(System.currentTimeMillis()));
	Assert.assertNotNull(todaysDate);
	Assert.assertEquals(sdf.format(new Date(System.currentTimeMillis())), todaysDate);
    }
}
