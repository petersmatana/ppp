/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.mendelu.ppr.vigenere_paralel;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;

/**
 *
 * @author LANeo
 */
public class Engine {

    public static String lang = "CZ";
    public static String encoding = "UTF-8";
    public static final int chunk = 1000;
    public static final String plainPath = "./Text/plain/";
    public static final String encPath = "./Text/encrypted/";
    public static final String decPath = "./Text/decrypted/";

    /**
     *
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        String key = "gurgle";
        int showtext = 72;

        String line;
        StringBuilder bld = new StringBuilder();
        try (
                InputStream fis = new FileInputStream(plainPath+"blabol1k.txt");
                InputStreamReader isr = new InputStreamReader(fis, Charset.forName("UTF-8"));
                BufferedReader br = new BufferedReader(isr);) {
            while ((line = br.readLine()) != null) {
                bld.append(line);
            }
        }
        
        String en = "When in the course of human events it becomes necessary for one people to dissolve the political bands which have connected them with another and to assume among the powers of the earth the separate and equal station to which the laws of Nature and of Nature's God entitle them, a decent respect to the opinions of mankind requires that they declare the causes of their separation.";
        String cz = "Ahoj já jsem krtek, říkám krtek, protože jsem prostě krtek. Měli byste mě mít rádi a já budu mít rád vás. Když mi dáte pokoj, budu hodnej, jinak ale vemu lopatičku a zatluču vás do země. máte štěstí, že jsem takový lidumil a mám vás rád vy umaštění myslivci. Jednoho dne bude vše moje a vy budete lítat na letadlech. Nemyslete si, že vám to projde vy červení slimáci.";

        String s = bld.toString();

        System.out.println(s.substring(0, showtext));

        String enc = Encryptor.Encrypt(Stringer.RipDiacrit(s), key);
        System.out.println(enc.substring(0, showtext));

        //String dec = Decryptor.KeyDecrypt(enc, key);
        //System.out.println(dec.substring(0, showtext));
        //String dec_f = Decryptor.FreqDecrypt(enc, 10);
        //System.out.println(dec_f.substring(0, showtext));

    }

}
