/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.mendelu.ppr.vigenere;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author LANeo
 */
public class Frequency {

    private static String lang = "EN";
    private static final Map<String, double[]> freq;

    static {
        freq = new HashMap<>();
        double[] en = {8.167, 1.492, 2.782, 4.253, 12.702, 2.228,
            2.015, 6.094, 6.966, 0.153, 0.772, 4.025, 2.406, 6.749, 7.507,
            1.929, 0.095, 5.987, 6.327, 9.056, 2.758, 0.978, 2.360, 0.150,
            1.974, 0.074};
        freq.put("EN", en);

        double[] cz = {8.3569, 1.5402, 3.6835, 3.5822, 10.5516, 0.2700,
            0.2697, 2.4138, 7.5345, 2.0949, 3.6935, 3.7979, 3.1894, 6.5401, 8.5970,
            3.3732, 0.0013, 4.8567, 5.2596, 5.7026, 3.8966, 4.6076, 0.0087, 0.0746,
            2.9469, 3.1569};
        freq.put("CZ", cz);
        
        if(!SetLang(Engine.lang)){
            lang = "EN";
        }
    }

    /**
     * Method for setting correct language in the environment manually.
     *
     * @param language A two letter abbreviation.
     * @return True on success, false on failure.
     */
    public static boolean SetLang(String language) {
        boolean set = true;
        if (language.equals("EN") || language.equals("CZ")) {
            lang = language;
        } else {
            set = false;
        }
        return set;
    }

    /**
     * Computes a score, which is sum of squares from the difference of normal
     * character frequency and character frequency in given text.
     *
     * @param data Text to be evaluated.
     * @return A percentage of diffrenetiation from standard. 0 is best match.
     */
    public static double score(String data) {
        int[] letters = new int[26];   //compute histogram of a-z frequency:

        for (char c : data.toLowerCase().replaceAll("[^a-z]", "").toCharArray()) {
            letters[c - 'a']++;
        }

        double sumDSquared = 0.0;
        for (int j = 0; j < freq.get(lang).length; j++) {
            sumDSquared += Math.pow(
                    (100.0 * letters[j] / data.length() - freq.get(lang)[j]),
                    2);
        }
        return sumDSquared;
    }

    /**
     * This method compares the character frequency differentiation squares and
     * returns the one with lesser result.
     *
     * @param a First string.
     * @param b Second string.
     * @return String with lesser quadrat of freaquency differentiation.
     */
    public static String min(String a, String b) {
        return a == null || (b != null && score(a) > score(b)) ? b : a;
    }

    /**
     * A Caesar cipher decoder. It runs a frequency analysis for each character
     * used for decryption. It allows the user to try only certain range, which
     * can only be one letter and thus merely translates it. For that purpose
     * there is {@link Decryptor#KeyDecrypt(java.lang.String, java.lang.String)
     * }
     *
     * @param msg
     * @param minChar
     * @param maxChar
     * @return
     */
    public static String DeCaesar(String msg, char minChar, char maxChar) {
        String best = null;
        for (int i = minChar; i <= maxChar; i++) {
            best = min(best, Encryptor.Encrypt(msg, "" + (char) (i)));
        }
        return best;
    }
}
