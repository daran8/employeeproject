package com.i2t.exception;

import java.lang.Exception;
/**
 * <p>
 * This class has a custom exception for employee.
 * </p>
 *
 */

public class EmployeeException extends Exception {
    /**
    * <p>
    * This method is a custom exception for employee. 
    * </p>
    *
    * @param String message.
    */
    public EmployeeException(String message) {
        super(message);
    }
}