package com.i2t.dto;

import java.util.Date;
import java.util.Set;

import com.i2t.employeeEnum.PrefixEnum;

/**
 * <p>
 * This class has receives data from controller.
 * </p>
 *
 */
public class EmployeeDto {
    private int id;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String email;
    private String address;
    private Date birthday;
    private Set<String> technicalLanguageKnown;
    private float cgpa;
    private String highestQualification;
    private int batchNumber;
    private int yearOfPass;
    private PrefixEnum.Prefix prefix;
    private String companyName;
    private int age;
    private int salary;
    private String jobTitle;
    private int teachingHours;
    private String previousCompanyName;

    public EmployeeDto() {}

    /**
     * <p>
     * This method will receive data of a trainee.
     * </p>
     *
     * @param PrefixEnum.Prefix prefix - This variable has name prefix.
     * @param String firstName. - This variable has trainee's first name
     * @param String lastName. - This variable has trainee's last name
     * @param String phoneNumber. - This variable has trainee's phone number 
     * @param String email. - This variable has trainee's Email. 
     * @param String address. - This variable has trainee's address.
     * @param Date birthday. - This variable has trainee's date of birth
     * @param int id. - This variable has trainee's id.
     * @param Set<String> technicalLanguageKnown. - This variable has trainee's skills
     * @param float cgpa. - This variable has trainee's cgpa. 
     * @param String qualification. - This variable has trainee's qualification.
     * @param int batchNumber. - This variable has trainee's batch number.
     * @param int yearOfPass. - This variable has trainee's year Of passing.
     * @param String jobTitle. - This variable has trainer's jobtitle.
     * @param int teachingHours. - This variable has trainer's teaching hours.
     * @param String previousCompanyName. - This variable has trainer's privious company name.
     *
     */
    public EmployeeDto(PrefixEnum.Prefix prefix, String firstName, String lastName, String phoneNumber, String email, 
                      String address, Date birthday, int id, Set<String> technicalLanguageKnown, 
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
        this.teachingHours = teachingHours;
        this.previousCompanyName = previousCompanyName;
        
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
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


    public String toString() {
        return "Id: "+this.getId()+"\nName: "+this.prefix+"."+this.getFirstName() +" "+ this.getLastName()+"\nPhoneNumber: +91 "+this.getPhoneNumber()+"\nAge: "+this.getAge()+"\nEmail: "+this.getEmail()+
               "\nCompany Name: "+this.getCompanyName()+"\nAddress: "+this.getAddress()+"\nKnownLanguage: "+String.join(", ", this.technicalLanguageKnown)+
               "\nCgpa: "+this.cgpa+"\nQualification: "+this.highestQualification+"\nbatchNumber: "+this.batchNumber+
               "\nYearofPass: "+this.yearOfPass+"\nJobTitle: "+this.jobTitle+
               "\nTeachingHours: "+this.teachingHours+"\nPreviousCompanyName: "+this.previousCompanyName;
    } 
}
