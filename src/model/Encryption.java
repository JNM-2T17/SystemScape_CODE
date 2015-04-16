package model;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import sun.misc.*;

import java.security.*;

public class Encryption {
	
//	private String toEncrypt;
	private static final byte[] keyValue = 
	        new byte[] { 'T', 'h', 'e', 'B', 'e', 's', 't',
	'S', 'e', 'c', 'r','e', 't', 'K', 'e', 'y' };

	
	public Encryption(){
	
	}
	
//	public void setToEncrypt(String toEncrypt){
//		this.toEncrypt = toEncrypt;
//	}
//	
//	public String getToEncrypt(){
//		return toEncrypt;
//	}
	
	public String encryptString(String Data) throws Exception{
		Key key = generateKey();
		
        Cipher c = Cipher.getInstance("AES");
        c.init(Cipher.ENCRYPT_MODE, key);
        byte[] encVal = c.doFinal(Data.getBytes());
        @SuppressWarnings("restriction")
		String encryptedValue = new BASE64Encoder().encode(encVal);
        return encryptedValue;
	}
	
	private static Key generateKey() throws Exception {
        Key key = new SecretKeySpec(keyValue, "AES");
        return key;
	}
	
	public String decryptString(String encryptedData) throws Exception{
		
		Key key = generateKey();
        Cipher c = Cipher.getInstance("AES");
        c.init(Cipher.DECRYPT_MODE, key);
        @SuppressWarnings("restriction")
		byte[] decordedValue = new BASE64Decoder().decodeBuffer(encryptedData);
        byte[] decValue = c.doFinal(decordedValue);
        String decryptedValue = new String(decValue);
        return decryptedValue;
		
	}

}
