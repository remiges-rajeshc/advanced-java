package com.objectapi.objectapiencrypt.util;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;


import org.springframework.stereotype.Component;


import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

@Component
public class AESUtil {


    // Method to generate AES key
    public static SecretKey generateAESKey() throws NoSuchAlgorithmException {
        KeyGenerator keyGen = KeyGenerator.getInstance("AES");
        keyGen.init(256); // Key size is 256 bits
        return keyGen.generateKey();
    }

    // Method to generate IV
    public static byte[] generateIV() {
        SecureRandom random = new SecureRandom();
        byte[] iv = new byte[16]; // IV size is 16 bytes
        random.nextBytes(iv);
        return iv;
    }


    // Method to encrypt using AES with CBC mode
    public static byte[] encryptAES_CBC(String plainText, SecretKey key, byte[] iv) throws Exception {
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE, key, new IvParameterSpec(iv));
        return cipher.doFinal(plainText.getBytes());
    }

    // Method to decrypt using AES with CBC mode
    public static String decryptAES_CBC(byte[] cipherText, SecretKey key, byte[] iv) throws Exception {
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        cipher.init(Cipher.DECRYPT_MODE, key, new IvParameterSpec(iv));
        byte[] decryptedBytes = cipher.doFinal(cipherText);
        return new String(decryptedBytes);
    }
   
}
