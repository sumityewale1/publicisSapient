package com.publicissapient.football.util;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.Base64;
import java.util.Objects;

/**
 * Utility class for AES decryption.
 * Note: This implementation uses ECB mode, which is not recommended for production cryptography.
 */
public final class AESUtil {

    private AESUtil() {
        // Utility class
    }

    /**
     * Decrypts a Base64-encoded, AES-encrypted string using the provided secret.
     *
     * @param encrypted the Base64-encoded string to decrypt
     * @param rawSecret the secret key to use for decryption
     * @return the decrypted plaintext string
     * @throws IllegalArgumentException if input values are invalid
     * @throws RuntimeException for any decryption errors
     */
    public static String decrypt(String encrypted, String rawSecret) {
        if (Objects.isNull(encrypted) || encrypted.isEmpty()) {
            throw new IllegalArgumentException("Encrypted string must not be null or empty");
        }
        if (Objects.isNull(rawSecret) || rawSecret.isEmpty()) {
            throw new IllegalArgumentException("Raw secret must not be null or empty");
        }
        try {
            // Derive key
            MessageDigest sha = MessageDigest.getInstance("SHA-256");
            byte[] keyBytes = sha.digest(rawSecret.getBytes(StandardCharsets.UTF_8));
            SecretKeySpec keySpec = new SecretKeySpec(keyBytes, "AES");

            // Decrypt
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding"); // Consider using CBC in production
            cipher.init(Cipher.DECRYPT_MODE, keySpec);

            byte[] decoded = Base64.getDecoder().decode(encrypted);
            byte[] decrypted = cipher.doFinal(decoded);

            return new String(decrypted, StandardCharsets.UTF_8);
        } catch (Exception e) {
            throw new RuntimeException("Error while decrypting API key: " + e.getMessage(), e);
        }
    }
}
