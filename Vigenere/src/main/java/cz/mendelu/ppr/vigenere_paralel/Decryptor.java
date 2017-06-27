/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.mendelu.ppr.vigenere_paralel;

import cz.mendelu.ppr.vigenere.Alphabet;
import cz.mendelu.ppr.vigenere.Frequency;
import cz.mendelu.ppr.vigenere.KeyMaster;
import java.util.concurrent.CountDownLatch;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * This class serves to decrypt given text, which is encrypted by Vigenere
 * cipher. This text could be decrypted with key by method
 * {@link #Decrypt1(String, String) Decrypt1} or forced by method
 * {@link #ForceKey(String, String) Decrypt1}
 *
 * @author LANeo
 */
public class Decryptor{

    /**
     * This method decrypts a text with supplied key. It does not check the
     * correctness of the decryption in any means.
     *
     * @param encrypted String to be decrypted.
     * @param key Key to be used for decryption.
     * @return Decrypted string.
     */
    public static String KeyDecrypt(String encrypted, String key) {
        String[] batch = Stringer.Split(encrypted);
        Thread[] bank = new Thread[batch.length];
        CountDownLatch count = new CountDownLatch(batch.length);
        KeyDecryptor.SetBatch(batch.length);
        for(int i = 0; i < batch.length; i++){
            bank[i] = new Thread(new KeyDecryptor(batch[i], key, i, count));
            bank[i].start();
        }
        
        try {
            count.await();
        } catch (InterruptedException ex) {
            Logger.getLogger(Encryptor.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return KeyDecryptor.GetText();
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
        //String best = null;
        CountDownLatch count = new CountDownLatch(maxKeyLen);
        Thread[] bank = new Thread[maxKeyLen];
        for (int keyLen = 1; keyLen <= maxKeyLen; keyLen++) {
            bank[keyLen-1] = new Thread(new FrequencyDecryptor(count, encrypted, keyLen));
            bank[keyLen-1].start();
        }
        
        try {
            count.await();
        } catch (InterruptedException ex) {
            Logger.getLogger(Decryptor.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return FrequencyDecryptor.GetBest();
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

}
