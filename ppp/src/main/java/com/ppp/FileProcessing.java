package com.ppp;

import java.io.BufferedReader;
import java.io.FileReader;

public class FileProcessing {
	
	private final String space = " ";
	private final String newLine = System.getProperty("line.separator");
	private final String tab = "\t";
	
	static String[] alpha = {"a", "b", "c", "d", "e", "f", "g",
			"h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r",
			"s", "t", "u", "v", "w", "x", "y", "z"};
	
	public String loadFile(String filePath) {
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(filePath));
		    StringBuilder sb = new StringBuilder();
		    String line = br.readLine();

		    while (line != null) {
		        sb.append(line);
		        sb.append(System.lineSeparator());
		        line = br.readLine();
		    }

		    br.close();
		    return sb.toString();
		} catch (Exception ex) {
			System.out.println("chyba = " + ex.toString());
		}
		
		return null;
	}
	
	public String removeUndesirableChars(String loadText) {
		StringBuffer sb = new StringBuffer();
		int lengthLoadText = loadText.length();
		
		for (int i = 0; i < lengthLoadText; i++) {
			String tmpChar = String.valueOf(loadText.charAt(i));
			tmpChar = tmpChar.toLowerCase();
			
			/*
			if (!
				(tmpChar.equals(space)) || 
				(tmpChar.equals(newLine)) ||
				(tmpChar.equals(tab))
				) {
				sb.append(tmpChar.toLowerCase());
			}
			*/
			sb.append(goodChar(tmpChar));
			
		}
		
		return sb.toString();
	}
	
	private String goodChar(String inputChar) {
		for (String value : alpha) {
			if (inputChar.equals(value)) {
				return inputChar;
			}
		}
		return null;
	}
	
}
