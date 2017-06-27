/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.mendelu.ppr.vigenere;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;

/**
 *
 * @author LANeo
 */
public class Engine {

    public static String lang = "CZ";
    public static String encoding = "UTF-8";
    public static final String plainPath = "./Text/plain/";
    public static final String encPath = "./Text/encrypted/";
    public static final String decPath = "./Text/decrypted/";

    /**
     *
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        final int chunk = 1000; // should be at least 500 for the frequency analysis to work
        final int maxKeyLen = 20;
        
        String plainFile = "nesmysl.txt";
        String key = "gurgle";
        char[] cbuff = new char[chunk];
        /*
        // encrypt file
        try (BufferedReader in = new BufferedReader(new InputStreamReader(
          new FileInputStream(new File(plainPath + plainFile)), encoding));
                PrintWriter writer = new PrintWriter(encPath + "enc_" + plainFile, encoding)) {
            int size = chunk; // can be any size
            while (in.read(cbuff, 0, size) != -1) {
                StringBuilder dataBuilder = new StringBuilder();
                dataBuilder.append(cbuff);
                String data = Stringer.RipDiacrit(dataBuilder.toString());
                System.out.println(data);
                writer.print(Encryptor.Encrypt(data, key));
            }
        } catch (Exception e) {
            System.err.println(e);
        }*/
        /*
        try (BufferedReader in = new BufferedReader(new FileReader(plainPath + plainFile)); PrintWriter writer = new PrintWriter(encPath + "enc_" + plainFile, "UTF-8")) {
            int size = chunk; // can be any size
            while (in.read(cbuff, 0, size) != -1) {
                String data = Stringer.RipDiacrit(cbuff.toString());
                writer.print(Encryptor.Encrypt(data, key));
            }
        } catch (Exception e) {
            System.err.println(e);
        }*/
        /*
        // decrypt file
        try (BufferedReader in = new BufferedReader(new FileReader(encPath + "enc_" + plainFile)); PrintWriter writer = new PrintWriter(decPath + "dec_" + plainFile, "UTF-8")) {
            while (in.read(cbuff, 0, chunk) != -1) { 
                String data = Stringer.RipDiacrit(Arrays.toString(cbuff));
                writer.print(Decryptor.FreqDecrypt(data, maxKeyLen));
            }
        } catch (Exception e) {
            System.err.println(e);
        }
        */
        
         String en = "When in the course of human events it becomes necessary for one people to dissolve the political bands which have connected them with another and to assume among the powers of the earth the separate and equal station to which the laws of Nature and of Nature's God entitle them, a decent respect to the opinions of mankind requires that they declare the causes of their separation.";
         String s = "Ahoj já jsem krtek, říkám krtek, protože jsem prostě krtek. Měli byste mě mít rádi a já budu mít rád vás. Když mi dáte pokoj, budu hodnej, jinak ale vemu lopatičku a zatluču vás do země. máte štěstí, že jsem takový lidumil a mám vás rád vy umaštění myslivci. Jednoho dne bude vše moje a vy budete lítat na letadlech. Nemyslete si, že vám to projde vy červení slimáci.";
         //String key = "ahoj";
         //Dictionary.SelectDictionary("CZ");
         //Frequency.SetLang("CZ");
         //String enc = Encryptor.Encrypt(s, key);
         String enc = Encryptor.Encrypt(Stringer.RipDiacrit(s), key);
         System.out.println(s);
         System.out.println(enc);
        
         String dec_f = Decryptor.FreqDecrypt(enc, 10);
         System.out.println(dec_f);
         //String dec_d = Decryptor.DictDecrypt(enc, 10);
         //System.out.println(dec_d);
         
    }

}
