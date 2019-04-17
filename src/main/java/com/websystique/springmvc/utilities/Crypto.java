package com.websystique.springmvc.utilities;

import java.security.MessageDigest;
import java.util.Arrays;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import org.apache.commons.codec.binary.Base64;

public class Crypto {
	
	final String secretKey = "blackcat13";
	String base64EncryptedString = "";
	String base64DecryptedString = "";
	
	public String encrypt(final String texto) {
        try {
        	 
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] digestOfPassword = md.digest(secretKey.getBytes("utf-8"));
            byte[] keyBytes = Arrays.copyOf(digestOfPassword, 24);
 
            SecretKey key = new SecretKeySpec(keyBytes, "DESede");
            Cipher cipher = Cipher.getInstance("DESede");
            cipher.init(Cipher.ENCRYPT_MODE, key);
 
            byte[] plainTextBytes = texto.getBytes("utf-8");
            byte[] buf = cipher.doFinal(plainTextBytes);
            byte[] base64Bytes = Base64.encodeBase64(buf);
            base64EncryptedString = new String(base64Bytes);
 
        } catch (Exception ex) {
        }
        return base64EncryptedString;
    }
	
	public String decrypt(final String textoEncriptado) {
		try {
            byte[] message = Base64.decodeBase64(textoEncriptado.getBytes("utf-8"));
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] digestOfPassword = md.digest(secretKey.getBytes("utf-8"));
            byte[] keyBytes = Arrays.copyOf(digestOfPassword, 24);
            SecretKey key = new SecretKeySpec(keyBytes, "DESede");
 
            Cipher decipher = Cipher.getInstance("DESede");
            decipher.init(Cipher.DECRYPT_MODE, key);
 
            byte[] plainText = decipher.doFinal(message);
 
            base64DecryptedString = new String(plainText, "UTF-8");
 
        } catch (Exception ex) {
        }
        return base64DecryptedString;
	}
	
	/*public static void main(final String[] args) {
		        final Crypto crypto = new Crypto();
		        final String encryptedData = crypto.encrypt("This is a test message.");
		        System.out.println(encryptedData);
		        final String decryptedData = crypto.decrypt(encryptedData);
		        System.out.println(decryptedData);
		    }*/

	
}
