/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.mendelu.ppr.vigenere_paralel;

import cz.mendelu.ppr.vigenere.Frequency;
import java.util.concurrent.CountDownLatch;

/**
 *
 * @author LANeo
 */
public class FrequencyDecryptor extends Decryptor implements Runnable {
    
    private static String best = null;
    private int keyLength;
    private String encrypted;
    private CountDownLatch myCount;

    public FrequencyDecryptor(CountDownLatch count, String s, int length) {
        myCount = count;
        encrypted = s;
        keyLength = length;
    }

    private static synchronized void SetBest(String text) {
        best = Frequency.min(best, text);
    }
    
    public static String GetBest(){
        return best;
    }

    @Override
    public void run() {
        String[] divided = Stringer.BrushString(encrypted, keyLength);
        String[] decrypted = new String[keyLength];
        for (int j = 0; j < keyLength; j++) {
            decrypted[j] = Frequency.DeCaesar(divided[j], 'a', 'z');
        }
        String combined = Stringer.MergeString(decrypted);
        SetBest(combined);
        myCount.countDown();
    }

}
