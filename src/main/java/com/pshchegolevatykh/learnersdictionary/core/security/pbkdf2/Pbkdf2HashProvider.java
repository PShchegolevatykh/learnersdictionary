package com.pshchegolevatykh.learnersdictionary.core.security.pbkdf2;

import java.math.BigInteger;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.util.AbstractMap;
import java.util.Map.Entry;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.ejb.Singleton;

import com.pshchegolevatykh.learnersdictionary.core.security.HashProvider;

@Singleton
public class Pbkdf2HashProvider implements HashProvider {

    private static final String PBKDF2_ALGORITHM = "PBKDF2WithHmacSHA1";
    private static final int SALT_BYTES = 24;
    private static final int HASH_BYTES = 24;
    private static final int PBKDF2_ITERATIONS = 1000;

    public Entry<String, String> createHash(String password) throws NoSuchAlgorithmException, InvalidKeySpecException {
        return createHash(password.toCharArray());
    }

    private Entry<String, String> createHash(char[] password) throws NoSuchAlgorithmException, InvalidKeySpecException {

        // Generate a random salt
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[SALT_BYTES];
        random.nextBytes(salt);

        // Hash the password
        byte[] hash = pbkdf2(password, salt, PBKDF2_ITERATIONS, HASH_BYTES);

        return new AbstractMap.SimpleEntry<String, String>(toHex(salt), toHex(hash));
    }

    private String toHex(byte[] array) {
        BigInteger bi = new BigInteger(1, array);
        String hex = bi.toString(16);
        int paddingLength = (array.length * 2) - hex.length();
        if (paddingLength > 0) {
            return String.format("%0" + paddingLength + "d", 0) + hex;
        } else {
            return hex;
        }
    }

    private byte[] pbkdf2(char[] password, byte[] salt, int pbkdf2Iterations, int hashBytes) throws NoSuchAlgorithmException,
            InvalidKeySpecException {
        PBEKeySpec spec = new PBEKeySpec(password, salt, pbkdf2Iterations, hashBytes * 8);
        SecretKeyFactory skf = SecretKeyFactory.getInstance(PBKDF2_ALGORITHM);
        return skf.generateSecret(spec).getEncoded();
    }

    public boolean validatePassword(String password, String hexHash, String hexSalt) throws NoSuchAlgorithmException,
            InvalidKeySpecException {
        return validatePassword(password.toCharArray(), hexHash, hexSalt);
    }

    private boolean validatePassword(char[] password, String hexHash, String hexSalt) throws NoSuchAlgorithmException,
            InvalidKeySpecException {
        byte[] salt = fromHex(hexSalt);
        byte[] hash = fromHex(hexHash);
        // Compute the hash of the provided password, using the same salt,
        // iteration count, and hash length
        byte[] testHash = pbkdf2(password, salt, PBKDF2_ITERATIONS, hash.length);
        // Compare the hashes in constant time. The password is correct if
        // both hashes match.
        return slowEquals(hash, testHash);
    }

    // slow to prevent brute force attacks
    private boolean slowEquals(byte[] a, byte[] b) {
        int diff = a.length ^ b.length;
        for (int i = 0; i < a.length && i < b.length; i++)
            diff |= a[i] ^ b[i];
        return diff == 0;
    }

    private byte[] fromHex(String hex) {
        byte[] binary = new byte[hex.length() / 2];
        for (int i = 0; i < binary.length; i++) {
            binary[i] = (byte) Integer.parseInt(hex.substring(2 * i, 2 * i + 2), 16);
        }
        return binary;
    }

}