package com.i2t.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * <p>
 * This class validates given words.
 * </p>
 *
 */ 
public class StringUtil {

    /**
     * <p>
     * This method validate the given name for atleast 3 charactor.
     * </p>
     *
     * @param String name
     *        this is a only parameter in this method which has the given name.
     * @return boolean
     *         this returns true or false for name.
     */
    public static boolean isValidName(String name) {
        return Pattern.matches("[a-zA-Z]{3,}", name);
    }
    
    /**
     * <p>
     * This method validate the given title for atleast 2 charactor.
     * </p>
     *
     * @param String word
     *        this is a only parameter in this method which has the given word.
     * @return boolean
     *         this returns true or false for title.
     */
    public static boolean isValidWord(String word) {
        return Pattern.matches("(?!\\.)(?!.*?\\.\\.)[a-zA-Z.(?!\\.)]{2,}", word);
    }
}