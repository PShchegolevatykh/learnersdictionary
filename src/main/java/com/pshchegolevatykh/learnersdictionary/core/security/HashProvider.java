package com.pshchegolevatykh.learnersdictionary.core.security;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Map.Entry;

public interface HashProvider {
    Entry<String, String> createHash(String password) throws NoSuchAlgorithmException, InvalidKeySpecException;

    boolean validatePassword(String password, String hash, String salt) throws NoSuchAlgorithmException, InvalidKeySpecException;
}