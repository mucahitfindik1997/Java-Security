
public class CTR {
	public static String encryptCTR(String[]plaintextHex,String nonce,int ctr,String secretKey,int numberofPiece) {
		if(numberofPiece==0)
			return new String("");
		else {
			String nonce_ctr=CTR.nonce_CTR(nonce, ctr);
			String encryptedString = AES.byte_tohex(AES.encrypt(nonce_ctr, secretKey));
		    String cipher=UsingFunction.xorHex(encryptedString,plaintextHex[plaintextHex.length-numberofPiece]);
		    return cipher.concat(encryptCTR(plaintextHex, nonce,ctr+1 , secretKey, numberofPiece-1));
		}
	}
	public static String decryptCTR(String[]encryptedStrings,String nonce,int ctr,String secretKey,int numberofPiece) {
		if(numberofPiece==0)
			return new String("");
		else {
			String nonce_ctr=CTR.nonce_CTR(nonce, ctr);
			String decryptedString = AES.byte_tohex(AES.encrypt(nonce_ctr, secretKey));
			String plainHex=UsingFunction.xorHex(decryptedString,encryptedStrings[encryptedStrings.length-numberofPiece]);
			if(numberofPiece==1) {//son blockdaki bulunan padding kýsmýný silmek için
				
	    		plainHex=AES.paddingdecode(plainHex);
	    	}
			String plain=UsingFunction.hex_toString(plainHex);
		    return plain.concat(decryptCTR(encryptedStrings, nonce,ctr+1, secretKey, numberofPiece-1));
		}
	}
	public static String nonce_CTR(String nonce,int ctr) {
		String hexCtr=Integer.toHexString(ctr);
		String zeros="";
		for(int i=0;i<(16-hexCtr.length())/2;i++) {
			zeros=new String("00").concat(zeros);
		}
		if((16-hexCtr.length())%2==1)
			zeros=new String("0").concat(zeros);
			
		hexCtr=zeros.concat(hexCtr);
		return nonce.concat(hexCtr);
		
	}
}
