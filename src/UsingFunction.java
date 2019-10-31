
public class UsingFunction {
	public static String hex_toString(String hexStr) {
		StringBuilder output = new StringBuilder("");
	     
	    for (int i = 0; i < hexStr.length(); i += 2) {
	        String str = hexStr.substring(i, i + 2);
	        output.append((char) Integer.parseInt(str, 16));
	    }
	    return output.toString();
	}
	public static String string_toHex(String st) {
		char[] chars = st.toCharArray();	
	    StringBuilder hex = new StringBuilder();
	    for (char ch : chars) {
	    	
	    	if(Integer.toHexString((int) ch).length()==1) {
	    		hex.append(new String("0").concat(Integer.toHexString((int) ch)));
	    	}
	    	else {
	    		hex.append(Integer.toHexString((int) ch));
	    	}
	    	
	        
	    }
	    return hex.toString();
	}

	public static String xorHex(String a, String b) {
	    // TODO: Validation
	    char[] chars = new char[a.length()];
	    for (int i = 0; i < chars.length; i++) {
	        chars[i] = toHex(fromHex(a.charAt(i)) ^ fromHex(b.charAt(i)));
	    }
	    return new String(chars);
	}

	private static int fromHex(char c) {
	    if (c >= '0' && c <= '9') {
	        return c - '0';
	    }
	    if (c >= 'A' && c <= 'F') {
	        return c - 'A' + 10;
	    }
	    if (c >= 'a' && c <= 'f') {
	        return c - 'a' + 10;
	    }
	    throw new IllegalArgumentException();
	}

	private static char toHex(int nybble) {
	    if (nybble < 0 || nybble > 15) {
	        throw new IllegalArgumentException();
	    }
	    return "0123456789abcdef".charAt(nybble);
	}
	
	
}
