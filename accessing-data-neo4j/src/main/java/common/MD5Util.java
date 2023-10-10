package common;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5Util {

    public static String encrypt(String input) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] hashBytes = md.digest(input.getBytes(StandardCharsets.UTF_8));

            // Convert the byte array to a hexadecimal representation
            BigInteger number = new BigInteger(1, hashBytes);
            StringBuilder hexString = new StringBuilder(number.toString(16));

            // Pad the hex string with leading zeros if necessary
            while (hexString.length() < 32) {
                hexString.insert(0, '0');
            }

            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("MD5 algorithm not available.", e);
        }
    }


    public static Boolean checkPassword(String password, String encryptedPassword) {
        return encrypt(password).equals(encryptedPassword);
    }

}
