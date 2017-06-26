/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.mendelu.ppr.vigenere_paralel;

/**
 *
 * @author LANeo
 */
public class Stringer {

    /**
     * Splits string by characters and adds each one in a particular field of
     * strings. The number of fields is relative to the cutting length and the
     * iterator switches to zero if that index in the string has been reached.
     *
     * For example string: "abcdefghi" with cut equal to 3 would result in a
     * String array of length 3 with "adg", "beh", "cfi".
     *
     * Opposite method of {@link #MergeString(java.lang.String[]) }
     *
     * @param str String to be cut.
     * @param numCuts Nmber of cuts and length of returned array.
     * @return String array with split string.
     */
    public static String[] BrushString(String str, int numCuts) {
        String[] strDiv = new String[numCuts];
        for (int i = 0; i < numCuts; i++) {
            strDiv[i] = "";
        }
        int strLength = str.length();
        for (int i = 0; i < strLength; i++) {
            strDiv[i % numCuts] += String.valueOf(str.charAt(i));
        }
        return strDiv;
    }

    /**
     * Merges chars in a buffer in the form of string array repeatedly from each
     * buffer index unitl empty.
     *
     * For example "adg", "beh", "cfi" would make "abcdefghi".
     *
     * Opposite method of {@link #BrushString(java.lang.String[]) }
     *
     * @param divStrings Divided string to be merged.
     * @return A merged string.
     */
    public static String MergeString(String[] divStrings) {
        String s = "";
        int len = divStrings[0].length();
        //int num = divStrings.length;

        for (int i = 0; i < len; i++) {
            for (int j = 0; j < divStrings.length; j++) {
                try {
                    s += String.valueOf(divStrings[j].charAt(i));
                } catch (Exception e) {
                    //System.err.println(e);
                    //this is actually intended behaviour so no hram done
                }
            }
        }
        return s;
    }

    /**
     * This method searches string for a delimiter and returns the part before a
     * delimiter occurance. Useful for trimming filename's suffixes.
     *
     * @param str String to be cut.
     * @param delim Single delimiter character.
     * @return Trimed string.
     */
    public static String CutOff(String str, char delim) {
        String s = "";
        char c;
        for (int i = 0; i < str.length(); i++) {
            c = str.charAt(i);
            if (c != delim) {
                s += String.valueOf(c);
            } else {
                break;
            }
        }
        return s;
    }

    /**
     * This method counts number of occurances of a sequence in certain string.
     *
     * @param str A string to be searched in for occurances.
     * @param seq A sequence to be looked for.
     * @return Number of occurances.
     */
    public static int CountSequence(String str, String seq) {
        int lastIndex = 0;
        int count = 0;

        while (lastIndex != -1) {
            lastIndex = str.indexOf(seq, lastIndex);

            if (lastIndex != -1) {
                count++;
                lastIndex += seq.length();
            }
        }
        return count;
    }

    /**
     * Removes every non-alphabet character in a string.
     *
     * @param text Text to be modified.
     * @return Modified string.
     */
    public static String RipNonAlpha(String text) {
        return text.toLowerCase().replaceAll("[^a-z]", "");
    }

    /**
     * Replaces occurance of all characters with diacritics in a string with the
     * version of the character without one.
     *
     * @param text Text to be modified.
     * @return Modified string.
     */
    public static String RipDiacrit(String text) {
        String s = text.toLowerCase();
        s = s.replaceAll("[ž]", "z");
        s = s.replaceAll("[š]", "s");
        s = s.replaceAll("[č]", "c");
        s = s.replaceAll("[ř]", "r");
        s = s.replaceAll("[ď]", "d");
        s = s.replaceAll("[ť]", "t");
        s = s.replaceAll("[ň]", "n");
        s = s.replaceAll("[áä]", "a");
        s = s.replaceAll("[éěë]", "e");
        s = s.replaceAll("[íï]", "i");
        s = s.replaceAll("[óö]", "o");
        s = s.replaceAll("[úůü]", "u");
        s = s.replaceAll("[ýÿ]", "y");

        return s;
    }
    
    /**
     * Method splits text from string into smaller chunks. Each chunk
     * has the size defined in Engine class.
     * 
     * @param text Text to be split.
     * @return An array of Strings. Lowest index is the beginning of the text.
     */
    public static String[] Split(String text) {
        int chunks = text.length() / Engine.chunk;
        int over = text.length() % Engine.chunk;
        int pos = 0;
        String[] batch = new String[chunks + 1];
        for (int i = 0; i < chunks; i++) {
            batch[i] = text.substring(pos, pos + Engine.chunk);
            pos += Engine.chunk;
        }
        batch[batch.length - 1] = text.substring(pos, pos + over);
        return batch;
    }
    
    /**
     * Reconstructs split sttring.
     * 
     * @param batch An array of ordere string parts.
     * @return Whole string from an array.
     */
    public static String Merge(String[] batch){
        StringBuilder bld = new StringBuilder();
        for(String s : batch){
            bld.append(s);
        }
        return bld.toString();
    }

}
