/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.mendelu.ppr.vigenere;

/**
 *
 * @author LANeo
 */
public class Decryptor {

    /**
     *
     * @param str
     * @param key
     * @return
     */
    private static String changeString(String str, String key) {
        int strLength = str.length();
        int keyLength = key.length();
        String result = "";
        for (int i = 0; i < strLength; i++) {
            result += Alphabet.shiftDown(str.charAt(i), key.charAt(i % keyLength));
        }
        return result;
    }

    private static int matchWordlist(String str, int keyLength, String filename) {
        List<map<int, int percentage = 100;
        return percentage;
    }

    private static boolean findKeyChar(String[] strArr, int keyLength) {
        return false;
    }

    /**
     *
     * @param str
     * @param keyLength
     * @return
     */
    public static String forceKey(String str, int keyLength) {
        int strLength = str.length();
        int aLength = Alphabet.alphabet.length();
        String key = "";
        String[] strDiv = new String[keyLength];
        boolean done = false;

        for (int i = 0; i < strLength; i++) {
            strDiv[i % keyLength] += String.valueOf(str.charAt(i));
        }

        for (int i = 0; i < keyLength; i++) {
            key += "a";
        }

        //  bordel
        for (int i = 0; i < aLength; i++) {

        }

        //  end of bordel
        return key;
    }

    public static String guessKey(String str, int maxKeyLength) {
        String key = "";
        return key;
    }

    public static String computeKey(String str) {
        String key = "";
        return key;
    }

    public static String Decrypt(String str, String key) {
        //rozdeleni a paralelizace
        String s = changeString(str, key);
        return s;
    }

}
