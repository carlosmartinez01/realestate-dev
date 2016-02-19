/**
 * 
 */
package com.maverik.realestate.test;

import java.security.NoSuchAlgorithmException;

import org.junit.Assert;
import org.junit.Test;

import com.maverik.realestate.utils.PasswordEncryption;

/**
 * @author jorge
 *
 */
public class PasswordEncryptionTest {
    
    private static String hashPassword = "275876e34cf609db118f3d84b799a790"; //dummy

    @Test
    public void testMD5Password() throws NoSuchAlgorithmException {
	String newHashPassword = null;
	newHashPassword = PasswordEncryption.md5Password("dummy");
	Assert.assertNotNull(newHashPassword);
	Assert.assertEquals(hashPassword, newHashPassword);
    }
    
    @Test
    public void testIsValidPassword() throws NoSuchAlgorithmException {
	boolean isValid = false;
	isValid = PasswordEncryption.isPasswordValid(hashPassword, "dummy");
	Assert.assertTrue(isValid);
    }
}
