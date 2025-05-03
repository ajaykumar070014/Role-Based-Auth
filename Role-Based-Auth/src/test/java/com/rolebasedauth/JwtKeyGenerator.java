package com.rolebasedauth;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.util.Base64;

public class JwtKeyGenerator {

    public static void main(String[] args) throws Exception {
        // Generate HMAC-SHA512 key
        KeyGenerator keyGen = KeyGenerator.getInstance("HmacSHA512");
        keyGen.init(512); // Set key size for HS512
        SecretKey secretKey = keyGen.generateKey();

        // Convert to Base64 string
        String encodedKey = Base64.getEncoder().encodeToString(secretKey.getEncoded());

        System.out.println("Generated Secret Key (Base64):");
        System.out.println(encodedKey);
    }
}
