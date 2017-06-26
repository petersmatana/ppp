/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.mendelu.ppr.vigenere_paralel;

import cz.mendelu.ppr.vigenere.Alphabet;
import java.util.concurrent.CountDownLatch;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author LANeo
 */
public class Encryptor implements Runnable{
    
    private final String myText;
    private final int myIndex;
    private static String sKey;
    private static String[] sBatch;
    private static CountDownLatch count;
    
    /**
     * Standard public constructor.
     * 
     * @param text Text to be encrypted.
     * @param index Index of the encrypting instance. Could be used as ID.
     */
    public Encryptor(String text, int index){
        myText = text;
        myIndex = index;
    }
    
    /**
     * Encrypts given string by given key and outputs it.
     * 
     * @param text Text to be encrypted with key.
     * @param key Key to encrypt the string with.
     * @return Encyrpted string.
     */
    public static String Encrypt(String text, String key){
        sKey = key;
        
        StringBuilder bld = new StringBuilder();
        String[] batch = Stringer.Split(text);
        Thread[] bank = new Thread[batch.length];
        count = new CountDownLatch(batch.length);
        sBatch = new String[batch.length];
        for(int i = 0; i < batch.length; i++){
            bank[i] = new Thread(new Encryptor(batch[i], i));
            bank[i].start();
        }
        
        try {
            count.await();
        } catch (InterruptedException ex) {
            Logger.getLogger(Encryptor.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        for (String s : sBatch) {
            bld.append(s);
        }
        return bld.toString();
    }

    @Override
    public void run() {
        StringBuilder bld = new StringBuilder();
        int strLength = myText.length();
        int keyLength = sKey.length();
        for (int i = 0; i < strLength; i++) {
            bld.append(Alphabet.shiftUp(myText.charAt(i),sKey.charAt(i % keyLength)));
        }
        sBatch[myIndex] = bld.toString();
        count.countDown();
    }

}
