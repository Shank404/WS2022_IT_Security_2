import java.util.HashMap;

public class Main {

	public static void main(String[] args) {
//		System.out.println(hexToBinary("0123"));
//		System.out.println(hexToBase64("49276d206b696c6c696e6720796f757220627261696e206c696b65206120706f69736f6e6f7573206d757368726f6f6d"));
//		System.out.println(xorHex("1c0111001f010100061a024b53535009181c","686974207468652062756c6c277320657965"));
		
	}
	
	public static String hexToBase64(String text) {
		String value = hexToBinary(text);
		return binaryToBase64(value);
	}
	
	public static String xorHex(String text1, String text2) {
		String a = hexToBinary(text1);
		String b = hexToBinary(text2);
		return binaryToHex(xor(a,b));
	}
	
	public static String xor(String text1, String text2) {
		String result = "";
		for(int i = 0; i < text1.length(); i++) {			
			int aInt = Integer.parseInt(String.valueOf(text1.charAt(i)));
			int bInt = Integer.parseInt(String.valueOf(text2.charAt(i)));
			if(aInt + bInt == 1) {
				result += 1;
			} else {
				result += 0;
			}
		}
		return result;
	}

	public static String binaryToBase64(String text) {
		ConverterTables table = new ConverterTables();
		String result = "";
		int counter = 0;
		text = fillWithLeadingZeros(text);
		for (int i = 0; i < text.length(); i++) {
			if (counter == 6) {
				String buffer = text.substring(i, i + counter);
				result += table.binBase64.get(buffer);
				counter = 0;
			}
			counter++;
		}
		return result;
	}

	public static String binaryToHex(String text) {
		ConverterTables table = new ConverterTables();
		String result = "";
		int counter = 0;
		for (int i = 0; i <= text.length(); i++, counter++) {
			if (counter == 4) {
				String buffer = text.substring(i - counter, i);
				result += table.binHex.get(buffer);
				counter = 0;
			}
		}
		return result;
	}
	
	public static String hexToBinary(String text) {
		ConverterTables table = new ConverterTables();
		String result = "";
		for (int i = 0; i < text.length(); i++) {
			result += table.hexBin.get(text.charAt(i));
		}
		return result;
	}

	public static String deleteLeadingZeros(String text) {
		boolean found = false;
		int counter = 0;
		for (int i = 0; i < text.length() && !found; i++) {
			if (text.charAt(i) != '0') {
				counter = i;
				found = true;
			}
		}
		return "0" + text.substring(counter, text.length());
	}

	public static String fillWithLeadingZeros(String text) {
		while (text.length() % 6 != 0) {
			text = 0 + text;
		}
		return text;
	}
}
