package com.ppp;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.LinkedList;
import java.util.List;

public class FileProcessing {

	static String[] alpha = { "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r",
			"s", "t", "u", "v", "w", "x", "y", "z" };

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

	/**
	 * Removing undesirable characters.
	 */
	public String removeUndesirableChars(String loadText) {
		StringBuffer sb = new StringBuffer();
		int lengthLoadText = loadText.length();

		for (int i = 0; i < lengthLoadText; i++) {
			String tmpChar = String.valueOf(loadText.charAt(i));
			tmpChar = tmpChar.toLowerCase();

			String toAppend = goodChar(tmpChar);
			if (toAppend != null) {
				sb.append(toAppend);
			}

		}

		return sb.toString();
	}

	/**
	 * Check if character is alphabetic or not. Alphabetic characters need but
	 * other characters like white space, dot or question mark do not need.
	 */
	private String goodChar(String inputChar) {
		for (String value : alpha) {
			if (inputChar.equals(value)) {
				return inputChar;
			}
		}
		return null;
	}

	/**
	 * Split text into small buckets.
	 * */
	public List<String> splitText(String text, int numberOfSplits) {
		int chars_in_buckets = text.length() / numberOfSplits;
		int tmp = 1;
		StringBuffer sb = new StringBuffer();
		List<String> list = new LinkedList<String>();
		
		for (int i = 0; i < text.length(); i++) {
			if (tmp != chars_in_buckets) {
				sb.append(text.charAt(i));
				tmp += 1;
			} else {
				list.add(sb.toString());
				sb = new StringBuffer();
				sb.append(text.charAt(i));
				tmp = 1;
			}
		}

		if (sb.toString().length() > 0) {
			list.add(sb.toString());
		}
		
		return list;
	}

}
