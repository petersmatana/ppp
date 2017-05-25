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
public class Alphabet {

    /**
     * 97 - 122 ascii (26: 0-25)
     */
    public static final String alphabet = "abcdefghijklmnopqrstuvwxyz";

    /**
     * 
     * @param c
     * @param key
     * @return 
     */
    public static char shiftUp(char c, char key) {
        int num = (int) c - 97;
        if ((num >= 0) && (num <= 25)) {
            num += (int) key - 97;
            if (num > 25) {
                num -= 26;
            }
            return alphabet.charAt(num);
        } else {
            return c;
        }
    }

    /**
     * 
     * @param c
     * @param key
     * @return 
     */
    public static char shiftDown(char c, char key) {
        int num = (int) c - 97;
        if ((num >= 0) && (num <= 25)) {
            num = num - (int) key + 97;
            if (num < 0) {
                num += 26;
            }
            return alphabet.charAt(num);
        } else {
            return c;
        }
    }

}
