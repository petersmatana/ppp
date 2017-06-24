/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.mendelu.ppr.vigenere;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Class for managing dictionaries, loading corresponding wordlists and text
 * matching.
 *
 * @author Lukas
 */
public class Dictionary {

    Comparator<String> comp = new Comparator<String>() {
        @Override
        public int compare(String o1, String o2) {
            return o2.length() - o1.length();
        }
    };

    private final List<String> vocabulary;
    private final String name;
    private static final List<Dictionary> langs = new ArrayList<>();
    private static Dictionary selectedDictionary;
    private static final String dirPath = "./Text/dictionaries";

    static {
        Init();
    }

    /**
     * Initializes the basic set of wordlists by loading them into separate
     * instances with corresponding name in a {@link #name} property. The
     * wordlists must be located within a directory specified in
     * {@link #dirPath} property.
     *
     * The files must have a {@code .txt} or {@code .lang} suffix.
     *
     * The instances are held in a static {@link #langs} property and can be
     * accessed only by the class methods.
     */
    public static void Init() {
        System.out.println("jedu");
        Path dir = new File(dirPath).toPath();
        try (DirectoryStream<Path> stream
                = Files.newDirectoryStream(dir, "*.{txt,lang,lst}")) {
            for (Path entry : stream) {
                langs.add(new Dictionary(entry.toFile()));
                //System.out.println(entry.getFileName());
            }
        } catch (IOException x) {
            // IOException can never be thrown by the iteration.
            // In this snippet, it can // only be thrown by newDirectoryStream.
            System.err.println(x);
        }
        if (!langs.isEmpty()) {
            if(!SelectDictionary(Engine.lang)){
                System.err.println("No suitable Dictionary found for preffered language.");
                selectedDictionary = langs.get(0);
            } 
        } else {
            selectedDictionary = null;
            System.err.println("There were no files present in the \"" + dirPath + "\"");
        }
    }
    
    /**
     * Supplies list of all loaded dictionaries.
     * 
     * @return List of loaded dictionaries.
     */
    public static List<String> ListDictionaries(){
        List<String> dics = new ArrayList<>();
        for (Dictionary d: langs) {
            dics.add(d.name);
        }
        return dics;
    }

    /**
     * Selects one dictionary instance from the {@link #langs} by its name.
     *
     * @param name name of the dicitionary instance (same as the filename
     * prefix).
     * @return true if successfully selected, false if there is no such
     * dictionary with given name.
     */
    public static boolean SelectDictionary(String name) {
        int index = 0;
        boolean foundDictionary = false;
        if (langs.isEmpty()) {
            throw new RuntimeException(
                    "There are no loaded dictionaries. Use Init() method first."
            );
        } else {
            while (index < langs.size() && !foundDictionary) {
                if (langs.get(index).name.equals(name)) {
                    foundDictionary = true;
                    selectedDictionary = langs.get(index);
                } else {
                    index++;
                }
            }
            return foundDictionary;
        }
    }

    /**
     * Calculates the percentage of matching words in given text with currently
     * selected dictionary in static {@link #selectedDictionary} property.
     *
     * @param text text to be searched for dictionary-legal words.
     * @return percentage of the text matched against a dictionary.
     */
    public static float MatchVocabulary(String text) {
        if (langs.isEmpty()) {
            throw new RuntimeException(
                    "There are no loaded dictionaries from \""
                    + dirPath
                    + "\". Use Init() method first but "
                    + "ensure the folder is not empty."
            );
        } else {
            int count;
            float percentage;
            int len = text.length();
            count = selectedDictionary.findWordsRem(text);
            percentage = (count / len) * 100;
            return percentage;
        }
    }

    /**
     * Calculates the percentage of matching characters in given text with a
     * paragon text.
     *
     * If the texts are not the same length, the method will only compare those
     * characters that are within the reach of both texts in terms of indexing.
     *
     * @param plain a paragon text for matching.
     * @param searchBatch a text for searching
     * @return percentage of the text matched
     */
    public static float MatchText(String plain, String searchBatch) {
        float percentage;
        int count = 0;
        int len = Math.min(plain.length(), searchBatch.length());
        for (int i = 0; i < len; i++) {
            if (plain.charAt(i) == searchBatch.charAt(i)) {
                count++;
            }
        }
        percentage = (count / len) * 100;
        return percentage;
    }

    /**
     * Standard constructor of the Dictionary class.
     *
     * @param dictionaryFile File containing wordlist of a certain language. A
     * word on each row.
     */
    public Dictionary(File dictionaryFile) {
        vocabulary = new ArrayList<>();
        name = Stringer.CutOff(dictionaryFile.getName(), '.');
        importWords(dictionaryFile);
        sortDictionary();
    }

    /**
     * Method for loading words from wordlists into an instance list.
     *
     * @param dictionaryFile File containing wordlist of a certain language. A
     * word on each row.
     */
    private void importWords(File dictionaryFile) {
        try (BufferedReader br = new BufferedReader(new FileReader(dictionaryFile))) {
            String s;
            while ((s = br.readLine()) != null) {
                //vocabulary.add(s); //diakritika ano
                vocabulary.add(Stringer.RipDiacrit(s)); //diakritika ne
            }
        } catch (Exception e) {
            System.err.println("Can't read file. Wrong path?");
            System.err.println(e);
        }
    }

    /**
     * Sorts the {@link #vocabulary} list of words by length descending.
     */
    private void sortDictionary() {
        Collections.sort(vocabulary, comp);
    }

    /**
     * This method prints a range of the words in a {@link #vocabulary} list.
     *
     * @param startIndex starting index in the vocabulary list
     * @param endIndex
     */
    public void printWords(int startIndex, int endIndex) {
        if (startIndex >= 0 && endIndex < vocabulary.size() && startIndex <= endIndex) {
            for (int i = startIndex; i < endIndex; i++) {
                System.out.println(vocabulary.get(i));
            }
        }
    }

    /**
     * Finds all the words in a text matching any in a {@link #vocabulary} and
     * returns their total character count. This method takes very long time to
     * complete if the vocabulary is very extensive. About half hour.
     *
     * @param text Text to be searched.
     * @return List of found words.
     */
    public int findWordsRem(String text) {
        int found = 0;
        int count;

        for (String word : vocabulary) {
            if (text.contains(word)) {
                count = text.length();
                text = text.replaceAll(word, "");
                found += count - text.length();
            }
        }
        return found;
    }
}
