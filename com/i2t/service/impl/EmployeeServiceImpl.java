package com.i2t.service.impl;

import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import com.i2t.constants.EmployeeConstants;
import com.i2t.dao.EmployeeDao;
import com.i2t.dao.impl.EmployeeDaoImpl;
import com.i2t.exception.EmployeeException;
import com.i2t.id.EmployeeId;
import com.i2t.mapper.EmployeeMapper;
import com.i2t.model.Employee;
import com.i2t.model.Role;
import com.i2t.service.EmployeeService;

/**
 * <p>
 * This class has the overridden methods of TraineeService interface.
 * </p>
 *
 */

public class EmployeeServiceImpl implements EmployeeService {
    EmployeeDao employeeDao = new EmployeeDaoImpl();
    EmployeeMapper employeeMapper = new EmployeeMapper();
    
    /**
     * {@inheritDoc}
     *
     */
    public int addTrainee(Employee employee) throws EmployeeException {
        Role role = employeeDao.getRoleByName(EmployeeConstants.ROLE_2);
        employee.getRoles().add(role);
        return employeeDao.insertEmployee(employee);  
    }

    /**
     * {@inheritDoc}
     *
     */
    public List<Employee> getTrainees(int activePage) throws EmployeeException {
        List<Employee> employeeList = new ArrayList<>();
        employeeDao.retrieveEmployeesByRole(EmployeeConstants.ROLE_2, activePage).stream().forEach(employee -> employeeList.add(employee));
        return employeeList;
        
    }

    /**
     * {@inheritDoc}
     *
     */
    public Employee searchTraineeById(int traineeId) throws EmployeeException {
        return employeeDao.searchEmployeeById(traineeId, EmployeeConstants.ROLE_2);
    }

    /**
     * {@inheritDoc}
     *
     */
    public void deleteTraineeById(int traineeId) throws EmployeeException {
        employeeDao.deleteEmployeeById(traineeId, EmployeeConstants.ROLE_2);
    }

    /**
     * {@inheritDoc}
     *
     */
    public Employee updateTraineeById(Employee employee) throws EmployeeException {
        Employee replaceEmployee = employeeDao.searchEmployeeById(employee.getId(), EmployeeConstants.ROLE_2);
        if (!employee.getPrefix().toString().isEmpty()) {
            replaceEmployee.setPrefix(employee.getPrefix());
        }
        if (!employee.getFirstName().isEmpty()) {  
            replaceEmployee.setFirstName(employee.getFirstName());
        }
        if (!employee.getLastName().isEmpty()) {
            replaceEmployee.setLastName(employee.getLastName());
        }
        if (!employee.getPhoneNumber().isEmpty()) {
            replaceEmployee.setPhoneNumber(employee.getPhoneNumber());
        }
        if (!employee.getEmail().isEmpty()) {
            replaceEmployee.setEmail(employee.getEmail());
        }
        if (!employee.getAddress().isEmpty()) {
            replaceEmployee.setAddress(employee.getAddress());
        }
        if (employee.getBirthday() != null) {
            replaceEmployee.setBirthday(employee.getBirthday());
        }
        if (!employee.getTechnicalLanguageKnown().isEmpty()) {
            replaceEmployee.setTechnicalLanguageKnown(employee.getTechnicalLanguageKnown());
        }
        if (employee.getCgpa() != 0) {
            replaceEmployee.setCgpa(employee.getCgpa());
        }
        if (!employee.getHighestQualification().isEmpty()) {
            replaceEmployee.setHighestQualification(employee.getHighestQualification());
        }
        if (employee.getBatchNumber() != 0) {
            replaceEmployee.setBatchNumber(employee.getBatchNumber());
        }
        if (employee.getYearOfPass() != 0) {
            replaceEmployee.setYearOfPass(employee.getYearOfPass());
        }
        return employeeDao.updateEmployee(replaceEmployee);
    }

    /**
     * {@inheritDoc}
     *
     */
    public int generateId() throws EmployeeException {
        int employeeId = employeeDao.getLastId();
        if (employeeId != 0) {
            employeeId = ++employeeId;
        } else {
            employeeId = EmployeeId.employeeId;
        }
        return employeeId;
    }

    /**
     * {@inheritDoc}
     *
     */
    public Employee updateTrainerById(Employee employee) throws EmployeeException {
        Employee replaceEmployee = employeeDao.searchEmployeeById(employee.getId(), EmployeeConstants.ROLE_1);
        if (!employee.getPrefix().toString().isEmpty()) {
            replaceEmployee.setPrefix(employee.getPrefix());
        }
        if (!employee.getFirstName().isEmpty()) {
            replaceEmployee.setFirstName(employee.getFirstName());
        }
        if (!employee.getLastName().isEmpty()) {
            replaceEmployee.setLastName(employee.getLastName());
        }
        if (!employee.getPhoneNumber().isEmpty()) {
            replaceEmployee.setPhoneNumber(employee.getPhoneNumber());
        }
        if (!employee.getEmail().isEmpty()) {
            replaceEmployee.setEmail(employee.getEmail());
        }
        if (!employee.getAddress().isEmpty()) {
            replaceEmployee.setAddress(employee.getAddress());
        }
        if (employee.getBirthday() != null) {
            replaceEmployee.setBirthday(employee.getBirthday());
        }
        if (!employee.getJobTitle().isEmpty()) {
            replaceEmployee.setJobTitle(employee.getJobTitle());
        }
        if (employee.getTeachingHours() != 0) {
            replaceEmployee.setTeachingHours(employee.getTeachingHours());
        }
        if (!employee.getPreviousCompanyName().isEmpty()) {
            replaceEmployee.setPreviousCompanyName(employee.getPreviousCompanyName());
        }
        return employeeDao.updateEmployee(replaceEmployee);
    }

    /**
     * {@inheritDoc}
     *
     */
    public int addTrainer(Employee employee) throws EmployeeException {
       Role role = employeeDao.getRoleByName(EmployeeConstants.ROLE_1);
       employee.getRoles().add(role);
       return employeeDao.insertEmployee(employee);
    }

    /**
     * {@inheritDoc}
     *
     */
    public List<Employee> getTrainers(int activePage) throws EmployeeException {
        List<Employee> employeeList = new ArrayList<>(employeeDao.retrieveEmployeesByRole(EmployeeConstants.ROLE_1, activePage));
        return employeeList;
    }

    /**
     * {@inheritDoc}
     *
     */
    public Employee searchTrainerById(int trainerId) throws EmployeeException {
        return employeeDao.searchEmployeeById(trainerId, EmployeeConstants.ROLE_1);
    } 

    /**
     * {@inheritDoc}
     *
     */
    public void deleteTrainerById(int trainerId) throws EmployeeException {
        employeeDao.deleteEmployeeById(trainerId, EmployeeConstants.ROLE_1);
    }

}