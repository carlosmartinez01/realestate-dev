package com.maverik.realestate.utils;

import java.text.MessageFormat;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import com.maverik.commons.mail.SendEmail;

@Component
public class SendMailUtil {

    private static final Logger LOGGER = LoggerFactory
	    .getLogger(SendMailUtil.class);

    @Autowired
    private Environment env;

    public boolean sendMail(String toAddress, String username,
	    String information) {
	LOGGER.info("Sending email to {}", toAddress);

	return SendEmail.sendMail(new String[] { toAddress },
		env.getProperty("email.subject"),
		generateBodyMessage(username, information),
		env.getProperty("email.fromAddress"));

    }

    private String generateBodyMessage(String username, String information) {

	return MessageFormat.format(env.getProperty("email.body.message"),
		username, information);
    }
}
