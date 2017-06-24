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
    
    public static String GuessKey(String str, int maxKeyLength) {
        String key = "";
        return key;
    }
    
    public static String BreakKey(String str, int maxKeyLength) {
        String key = "";
        return key;
    }
    
    /**
     * 
     * @param str
     * @return 
     */
    public static int ComputeKeyLength(String str) {
        int keyLength = 0;
        return keyLength;
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
