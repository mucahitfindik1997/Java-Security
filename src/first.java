import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;





public class first {
	private static final String key = "aesEncryptionKey";
	private static final String initVector = "encryptionIntVec";

	//original AES/CBC test etmek i�in kulland�m
	public static String encrypt(String value) {
		try {
			IvParameterSpec iv = new IvParameterSpec(initVector.getBytes("UTF-8"));
			SecretKeySpec skeySpec = new SecretKeySpec(key.getBytes("UTF-8"), "AES");

			Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
			cipher.init(Cipher.ENCRYPT_MODE, skeySpec, iv);

			byte[] encrypted = cipher.doFinal(value.getBytes());
			return Base64.getEncoder().encodeToString(encrypted);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}

	public static void main(String[] args) throws Exception 
	{
		//hex_tobyte byte_tohex UsinFunctiona ta��nabilir.
		//utf-8 (t�rk�e karakter verilcek mi plaintextde?)
		//ofb de online tool paddingi kullanm�yor  ben kullan�yorum.S�k�nt� olur mu?
		final String secretKey = "aesEncryptionKey";
		final String initVector = "encryptionIntVec";
	    String plaintext="password1234567d87465122das2asd2";
	    String mode="CBC";
	    
	    String[] plaintextHex=AES.initiliaze(plaintext);//padding i�lemini burda yap�yorum .Ayr�ca plaintext hex de�erlerinin 128 bit �eklinde olmas� i�in bloklar� ay�r�yorum ve bu hex listesini geri d�nd�r�yorum.
	    int numberofPiece=plaintextHex.length;//ka� tane block oldu�unu tutuyorum.
	    //b�t�n modlarda PKCS5PADDING kulland�m.
	    
	    //OFB-CBC-CTR encrypt fonksiyonlar� datan�n encrypt edilmi� halinin hex de�erlerini d�n�yor.
	    
	    String nonce="3d540021";
	    //nonce,iv datalar�n� encypt ve decrypt fonksiyonlar�na hex halini yolluyorum
	    //AES.parselEncrypt hex olarak encrypt edilmi� datay� 128 bit bloklara b�l�yor.
	    //AES.parselDecrypt String olarak d�nm�� decrypt veriyi 128 bit olarak bloklara b�l�yor //bunu kald�rabilirim.
	   if(mode.compareTo("CTR")==0) {
		    String encryptedStringCtr=CTR.encryptCTR(plaintextHex, UsingFunction.string_toHex(nonce), 0,secretKey, numberofPiece);
		    System.out.println(encryptedStringCtr);
		    System.out.println(new String( Base64.getEncoder().encode(AES.hex_tobyte(encryptedStringCtr))));	//Online toollarla do�rulu�unu kontrol edebilmek i�n encrypt fonksiyonundan d�nen hex de�erlerini base64 �eviriyorum.
		    String [] decryptedStringsCtr=AES.parselDecrypt(CTR.decryptCTR(AES.parselEncrypt(encryptedStringCtr, numberofPiece), UsingFunction.string_toHex(nonce), 0, secretKey, numberofPiece),numberofPiece);
		    
		    for (int i=0;i<numberofPiece;i++) {
		    	System.out.println(decryptedStringsCtr[i]);
		    }
	   }
	   if(mode.compareTo("OFB")==0) {
		    String encryptedStringOfb=OFB.encryptOFB(plaintextHex, UsingFunction.string_toHex(initVector), secretKey, numberofPiece);
		    
		    System.out.println(new String( Base64.getEncoder().encode(AES.hex_tobyte(encryptedStringOfb))));
		   
		    String [] decryptedStringsOfb=AES.parselDecrypt(OFB.decryptOFB(AES.parselEncrypt(encryptedStringOfb, numberofPiece), UsingFunction.string_toHex(initVector), secretKey, numberofPiece),numberofPiece);
		    for (int i=0;i<numberofPiece;i++) {
		    	System.out.println(decryptedStringsOfb[i]);
		    }
	   }
	    
	   if(mode.compareTo("CBC")==0) {
		    System.out.println(encrypt(plaintext));
		    String encryptedStringCbc=CBC.encryptCBC(plaintextHex, UsingFunction.string_toHex(initVector), secretKey, numberofPiece);
		    
		    System.out.println(new String( Base64.getEncoder().encode(AES.hex_tobyte(encryptedStringCbc))));
		   
		    String [] decryptedStringsCbc=AES.parselDecrypt(CBC.decryptCBC(AES.parselEncrypt(encryptedStringCbc, numberofPiece), UsingFunction.string_toHex(initVector), secretKey, numberofPiece),numberofPiece);
		    for (int i=0;i<numberofPiece;i++) {
		    	System.out.println(decryptedStringsCbc[i]);
		    }
	   }
	}
}

