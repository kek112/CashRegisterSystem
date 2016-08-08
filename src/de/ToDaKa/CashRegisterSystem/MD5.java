 package de.ToDaKa.CashRegisterSystem;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
 /**
  Funktion: MD5
  @author Daniel Albrecht

  Generate a MD5 Hash from a String
  **/

public class MD5 {
     /**
      Funktion: MD5
      @author Daniel Albrecht
      @param _Input
      @return String

      **/

     public static String getMD5(String _Input) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] messageDigest = md.digest(_Input.getBytes());
            BigInteger number = new BigInteger(1, messageDigest);
            String hashtext = number.toString(16);
            while (hashtext.length() < 32) {
                hashtext = "0" + hashtext;
            }
            return hashtext;
        }
        catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }


}