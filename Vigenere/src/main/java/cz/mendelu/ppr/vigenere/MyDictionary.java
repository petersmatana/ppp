/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.mendelu.ppr.vigenere;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 *
 * @author Lukas
 */
public class MyDictionary {
    Comparator<String> comp = new Comparator<String>(){
        @Override
        public int compare(String o1, String o2){
            return o2.length() - o1.length();
        }
    };    
    
    private List<String> dictionary;
    
    public MyDictionary(String path){
        dictionary = new ArrayList<String>();
        importWords(path);
        sortDictionary();
    }
    
    private void importWords(String path){
        try (BufferedReader br = new BufferedReader(new FileReader(path))){
            String s;
            while ((s = br.readLine()) != null){
                    dictionary.add(s);
            }
        }
        catch (Exception e){
            System.err.println("Can't read file. Wrong path?");
        }
    }
    
    private void sortDictionary(){
        Collections.sort(dictionary, comp);       
    }
    
    public void printWords(int startIndex, int endIndex){
        if(startIndex >= 0 && endIndex < dictionary.size() && startIndex <= endIndex){
            for(int i = startIndex; i < endIndex; i++)
                System.out.println(dictionary.get(i));
        }
    }
    
    //vraci vse co lze v retezci identifikovat
    public List<String> findWords(String text){
        List<String> found = new ArrayList<String>();
        for (String word: dictionary){
            if(text.contains(word))
                found.add(word);
        }
        return found;
    }
    
    //nalezena slova z retezce maze
    public List<String> findWordsRem(String text){
        List<String> found = new ArrayList<String>();
        for (String word: dictionary){
            if(text.contains(word)){
                found.add(word);
                text=text.replaceAll(word, "");
            }            
        }
        return found;
    }
}