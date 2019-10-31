
public class OFB {
	public static String encryptOFB(String[]plaintextHex,String iv,String secretKey,int numberofPiece) {
		if(numberofPiece==0)
			return new String("");
		else {
			String encryptedString = AES.byte_tohex(AES.encrypt(iv, secretKey));
		    String cipher=UsingFunction.xorHex(encryptedString,plaintextHex[plaintextHex.length-numberofPiece]);
		    return cipher.concat(encryptOFB(plaintextHex, encryptedString, secretKey, numberofPiece-1));
		}
	}
	public static String decryptOFB(String[]encryptedStrings,String iv,String secretKey,int numberofPiece) {
		if(numberofPiece==0)
			return new String("");
		else {
			String decryptedString = AES.byte_tohex(AES.encrypt(iv, secretKey));
			String plainHex=UsingFunction.xorHex(decryptedString,encryptedStrings[encryptedStrings.length-numberofPiece]);
			if(numberofPiece==1) {
				
	    		plainHex=AES.paddingdecode(plainHex);
	    	}
			String plain=UsingFunction.hex_toString(plainHex);
		    return plain.concat(decryptOFB(encryptedStrings, decryptedString, secretKey, numberofPiece-1));
		}
	}
}
