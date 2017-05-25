/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.mendelu.ppr.vigenere;

import org.apache.commons.cli.*;
//import org.apache.commons.cli.Options;

/**
 *
 * @author LANeo
 */
public class Engine {
    
    private static String removeDiacritics(String str){
        return "";
    }
    
    private static String removeNonAlphabet(String str){
        return "";
    }

    /**
     * 
     * @param args
     * @throws Exception 
     */
    public static void main(String[] args) throws Exception {
        Options options = new Options();
        options.addOption("V", false, "Verbose");
        options.addOption("S", false, "Silent");
        options.addOption("E", false, "Encrypt");
        options.addOption("D", false, "Decrypt");
        options.addOption("s", true, "string");
        options.addOption("k", true, "key");
        options.addOption("f", true, "filename");

        //CommandLineParser parser = new DefaultParser();
        CommandLineParser parser = new BasicParser();
        CommandLine cmd = parser.parse(options, args);

        if (cmd.hasOption("s") && cmd.hasOption("k")) {
            String str = cmd.getOptionValue("s");
            String key = cmd.getOptionValue("k");
            String result = "";
            if (cmd.hasOption("E")) {
                if (!cmd.hasOption("S")) {
                    System.out.println("Encrypting");
                    if (cmd.hasOption("V")) {
                        System.out.println("String: " + str);
                        System.out.println("Key: " + key);
                    }
                }
                result = Encryptor.Encrypt(str, key);
            } else if (cmd.hasOption("D")) {
                if (!cmd.hasOption("S")) {
                    System.out.println("Decrypting");
                    if (cmd.hasOption("V")) {
                        System.out.println("String: " + str);
                        System.out.println("Key: " + key);
                    }
                }
                result = Decryptor.Decrypt(str, key);
            }
            if (cmd.hasOption("f")) {
                String fileName = cmd.getOptionValue("f");
                //open file
                //save stuff
                //close file
                if (!cmd.hasOption("S")){
                    System.out.println("Result saved in file: "+fileName);
                }
            } else {
                if (!cmd.hasOption("S")){
                    System.out.println("Result:");
                }
                System.out.println(result);
            }
        } else {
            System.out.println("No options on CL found.");
            System.out.println("Entering interactive mode...");
            char choice = 'x';
            while (choice != 'e'){
                //printmenu
                //read
                //switch do stuff
            }
        }

        //BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("grump");
    }

}
