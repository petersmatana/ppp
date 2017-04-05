package com.ppp;

public class Sifrovani {

	private static String alphabet = "abcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyz";
	private static String key = "key";
	// private static String text = "this is text";
	// private static String text = "abcdefghijklmnopqrstuvwxyz";
	private static String text = "zyxwvutsrqponmlkjihgfedcba";
	
	public static void main(String[] args) {
		int textLength = text.length();
		int mod = 3;
		
		for (int i = 0; i < textLength; i++) {
			// vypocitam si jaky znak z klice je narade
			int index = (i) % mod;
			
			char textChar = text.charAt(i);
			char textKey = key.charAt(index);
			
			int newChar = (int) textKey + (int) textChar - (2 * 95);
			
			System.out.print(String.valueOf(textChar) + "(" + (((int) textChar) - 97) + ")" + " - ");
			System.out.print(String.valueOf(textKey) + "(" + (((int) textKey) - 97) + ")" + " -> ");
			System.out.print(alphabet.charAt(newChar));
			
			System.out.println();
		}
	}
	
}
