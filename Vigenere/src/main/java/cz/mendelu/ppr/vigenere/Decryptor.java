/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.mendelu.ppr.vigenere;

import java.util.List;

/**
 * This class serves to decrypt given text, which is encrypted by Vigenere
 * cipher. This text could be decrypted with key by method
 * {@link #Decrypt1(String, String) Decrypt1} or forced by method
 * {@link #ForceKey(String, String) Decrypt1}
 *
 * @author LANeo
 */
public class Decryptor {

    /**
     * This method decrypts a text with supplied key. It does not check the
     * correctness of the decryption in any means.
     *
     * @param encrypted String to be decrypted.
     * @param key Key to be used for decryption.
     * @return Decrypted string.
     */
    public static String KeyDecrypt(String encrypted, String key) {
        int strLength = encrypted.length();
        int keyLength = key.length();
        String result = "";
        for (int i = 0; i < strLength; i++) {
            result += Alphabet.shiftDown(encrypted.charAt(i), key.charAt(i % keyLength));
        }
        return result;
    }

    /**
     * This method uses a frequency analysis of characters for decryption. Every
     * language has its own character occurance and it is stored in
     * {@link Frequency#freq}. To select another than default EN alphabet, use
     * {@link Frequency#SetLang(java.lang.String)}. This operation should happen
     * automatically.
     * 
     * Decryption is made by decoding a Caesar cipher on corresponding
     * characters by a key character. The key starts at "a" and then goes up.
     * No key assembling is done here. To do that, use {@link KeyMaster}.
     *
     * @param encrypted String to be decrypted.
     * @param maxKeyLen Maximal key length.
     * @return Decrypted string.
     */
    public static String FreqDecrypt(String encrypted, int maxKeyLen) {
        String best = null;
        for (int keyLen = 1; keyLen <= maxKeyLen; keyLen++) {
            String[] divided = Stringer.BrushString(encrypted, keyLen);
            String[] decrypted = new String[keyLen];
            for (int j = 0; j < keyLen; j++) {
                decrypted[j] = Frequency.DeCaesar(divided[j], 'a', 'z');
            }
            String combined = Stringer.MergeString(decrypted);
            best = Frequency.min(best, combined);
        }
        return best;
    }

    /**
     * This method decrypts a text by matching the decrypted text
     * with a dictionary. This is so called <b>"brute-force"</b> method.
     *
     * @param encrypted String to be decrypted.
     * @param maxKeyLen Maximal key length.
     * @return Decrypted string.
     */
    public static String DictDecrypt(String encrypted, int maxKeyLen) {
        float match = 0;
        String decrypted = "";
        String key = "a";

        while ((match < 90) && (key.length() <= maxKeyLen)) {
            decrypted = KeyDecrypt(encrypted, key);
            match = Dictionary.MatchVocabulary(decrypted);
            key = KeyMaster.ShiftKeyUp(key);
        }

        if (match > 90) {
            return decrypted;
        } else {
            return "";
        }
    }

    /**
     * @deprecated No longer viable idea. it is nonsense.
     *
     * @param encrypted
     * @param keyLength
     * @return
     */
    public static String KeyLenDecrypt(String encrypted, int keyLength) {
        String key = KeyMaster.GenerateKey('a', keyLength);
        String decrypted = "";
        float match = 0;
        while ((match < 90) && (key.length() == keyLength)) {
            decrypted = KeyDecrypt(encrypted, key);
            match = Dictionary.MatchVocabulary(decrypted);
            key = KeyMaster.ShiftKeyUp(key);
        }
        if (match >= 90) {
            return decrypted;
        } else {
            return "";
        }
    }

    // TESTOVACI METODA BEHU!!! nepouzivat k ostre funkci. volana na konci engine main
    public static void testit() {
        String key = "password";
        double match = 0;
        int maxKeyLength = 10;
        String s = "When in the course of human events it becomes necessary for one people to dissolve the political bands which have connected them with another and to assume among the powers of the earth the separate and equal station to which the laws of Nature and of Nature's God entitle them, a decent respect to the opinions of mankind requires that they declare the causes of their separation.";
        String e = Encryptor.Encrypt(s, key);
        System.out.println(s);
        System.out.println(e);
        String d = FreqDecrypt(e, maxKeyLength);
        System.out.println(d);
        //Stringer.br

        /*while ((match < 90) && (key.length() <= maxKeyLength)) {            
         d = KeyDecrypt(e, key);
         match = Dictionary.MatchText(s, d);
            
         System.out.print(match);
         System.out.print(", ");
         System.out.print(key);
         System.out.print(", ");
         System.out.println(d);
         key = KeyMaster.ShiftKeyUp(key);
         }*/
        //String d = DictDecrypt(s, 10);
    }

}
