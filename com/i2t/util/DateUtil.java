package com.i2t.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.util.Date;

/**
 * <p>
 * This class calculate the age and check input valid or not for the
 * given date of birth.
 * </p>
 *
 */
public class DateUtil {

    /**
     * <p>
     * This method calculate age for the given date and also validate the given date.
     * </p>
     *
     * @param String dateOfBirth
     *        this parameter in this method has the given date.
     * @return int
     *         this returns the age as years for dateOfBirth.
     */
    public static int calculateAge(String dateOfBirth) {
        try {
            Date date = new SimpleDateFormat("dd-MM-yyyy").parse(dateOfBirth);
            String parseDate = new SimpleDateFormat("yyyy-MM-dd").format(date);
            LocalDate birthday = LocalDate.parse(parseDate);
            LocalDate presentDate = LocalDate.now();
            Period age = Period.between(birthday, presentDate);
            return age.getYears(); 
        } catch (ParseException parseException) {
            return 0;
        }
    }
 
    /**
     * <p>
     * This method validate the given date.
     * </p>
     *
     * @param String dateOfBirth
     *        this in this method has the given date.
     * @return int
     *         this returns true or false.
     */
    public static boolean isValidDate(String dateOfBirth) {
        if (dateOfBirth.length() == 10) {
            try {
                DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
                dateFormat.setLenient(false);
                dateFormat.parse(dateOfBirth);
                return true;
            } catch (ParseException parseException) {
                return false;
            }
        } else {
            return false;
        }
    }

    /**
     * <p>
     * This method validates the given year.
     * </p>
     *
     * @param String year
     *        this in this method has the given date.
     * @return boolean
     *         this returns true or false.
     */
    public static boolean isValidYear(String year) {
        if (year.length() == 4) {
            try {
                DateFormat dateFormat = new SimpleDateFormat("yyyy");
                dateFormat.setLenient(false);
                Date date = new SimpleDateFormat("yyyy").parse(year);
                String parseDate = new SimpleDateFormat("yyyy-MM-dd").format(date);
                LocalDate passingYears = LocalDate.parse(parseDate);
                LocalDate presentDate = LocalDate.now();
                Period yearsDifferent = Period.between(passingYears, presentDate);
                if (yearsDifferent.getYears() <= 3 && yearsDifferent.getYears() >= 0) {
                    return true;
                }
            } catch (ParseException parseException) {
                return false;
            }
        }
        return false;
    }

    /**
     * <p>
     * This method converts given string into date.
     * </p>
     *
     * @param String date
     *        this in this method has the given date.
     * @return Date
     *         this returns converted Date.
     */
    public static Date convertDate(String date) {
        Date employeeDateOfBirth = null;
        try {
            employeeDateOfBirth = new SimpleDateFormat("dd-MM-yyyy").parse(date);
            return employeeDateOfBirth;
        } catch (ParseException parseException) {
            return employeeDateOfBirth;
        }
    }
} 