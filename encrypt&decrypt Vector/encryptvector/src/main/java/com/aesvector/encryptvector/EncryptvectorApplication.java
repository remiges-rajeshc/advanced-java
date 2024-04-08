package com.aesvector.encryptvector;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.aesvector.encryptvector.encrypt.EncryptVector;

@SpringBootApplication
public class EncryptvectorApplication {

	private static final Logger logger = LoggerFactory.getLogger(EncryptvectorApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(EncryptvectorApplication.class, args);

		String secretKey = "MySecretKey";
		String salt = "MySalt";

		String originalString = "Hei, I am Rajesh Chowdhury.";

		logger.info("Using secret key: {}", secretKey);

		String encryptedString = EncryptVector.encrypt(originalString, secretKey, salt);
		if (encryptedString != null) {
			logger.info("Encrypted: {}", encryptedString);
		} else {
			logger.error("Encryption failed.");
			return;
		}

		String decryptedString = EncryptVector.decrypt(encryptedString, secretKey, salt);
		if (decryptedString != null) {
			logger.info("Decrypted: {}", decryptedString);
		} else {
			logger.error("Decryption failed.");
		}
	}
}
