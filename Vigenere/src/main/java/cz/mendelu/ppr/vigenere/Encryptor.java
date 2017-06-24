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
public class Encryptor {
    
    /**
     * Encrypts given string by given key and outputs it.
     * 
     * @param str string to be encrypted
     * @param key key to encrypt the string with
     * @return    encyrpted string
     */
    public static String Encrypt(String str, String key){
        int strLength = str.length();
        int keyLength = key.length();
        String result = "";
        for(int i = 0; i < strLength; i++){
            result += Alphabet.shiftUp(str.charAt(i),key.charAt(i % keyLength));
        }
        return result;
    }
    
}
