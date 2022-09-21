package com.i2t.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.EnumType;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import com.i2t.employeeEnum.PrefixEnum;
/**
 * <p>
 * This class collects and stores the common information of
 * each employee of a company. 
 * </p>
 *
 */
@Entity
@Table(name = "employee")
public class Employee {
    @Id
    @Column(name = "id")
    private int id;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;

    @Column(name = "phone_number")
    private String phoneNumber;
    @Column(name = "email")
    private String email;
    @Column(name = "address")
    private String address;

    @Temporal(TemporalType.DATE)
    @Column(name = "birthday")
    private Date birthday;

    @Enumerated(EnumType.STRING)
    @Column(name = "Prefix")
    private PrefixEnum.Prefix prefix;
   
    @Column(name = "skill_name")
    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "employee_skill", joinColumns = @JoinColumn(name = "id"))
    private Set<String> technicalLanguageKnown;

    @Column(name = "cgpa")
    private float cgpa;
    @Column(name = "qualification")
    private String highestQualification;
    @Column(name = "batch_number")
    private int batchNumber;
    @Column(name = "year_of_pass")
    private int yearOfPass;

    @Column(name = "job_title")
    private String jobTitle;
    @Transient
    private int salary;
    @Column(name = "teaching_hours")
    private int teachingHours;
    @Column(name = "previous_company")
    private String previousCompanyName;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
        name = "employee_role",
        joinColumns = {@JoinColumn(name = "id")},
        inverseJoinColumns = {@JoinColumn(name = "role_id")})
    private List<Role> roles = new ArrayList<>();

    public Employee() {}

    /**
     * <p>
     * This method will add new trainee.
     * </p>
     *
     * @param PrefixEnum.Prefix prefix. - This Variable has the name prefix.
     * @param String name. - This variable has employee's first name
     * @param String lastName. - This variable has employee's last name
     * @param String phoneNumber. - This variable has employee's phone number 
     * @param String email. - This variable has employee's Email. 
     * @param String address. - This variable has employee's address.
     * @param Date birthday. - This variable has employee's date of birth
     * @param int id. - This variable has employee's id.
     * @param Set<String> technicalLanguageKnown. - This variable has trainee's skills
     * @param float cgpa. - This variable has trainee's cgpa. 
     * @param String qualification. - This variable has trainee's qualification.
     * @param int batchNumber. - This variable has trainee's batch number.
     * @param int yearOfPass. - This variable has trainee's year Of passing.
     * @param String jobTitle. - This variable has trainer's jobtitle.
     * @param int teachingHours. - This variable has trainer's teaching hours.
     * @param String companyName. - This variable has trainer's privious company name
     *
     */
    public Employee(PrefixEnum.Prefix prefix, String firstName,String lastName,String phoneNumber,
        String email, String address, int id, Date birthday, Set<String> technicalLanguageKnown,
        float cgpa, String highestQualification, int batchNumber, int yearOfPass, String jobTitle, int teachingHours, String previousCompanyName) {

        this.prefix = prefix;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.address = address;
        this.id = id;
        this.birthday = birthday;
        this.technicalLanguageKnown = technicalLanguageKnown;
        this.cgpa = cgpa;
        this.highestQualification = highestQualification;
        this.batchNumber = batchNumber;
        this.yearOfPass = yearOfPass;
        this.jobTitle = jobTitle;
        this.salary = salary;
        this.teachingHours = teachingHours;
        this.previousCompanyName = previousCompanyName;
    }
 
    public PrefixEnum.Prefix getPrefix() {
        return prefix;
    }

    public void setPrefix(PrefixEnum.Prefix prefix) {
        this.prefix = prefix;
    }
    
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public Set<String> getTechnicalLanguageKnown() {
        return technicalLanguageKnown;
    }

    public void setTechnicalLanguageKnown(Set<String> technicalLanguageKnown) {
        this.technicalLanguageKnown = technicalLanguageKnown;
    }

    public float getCgpa() {
        return cgpa;
    }

    public void setCgpa(float cgpa) {
        this.cgpa = cgpa;
    }

    public String getHighestQualification() {
        return highestQualification;
    }

    public void setHighestQualification(String highestQualification) {
        this.highestQualification = highestQualification;
    }

    public int getBatchNumber() {
        return batchNumber;
    }

    public void setBatchNumber(int batchNumber) {
        this.batchNumber = batchNumber;
    }

    public int getYearOfPass() {
        return yearOfPass;
    }

    public void setYearOfPass(int yearOfPass) {
        this.yearOfPass = yearOfPass;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public int getTeachingHours() {
        return teachingHours;
    }

    public void setTeachingHours(int teachingHours) {
        this.teachingHours = teachingHours;
    }

    public String getPreviousCompanyName() {
        return previousCompanyName;
    }

    public void setPreviousCompanyName(String previousCompanyName) {
        this.previousCompanyName = previousCompanyName;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }
    
}