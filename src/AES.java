
 
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
 
public class AES {
 
    private static SecretKeySpec secretKey;

    public static byte [] encrypt(String strToEncrypt, String secret) 
    {
        try
        {
        	SecretKeySpec secretKey = new SecretKeySpec(secret.getBytes("UTF-8"), "AES");
            Cipher cipher = Cipher.getInstance("AES/ECB/NOPADDING");	
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);
            byte []bPlain=hex_tobyte(strToEncrypt);
            byte []b=cipher.doFinal(bPlain);
            return b;
        } 
        catch (Exception e) 
        {
            System.out.println("Error while encrypting: " + e.toString());
        }
        return null;
    }
    private static final char[] DIGITS
    = {'0', '1', '2', '3', '4', '5', '6', '7',
        '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};

	public static final String byte_tohex(byte[] data) {
	final StringBuffer sb = new StringBuffer(data.length * 2);
	for (int i = 0; i < data.length; i++) {
	    sb.append(DIGITS[(data[i] >>> 4) & 0x0F]);
	    sb.append(DIGITS[data[i] & 0x0F]);
	}
	return sb.toString();
	}
 
    public static byte [] decrypt(String strToDecrypt, String secret) 
    {
        try
        {
        	SecretKeySpec secretKey = new SecretKeySpec(secret.getBytes("UTF-8"), "AES");
            Cipher cipher = Cipher.getInstance("AES/ECB/NOPADDING");
            cipher.init(Cipher.DECRYPT_MODE, secretKey);
            byte [] bCipher=hex_tobyte(strToDecrypt);
            byte [] b=cipher.doFinal(bCipher);
            return b;
        } 
        catch (Exception e) 
        {
            System.out.println("Error while decrypting: " + e.toString());
        }
        return null;
    }
	public static String[] parselEncrypt(String encryptedString,int numberofPiece) {
		String []encryptedStrings=new String[numberofPiece];
		for(int i=0; i<numberofPiece;i++) {
	    	encryptedStrings[i]=encryptedString.substring(i*32,(i+1)*32);
	    }
		return encryptedStrings;
	}
	public static String[] parselDecrypt(String decryptedString,int numberofPiece) {
		String[] decryptedStrings=new String[numberofPiece];
		for(int i=0; i<numberofPiece;i++) {
			if(i==numberofPiece-1)
				decryptedStrings[i]=decryptedString.substring(i*16,decryptedString.length());
			else
				decryptedStrings[i]=decryptedString.substring(i*16,(i+1)*16);
	    }
		return decryptedStrings;
	}
	public static String paddingdecode(String hexPlain) {
		int c1=Character.getNumericValue(hexPlain.charAt(30));
		int c2=Character.getNumericValue(hexPlain.charAt(31));
		if(c1==1)
			return "";
		return hexPlain.subSequence(0, 32-(c2*2)).toString();
	}
    public static String padding(String plaintext) {
		String hexStrs=UsingFunction.string_toHex(plaintext);
		int paddingLength=(32-(hexStrs.length()%32))/2;
		if(paddingLength==0)
			return hexStrs;
		if((paddingLength*2)==32) {
			for(int i=0;i<paddingLength;i++) {
				hexStrs=hexStrs.concat(Integer.toHexString(paddingLength));
			}
		}
		else {
			for(int i=0;i<paddingLength;i++) {
				hexStrs=hexStrs.concat(new String("0").concat(Integer.toHexString(paddingLength)));
			}
		}
		return hexStrs;
	}
    public static String [] initiliaze(String plaintext){
		String hexStr=AES.padding(plaintext);
	    int numberofPiece=hexStr.length()/32;
	    String[] plaintextHex=new String[numberofPiece];
	    for(int i=0; i<numberofPiece;i++) {
	    	plaintextHex[i]=hexStr.substring(i*32,(i+1)*32);
	    }
	    return plaintextHex;
	}
    public static byte [] hex_tobyte(String str) {
    	byte[] val = new byte[str.length() / 2];
    	for (int i = 0; i < val.length; i++) {
    		   int index = i * 2;
    		   int j = Integer.parseInt(str.substring(index, index + 2), 16);
    		   val[i] = (byte) j;
    		}
    	return val;
    }
}