package com.publicissapient.football.util;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.security.MessageDigest;
import java.util.Base64;
public class AesEncryptor {
    public static void main(String[] args) throws Exception {
        String rawSecret = "MySuperSecretKey!";
        String apiKey = "test";

        // Derive AES key from raw secret
        MessageDigest sha = MessageDigest.getInstance("SHA-256");
        byte[] keyBytes = sha.digest(rawSecret.getBytes("UTF-8"));
        SecretKeySpec keySpec = new SecretKeySpec(keyBytes, "AES");

        // Encrypt API key
        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE, keySpec);
        byte[] encrypted = cipher.doFinal(apiKey.getBytes("UTF-8"));

        String encryptedBase64 = Base64.getEncoder().encodeToString(encrypted);
        System.out.println("Encrypted API Key: " + encryptedBase64);
    }
}
