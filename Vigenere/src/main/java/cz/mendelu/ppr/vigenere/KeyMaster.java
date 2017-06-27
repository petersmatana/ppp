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
public class KeyMaster {
    
    /**
     * 
     * @param keyChar
     * @param multiplier
     * @return 
     */
    public static String GenerateKey(char keyChar, int multiplier) {
        String key = "";
        for (int i = 0; i < multiplier; i++) {
            key += String.valueOf(keyChar);
        }
        return key;
    }
    
    /**
     * This method computes key from a plain text and from text
     * that is encrypted or vice versa.
     * 
     * @param plainText Etalon text to be compared.
     * @param mask Text that serves as a mask.
     * @param maxKeyLength Maximum key length.
     * @return The key with which plaintext was encrypted.
     */
    public static String ComputeKey(String plainText, String mask, int maxKeyLength) {
        //String key = "";
        throw new UnsupportedOperationException("Not implemented yet");
        //return key;
    }
    
    /**
     * This method increments the value of given key
     * The length of the key can increase if the key values
     * are at its maximum (z)
     * 
     * @param key alphabetical key to be incremented
     * @return incremented key
     */
    public static String ShiftKeyUp(String key) {
        int keyLength = key.length();
        int c = (int)key.charAt(0);
        if (c == 'z') {
            if(keyLength == 1){
                key += "a";
            } else {
                key = "a" + ShiftKeyUp(key.substring(1, keyLength));
            }
        } else {
            //int c = (int)key.charAt(keyLength-1);
            c++;
            key = String.valueOf((char)c) + key.substring(1, keyLength);
        }
        return key;
    }
    
}
