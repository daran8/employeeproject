package com.i2t.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * <p>
 * This class validates given phone number.
 * </p>
 *
 */

public class PhoneNumUtil {

    /**
     * <p>
     * This method validates the given Phone Number.
     * </p>
     *
     * @param String number
     *        this is a only parameter in this method which has the given Phone number.
     * @return boolean
     *         this returns true or false for number.
     */
    public static boolean isValidPhoneNum(String number) {
        return Pattern.matches("([6-9]{1}[0-9]{9})",number); 
    }    
}