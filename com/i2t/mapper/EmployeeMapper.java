package com.i2t.mapper;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Set;

import com.i2t.constants.EmployeeConstants;
import com.i2t.dto.EmployeeDto;
import com.i2t.model.Employee;
import com.i2t.model.Role;
import com.i2t.service.EmployeeService;
import com.i2t.service.impl.EmployeeServiceImpl;
import com.i2t.util.DateUtil;

/**
 * <p>
 * This class will convert dto object to 
 * separate entity.
 * </p>
 *
 */
public class EmployeeMapper {

    /**
     * <p>
     * This meethod will convert dto object to 
     * separate entity and send to service.
     * </p>
     *
     * @param employeeDto employeeDto.
     *
     * @return employee.
     */
    public Employee dtoToEntity(EmployeeDto employeeDto) {
        Employee employee = new Employee();

        employee.setPrefix(employeeDto.getPrefix());
        employee.setFirstName(employeeDto.getFirstName());
        employee.setLastName(employeeDto.getLastName());
        employee.setPhoneNumber(employeeDto.getPhoneNumber());
        employee.setEmail(employeeDto.getEmail());
        employee.setAddress(employeeDto.getAddress());
        employee.setId(employeeDto.getId());
        employee.setBirthday(employeeDto.getBirthday());
        employee.setTechnicalLanguageKnown(employeeDto.getTechnicalLanguageKnown());
        employee.setCgpa(employeeDto.getCgpa());
        employee.setHighestQualification(employeeDto.getHighestQualification());
        employee.setBatchNumber(employeeDto.getBatchNumber());
        employee.setYearOfPass(employeeDto.getYearOfPass());
        employee.setJobTitle(employeeDto.getJobTitle());
        employee.setTeachingHours(employeeDto.getTeachingHours());
        employee.setPreviousCompanyName(employeeDto.getPreviousCompanyName());
        return employee;
    }

    /**
     * <p>
     * This meethod will convert separate entity to 
     * dto object and send to service.
     * </p>
     *
     * @param employee employee.
     *
     * @return employeeDto.
     */
    public EmployeeDto entityToDto(Employee employee) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
        EmployeeDto employeeDto = new EmployeeDto();
        
        if (employee != null) {
            employeeDto.setPrefix(employee.getPrefix());
            employeeDto.setFirstName(employee.getFirstName());
            employeeDto.setLastName(employee.getLastName());
            employeeDto.setPhoneNumber(employee.getPhoneNumber());
            employeeDto.setEmail(employee.getEmail());
            employeeDto.setAge(DateUtil.calculateAge(simpleDateFormat.format(employee.getBirthday())));
            employeeDto.setCompanyName(EmployeeConstants.COMPANY_NAME);
            employeeDto.setAddress(employee.getAddress());
            employeeDto.setId(employee.getId());
            employeeDto.setBirthday(employee.getBirthday());
            employeeDto.setTechnicalLanguageKnown(employee.getTechnicalLanguageKnown());
            employeeDto.setCgpa(employee.getCgpa());
            employeeDto.setHighestQualification(employee.getHighestQualification());
            employeeDto.setBatchNumber(employee.getBatchNumber());
            employeeDto.setYearOfPass(employee.getYearOfPass());
            employeeDto.setJobTitle(employee.getJobTitle());
            employeeDto.setTeachingHours(employee.getTeachingHours());
            employeeDto.setPreviousCompanyName(employee.getPreviousCompanyName());
        }
        return employeeDto;
    }
}