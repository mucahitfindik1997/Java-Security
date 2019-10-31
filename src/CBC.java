import java.math.BigInteger;
import java.util.Base64;

public class CBC {
	public static String encryptCBC(String[]plaintextHex,String iv,String secretKey,int numberofPiece) {
		if(numberofPiece==0)
			return new String("");
		else {
			String input=plaintextHex[plaintextHex.length-numberofPiece];//hex
			//System.out.println(plaintextHex[0]);
		    String resultXOR=UsingFunction.xorHex(input, iv);
		    //System.out.println(resultXOR);
		    String encryptedString = AES.byte_tohex(AES.encrypt(resultXOR, secretKey)) ;
		    return encryptedString.concat(encryptCBC(plaintextHex, encryptedString, secretKey, numberofPiece-1));
		}
	}
	public static String decryptCBC(String[]encryptedStrings,String iv,String secretKey,int numberofPiece) {
		if(numberofPiece==0)
			return new String("");
		else {
			String decryptedString = AES.byte_tohex(AES.decrypt(encryptedStrings[encryptedStrings.length-numberofPiece], secretKey));
	    	String xorBack_Hex=UsingFunction.xorHex(decryptedString, iv);
	    	
	    	if(numberofPiece==1) {
	    		xorBack_Hex=AES.paddingdecode(xorBack_Hex);
	    	}
	    	String string_xorBack=UsingFunction.hex_toString(xorBack_Hex);
		    return string_xorBack.concat(decryptCBC(encryptedStrings, encryptedStrings[encryptedStrings.length-numberofPiece], secretKey, numberofPiece-1));
		}
	}
}
//150f10010e1f060d676641667c5e6d6b