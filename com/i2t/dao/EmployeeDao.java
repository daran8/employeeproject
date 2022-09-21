package com.i2t.dao;

import java.util.List;

import com.i2t.exception.EmployeeException;
import com.i2t.model.Employee;
import com.i2t.model.Role;

/**
 * <p>
 * This interface Stores the information of Employee.
 * </p>
 *
 */
public interface EmployeeDao {

    /**
     * <p>
     * This Method Will add new Employee.
     * </p>
     *
     * @param Employee employee. - This variable has Employee's data as object.
     * @throws EmployeeException.
     * @return int.
     */
    int insertEmployee(Employee employee) throws EmployeeException;

    /**
     * <p>
     * This Method Will modify a Employee.
     * </p>
     *
     * @param Employee employee. - This variable has Employee's data as object.
     * @throws EmployeeException.
     */
    Employee updateEmployee(Employee employee) throws EmployeeException;

    /**
     * <p>
     * This Method Will search a Employee.
     * </p>
     *
     * @param int employeeId. - This variable has Employee's id number.
     * @param String role. - This variable has Employee's role.
     * @throws EmployeeException.
     * @return Employee. - Prints a particular Employee.
     */
    Employee searchEmployeeById(int employeeId, String role) throws EmployeeException;

    /**
     * <p>
     * This Method Will delete a Employee.
     * </p>
     *
     * @param int employeeId. - This variable has Employee's id number.
     * @param String role. - This variable has Employee's role.
     * @throws EmployeeException.
     */
    void deleteEmployeeById(int employeeId, String role) throws EmployeeException;

    /**
     * <p>
     * This Method Will print all Employee by role.
     * </p>
     *
     * @param String role. - This variable has Employee's role.
     * @param int activePage.
     * @throws EmployeeException.
     * @return List<Employee>. - Prints entire employee.
     */
    List<Employee> retrieveEmployeesByRole(String role,int activePage) throws EmployeeException;

    /**
     * <p>
     * This Method Will get role.
     * </p>
     *
     * @param String role.
     * @throws EmployeeException.
     * @return Role.
     */
    Role getRoleByName(String role) throws EmployeeException;

    /**
     * <p>
     * This Method Will get last id value.
     * </p>
     *
     * @throws EmployeeException.
     * @return int.
     */
    int getLastId() throws EmployeeException;
}