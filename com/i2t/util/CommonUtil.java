package com.i2t.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * <p>
 * This class has validation for common attributes.
 * </p>
 *
 */
public class CommonUtil {

    /**
     * <p>
     * This method checks the input is valid or not.
     * </p>
     *
     * @param String hours.  
     *
     * @return boolean true or false.
     */
    public static boolean isValidHours(String hours) {
        return Pattern.matches("([0][0-9]?|[0-9]?)", hours);
    }
    
    /**
     * <p>
     * This method checks the input is valid or not.
     * </p>
     *
     * @param String traineeCgpa.  
     *
     * @return boolean true or false.
     */
    public static boolean isValidCgpa(String traineeCgpa) {
        return Pattern.matches("([1-9]|[0][1-9]?|[1-9].[0-9]|[1][0])", traineeCgpa);
    }

    /**
     * <p>
     * This method checks the input is valid or not.
     * </p>
     *
     * @param String option.  
     *
     * @return boolean true or false.
     */
    public static boolean isValidOption(String option) {
        return Pattern.matches("[1-2]?", option);
    }

    /**
     * <p>
     * This method checks the input is valid or not.
     * </p>
     *
     * @param String number.  
     *
     * @return boolean true or false.
     */
    public static boolean isValidNumber(String number) {
        return Pattern.matches("[0-9]{1,}", number);
    }

    /**
     * <p>
     * This method checks the input is valid or not.
     * </p>
     *
     * @param String option.  
     *
     * @return boolean true or false.
     */
    public static boolean isValidMenu(String option) {
        return Pattern.matches("[1-6]?", option);
    }
}    