package com.application.Project_Version_03_Spring_Boot.encryption;

import java.security.MessageDigest;
import java.util.Arrays;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import javax.crypto.Cipher;
import org.apache.commons.codec.binary.Base64;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import javax.crypto.NoSuchPaddingException;
import java.security.InvalidKeyException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.BadPaddingException;
import java.util.logging.Logger;
import java.util.logging.Level;

public class Encryption {

    private static final String Key = "ListenUpOasis1995";

    public static String encryptUserPassword(String decryptedUserPassword) throws UnsupportedEncodingException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
        String encryptedUserPassword = "";
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            byte[] Obj_Byte_01 = messageDigest.digest(Key.getBytes("UTF-8"));
            byte[] Obj_Byte_02 = Arrays.copyOf(Obj_Byte_01, 24);
            SecretKey secretKey = new SecretKeySpec(Obj_Byte_02, "DESede");
            Cipher cipher = Cipher.getInstance("DESede");
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);
            byte[] Obj_Byte_03 = decryptedUserPassword.getBytes("UTF-8");
            byte[] Obj_Byte_04 = cipher.doFinal(Obj_Byte_03);
            byte[] Obj_Byte_05 = Base64.encodeBase64(Obj_Byte_04);
            encryptedUserPassword = new String(Obj_Byte_05);
            // System.out.println("encryptedUserPassword: " + encryptedUserPassword);
            return encryptedUserPassword;
        } catch (UnsupportedEncodingException | NoSuchAlgorithmException | NoSuchPaddingException |
                 InvalidKeyException | IllegalBlockSizeException |
                 BadPaddingException Obj_UnsupportedEncodingException_NoSuchAlgorithmException_NoSuchPaddingException_InvalidKeyException_IllegalBlockSizeException_BadPaddingException) {
            Logger.getLogger(Encryption.class.getName()).log(Level.SEVERE, Obj_UnsupportedEncodingException_NoSuchAlgorithmException_NoSuchPaddingException_InvalidKeyException_IllegalBlockSizeException_BadPaddingException.getMessage());
        }
        return null;
    }

    public static String decryptUserPassword(String encryptedUserPassword) throws UnsupportedEncodingException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
        String decryptedUserPassword = "";
        try {
            byte[] Obj_Byte_01 = Base64.decodeBase64(encryptedUserPassword.getBytes("UTF-8"));
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            byte[] Obj_Byte_02 = messageDigest.digest(Key.getBytes("UTF-8"));
            byte[] Obj_Byte_03 = Arrays.copyOf(Obj_Byte_02, 24);
            SecretKey secretKey = new SecretKeySpec(Obj_Byte_03, "DESede");
            Cipher cipher = Cipher.getInstance("DESede");
            cipher.init(Cipher.DECRYPT_MODE, secretKey);
            byte[] Obj_Byte_04 = cipher.doFinal(Obj_Byte_01);
            decryptedUserPassword = new String(Obj_Byte_04, "UTF-8");
            // System.out.println("decryptedUserPassword: " + decryptedUserPassword);
            return decryptedUserPassword;
        } catch (UnsupportedEncodingException | NoSuchAlgorithmException | NoSuchPaddingException |
                 InvalidKeyException | IllegalBlockSizeException |
                 BadPaddingException Obj_UnsupportedEncodingException_NoSuchAlgorithmException_NoSuchPaddingException_InvalidKeyException_IllegalBlockSizeException_BadPaddingException) {
            Logger.getLogger(Encryption.class.getName()).log(Level.SEVERE, Obj_UnsupportedEncodingException_NoSuchAlgorithmException_NoSuchPaddingException_InvalidKeyException_IllegalBlockSizeException_BadPaddingException.getMessage());
        }
        return null;
    }
}