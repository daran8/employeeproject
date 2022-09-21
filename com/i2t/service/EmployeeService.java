package com.i2t.service;

import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.Set;

import com.i2t.dto.EmployeeDto;
import com.i2t.exception.EmployeeException;
import com.i2t.model.Employee;

/**
 * <p>
 * This interface has methods for entire employee.
 * </p>
 *
 */
public interface EmployeeService {

    /**
     * <p>
     * This method will add new employee.
     * </p>
     *
     * @param Employee employee.
     * @return int.
     * @throws EmployeeException.
     */
    int addTrainee(Employee employee) throws EmployeeException;

    /**
     * <p>
     * This method will print entire employees .
     * </p>
     * 
     * @param int activePage.
     * @return List<Employee> - list of trainees.
     * @throws EmployeeException.
     */
    List<Employee> getTrainees(int activePage) throws EmployeeException;

    /**
     * <p>
     * This method will print a particular trainee .
     * </p>
     *
     * @param int traineeid  
     * @return Employee - particular trainee's object.
     * @throws EmployeeException.
     */
    Employee searchTraineeById(int traineeId) throws EmployeeException;

    /**
     * <p>
     * This method will remove a particular trainee .
     * </p>
     *
     * @param int traineeid 
     * @throws EmployeeException.
     */
    void deleteTraineeById(int traineeId) throws EmployeeException;
    
    /**
     * <p>
     * This method can modify entire data for a particular trainee .
     * </p>
     *
     * @param Employee employee.
     * @return Employee.
     * @throws EmployeeException.
     */
    Employee updateTraineeById(Employee employee) throws EmployeeException;

    /**
     * <p>
     * This method will generate id number for trainee.
     * </p>
     *
     * @return int.
     * @throws EmployeeException.
     */
    int generateId() throws EmployeeException;

    /**
     * <p>
     * This method will add new trainer .
     * </p>
     *
     * @param Employee employee.
     * @return int.
     * @throws EmployeeException.
     *
     */
    int addTrainer(Employee employee) throws EmployeeException;

    /**
     * <p>
     * This method will give the stored data.
     * </p>
     * 
     * @param int activePage.
     * @return List<Employee> list of trainers.
     * @throws EmployeeException.
     */
    List<Employee> getTrainers(int activePage) throws EmployeeException;

    /**
     * <p>
     * This will give a particular trainer detail.
     * </p>
     *
     * @param int trainerId  
     * @return Employee - a particular trainer.
     * @throws EmployeeException.
     */
    Employee searchTrainerById(int trainerId) throws EmployeeException;

    /**
     * <p>
     * This will remove a particular trainer detail.
     * </p>
     *
     * @param  int trainerId.
     * @throws EmployeeException.
     */
    void deleteTrainerById(int trainerId) throws EmployeeException;

    /**
     * <p>
     * This will modify entire details of particular trainer.
     * </p>
     *
     * @param Employee employee.
     * @return Employee.
     * @throws EmployeeException.
     */
    Employee updateTrainerById(Employee employee) throws EmployeeException; 
}