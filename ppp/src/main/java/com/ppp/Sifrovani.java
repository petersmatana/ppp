package com.ppp;

public class Sifrovani {

	private static String alphabet = "abcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyza";
	private static String key = "ak";
	// private static String text = "this is text";
	private static String text = "abcdefghijklmnopqrstuvwxyz";
	private static String text2 = "zyxwvutsrqponmlkjihgfedcbaabcdefghijklmnopqrst";
	
	public static void main(String[] args) {
		
		//FileProcessing fp = new FileProcessing();
		//String x = fp.loadFile("/home/smonty/Dropbox/zz_skola/4semestr/paralelko/projekt_repo/data/small_file");
		//System.out.println(fp.removeUndesirableChars(x));
		//fp.splitText();
		
		
		// System.out.println(text2.length());
		
		// fp.splitText(text, 7);
		//fp.splitText(text2, 7);
		
		
		/*
		String asd = "a d ad a d";
		for (int i = 0; i < asd.length(); i++) {
			String fuck = String.valueOf(asd.charAt(i));
			System.out.print(fuck);
			if (fuck.equals("a")) {
				System.out.print("fuuu");
			} else {
				System.out.print("_");
			}
			System.out.println();
		}
		*/
		
		
		go();
		
		/*
		char znak = 'a';
		System.out.println((int) znak);
		*/
	}
	
	private static void go() {
		int textLength = text.length();
		int mod = key.length();
		
		for (int i = 0; i < textLength; i++) {
			// vypocitam si jaky znak z klice je narade
			int index = (i) % mod;
			//System.out.println(index);
			
			char textChar = text.charAt(i);
			char textKey = key.charAt(index);
			
			int newChar = (int) textKey + (int) textChar - (2 * 97);
			
			System.out.print(String.valueOf(textChar) + "(" + (((int) textChar) - 97) + ")" + " - ");
			System.out.print(String.valueOf(textKey) + "(" + (((int) textKey) - 97) + ")" + " -> ");
			System.out.print(alphabet.charAt(newChar) + "(" + (int) textKey + ")");
			
			System.out.println();
		}
	}
	
}
