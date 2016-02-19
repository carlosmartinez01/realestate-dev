/**
 * 
 */
package com.maverik.realestate.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.maverik.realestate.utils.SendMailUtil;

/**
 * @author jorge
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:application-context.xml" })
public class SendMailTest {

    @Autowired
    private SendMailUtil client;

    @Test
    public void sendMail() throws Exception {
	boolean isSent = client.sendMail("jorgem@maverik.com", "jorgem",
		"Testing mail utility");

	org.junit.Assert.assertTrue(isSent);
    }
}
