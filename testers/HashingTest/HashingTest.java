// Code based on: https://www.baeldung.com/sha-256-hashing-java

import java.security.*;

public class HashingTest {
    public static MessageDigest md;
    public static String message = "password";
    public static void main(String[] args) {
        try {
            md = MessageDigest.getInstance("SHA-256");
            byte[] encodedHash = md.digest(message.getBytes());
            String s = new String(encodedHash);
            System.out.println(s);

            System.out.println(bytesToHex(encodedHash));
        }
        catch (Exception ex) {

        }
    }

    public static StringBuilder bytesToHex(byte[] hash) {
        StringBuilder stringHex = new StringBuilder(2 * hash.length);
        String hex;
        for (Byte b : hash) {
            hex = Integer.toHexString(0xff & b);
            if (hex.length() == 1) {
                stringHex.append('0');
            }
            stringHex.append(hex);
        }
        return stringHex;
    }
}