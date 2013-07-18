package com.pshchegolevatykh.learnersdictionary.core.security;

import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Map.Entry;

import org.junit.BeforeClass;
import org.junit.Test;

import com.pshchegolevatykh.learnersdictionary.core.security.pbkdf2.Pbkdf2HashProvider;

public class Pbkdf2HashProviderTest {
    private static HashProvider hashProvider;

    @BeforeClass
    public static void setUp() {
	hashProvider = new Pbkdf2HashProvider();
    }

    @Test
    public void createHash_RunTwoTimes_CreatesDifferentSalts() {
	String password = "bluesky";
	try {
	    Entry<String, String> firstEntry = hashProvider
		    .createHash(password);
	    Entry<String, String> secondEntry = hashProvider
		    .createHash(password);
	    assertNotEquals(firstEntry.getKey(), secondEntry.getKey());
	} catch (NoSuchAlgorithmException e) {
	    e.printStackTrace();
	} catch (InvalidKeySpecException e) {
	    e.printStackTrace();
	}
    }

    @Test
    public void createHash_RunTwoTimes_CreateDifferentHashes() {
	String password = "bluesky";
	try {
	    Entry<String, String> firstEntry = hashProvider
		    .createHash(password);
	    Entry<String, String> secondEntry = hashProvider
		    .createHash(password);
	    assertNotEquals(firstEntry.getValue(), secondEntry.getValue());
	} catch (NoSuchAlgorithmException e) {
	    e.printStackTrace();
	} catch (InvalidKeySpecException e) {
	    e.printStackTrace();
	}
    }

    @Test
    public void validatePassword_WrongPassword_ReturnsFalse() {
	String goodPassword = "bluesky";
	String wrongPassword = "redsky";
	try {
	    Entry<String, String> hashEntry = hashProvider
		    .createHash(goodPassword);
	    assertFalse(hashProvider.validatePassword(wrongPassword,
		    hashEntry.getValue(), hashEntry.getKey()));
	} catch (NoSuchAlgorithmException e) {
	    e.printStackTrace();
	} catch (InvalidKeySpecException e) {
	    e.printStackTrace();
	}
    }

    @Test
    public void validatePassword_GoodPassword_ReturnsTrue() {
	String goodPassword = "bluesky";
	try {
	    Entry<String, String> hashEntry = hashProvider
		    .createHash(goodPassword);
	    assertTrue(hashProvider.validatePassword(goodPassword,
		    hashEntry.getValue(), hashEntry.getKey()));
	} catch (NoSuchAlgorithmException e) {
	    e.printStackTrace();
	} catch (InvalidKeySpecException e) {
	    e.printStackTrace();
	}
    }
}