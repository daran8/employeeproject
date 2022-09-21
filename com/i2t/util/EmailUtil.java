package com.i2t.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * <p>
 * This class validates given email.
 * </p>
 *
 */

public class EmailUtil {

    /**
     * <p>
     * This method validate the given Email.
     * </p>
     *
     * @param String email
     *        this is a only parameter in this method which has the given email.
     * @return boolean
     *         this returns true or false for email.
     *
     */
    public static boolean isValidEmail(String email) {
        Pattern pattern = Pattern.compile("^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$");
        Matcher matcher = pattern.matcher(email);
        return matcher.find();        
    }    
}