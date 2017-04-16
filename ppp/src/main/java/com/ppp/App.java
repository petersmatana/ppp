package com.ppp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class App {

	static String[] alpha = {"a", "b", "c", "d", "e", "f", "g",
			"h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r",
			"s", "t", "u", "v", "w", "x", "y", "z", "1", "2", "3",
			"4", "5", "6", "7", "8", "9", "0", " "};
	static List<String> alphaList = new ArrayList<String>(Arrays.asList(alpha));

	static String[] morse = {".-", "-...", "-.-.", "-..", ".",
			"..-.", "--.", "....", "..", ".---", "-.-", ".-..",
			"--", "-.", "---", ".--.", "--.-", ".-.", "...", "-",
			"..-", "...-", ".--", "-..-", "-.--", "--..", ".----",
			"..---", "...--", "....-", ".....", "-....", "--...",
			"---..", "----.", "-----", "|"};
	static List<String> morseList = new ArrayList<String>(Arrays.asList(morse));

	static String testAlpha = "Hello World";
	static String testMorse = ".... . .-.. .-.. --- | .-- --- .-. .-.. -..";
	
	public static void _main(String[] args) {
		System.out.println(getAlphaStringToMorseString(testAlpha.toLowerCase()));
		System.out.println(getMorseStringToAlphaString(testMorse));
	}

	public static String convertAlphaCharToMorseChar(String alphaChar) {
		int position = alphaList.indexOf(alphaChar);
		return morseList.get(position);
	}
	
	public static String convertMorseCharToAlphaChar(String morseChar) {
		int position = morseList.indexOf(morseChar);
		return alphaList.get(position);
	}

	public static String getAlphaStringToMorseString(String alphaString) {
		alphaString = alphaString.toLowerCase();
		StringBuffer buffer = new StringBuffer();
		
		for (int i = 0; i < alphaString.length(); i++) {
			String tmpChar = String.valueOf(alphaString.charAt(i));
			buffer.append(convertAlphaCharToMorseChar(tmpChar) + " ");
		}
		
		return buffer.toString();
	}
	
	public static String getMorseStringToAlphaString(String morseString) {
		StringBuffer buffer = new StringBuffer();
		String[] morseStringSplit = morseString.split(" ");
		
		for (int i = 0; i < morseStringSplit.length; i++) {
			if (morseStringSplit[i].length() > 0) {
				String tmpChar = String.valueOf(morseStringSplit[i]);
				buffer.append(convertMorseCharToAlphaChar(tmpChar));
			}
		}
		
		return buffer.toString();
	}
}
