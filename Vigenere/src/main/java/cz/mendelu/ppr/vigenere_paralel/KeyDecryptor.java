/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.mendelu.ppr.vigenere_paralel;

import cz.mendelu.ppr.vigenere.Alphabet;
import java.util.concurrent.CountDownLatch;

/**
 *
 * @author LANeo
 */
public class KeyDecryptor extends Decryptor implements Runnable{
    private final String myText;
    private final String myKey;
    private final int myIndex;
    private final CountDownLatch myCount;
    private static String[] sBatch;
    
    /**
     * Standard constructor.
     * 
     * @param s String to be Decrypted.
     * @param key Key used for decryption.
     * @param index Index of the decrypting instance. Could be used as ID.
     */
    public KeyDecryptor(String s, String key, int index, CountDownLatch count){
        myText = s;
        myKey = key;
        myIndex = index;
        myCount = count;
    }
    
    /**
     * Sets the size of a batch in accordance with the number of threads
     * to be executed.
     * 
     * @param size The size of the buffer, same as number of threads.
     */
    public static void SetBatch(int size){
        sBatch = new String[size];
    }
    
    /**
     * This method assembles the final text from the results of all threads.
     * 
     * @return The decrypted text.
     */
    public static String GetText(){
        StringBuilder bld = new StringBuilder();
        for(String s : sBatch){
            bld.append(s);
        }
        return bld.toString();
    }

    @Override
    public void run() {
        StringBuilder bld = new StringBuilder();
        int strLength = myText.length();
        int keyLength = myKey.length();
        for (int i = 0; i < strLength; i++) {
            bld.append(Alphabet.shiftDown(myText.charAt(i),myKey.charAt(i % keyLength)));
        }
        sBatch[myIndex] = bld.toString();
        myCount.countDown();
    }
    
}
