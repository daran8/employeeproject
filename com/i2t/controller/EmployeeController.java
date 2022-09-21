package com.i2t.controller;

import java.lang.Enum;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import com.i2t.constants.EmployeeConstants;
import com.i2t.dto.EmployeeDto;
import com.i2t.employeeEnum.PrefixEnum;
import com.i2t.exception.EmployeeException;
import com.i2t.mapper.EmployeeMapper;
import com.i2t.model.Employee;
import com.i2t.service.EmployeeService;
import com.i2t.service.impl.EmployeeServiceImpl;
import com.i2t.util.CommonUtil;
import com.i2t.util.DateUtil;
import com.i2t.util.EmailUtil;
import com.i2t.util.PhoneNumUtil;
import com.i2t.util.StringUtil;

/**
 * <p>
 * This class interact with the user get data
 * from the user and display result to the user.
 * </p>
 *
 */
public class EmployeeController {
     private static Logger logger = LogManager.getLogger(EmployeeController.class);
     EmployeeService employeeService = new EmployeeServiceImpl();

     public static void main(String args[]) {
        EmployeeController employeeController = new EmployeeController();
        int mainMenu = 0;
        boolean isValidMenu = true;
        Scanner scanner = new Scanner(System.in);
        do {
            logger.info("Please Select a Option To Add");
            while (isValidMenu) {
                logger.info("1.Trainer.\t2.Trainee.");
                String option = scanner.next();
                if (CommonUtil.isValidOption(option)) {
                    mainMenu = Integer.parseInt(option);
                    logger.info("You have Choosen Menu -- " + mainMenu);
                    isValidMenu = false;
                } else {
                    logger.warn(EmployeeConstants.INVALID_MENU_MESSAGE);
                }
            }
            isValidMenu = true;
            switch (mainMenu) {
                case 1:
                    try {
                        employeeController.trainerMenu();
                    } catch (EmployeeException employeeException) {
                        logger.fatal(employeeException.getMessage()); 
                        
                    }
                    break;
                case 2:
                    try {
                        employeeController.traineeMenu();
                    } catch (EmployeeException employeeException) {
                        logger.fatal(employeeException.getMessage());
                    }
                    break;
            }
         
        } while (mainMenu > 0 && mainMenu < 3);
    }
     
    /**
     * <p>
     * This method asks inputs from the user and transfer the data to service.
     * </p>
     * 
     * @throws EmployeeException. 
     */
    public void addTrainer() throws EmployeeException {
        EmployeeMapper employeeMapper = new EmployeeMapper();
        Set<String> traineeTechLanguage = new HashSet<>();
        float traineeCgpa = 0;
        String traineeQualification = "";
        int traineeBatchNumber = 0;
        int traineeYearOfPass = 0;

        logger.info("You Have Choosen Trainer Details");
        logger.info("* Fields Are Mandatory.");
 
        PrefixEnum.Prefix prefixEnum = PrefixEnum.Prefix.valueOf(namePrefix().toUpperCase());
        String employeeFirstName = employeeFirstName();
        String employeeLastName = employeeLastName();
        String employeePhoneNumber = employeePhoneNumber();
 
        String employeeEmail = employeeEmail();
        String employeeAddress = employeeAddress();
        Date trainerDateOfBirth = employeeDateOfBirth();
        String trainerJobTitle = employeeJobTitle();

        int trainerTeachingHours = employeeTeachingHours();
        String trainerCompanyName = employeeCompanyName();
        int trainerId = employeeService.generateId();
        logger.info("Please Note Your Id Number For Future Use   ID:"+ trainerId);

        EmployeeDto employeeDto = new EmployeeDto(prefixEnum, employeeFirstName, employeeLastName, employeePhoneNumber, employeeEmail, 
                                    employeeAddress, trainerDateOfBirth, trainerId, traineeTechLanguage, traineeCgpa, traineeQualification, 
                                    traineeBatchNumber, traineeYearOfPass, trainerJobTitle, trainerTeachingHours, trainerCompanyName);

        if (employeeService.addTrainer(employeeMapper.dtoToEntity(employeeDto)) == trainerId ) {
            logger.info(EmployeeConstants.VALID_INSERT_MESSAGE);
        } else { 
            logger.warn(EmployeeConstants.FALSE_MESSAGE);
        }   
    }
    
    /**
     * <p>
     * This method display all trainer information from service.
     * </p>
     *
     * @throws EmployeeException.
     */
    public void getTrainers() throws EmployeeException {
        Scanner scanner = new Scanner(System.in);
        EmployeeMapper employeeMapper = new EmployeeMapper();
        int activePage = 0;
        while (activePage < 3) {
            List<Employee> employees = employeeService.getTrainers(activePage);
            employees.forEach(employee -> logger.info(employeeMapper.entityToDto(employee)));
            if (employees.size() == 5) {
                logger.info("Enter 1.Next Page\t2.Previous Page.");
                String option = scanner.next();
                if (CommonUtil.isValidOption(option)) {
                    activePage = Integer.parseInt(option);
                }
            } else {
                logger.info("Enter 2.Previous Page.");
                String option = scanner.next();
                if (CommonUtil.isValidOption(option)) {
                    activePage = Integer.parseInt(option);
                }
            }
        }
    }

    /**
     * <p>
     * This method asks inputs from the user and transfer the data to service.
     * </p>
     *
     * @throws EmployeeException.
     */
    public void addTrainee() throws EmployeeException {
        EmployeeMapper employeeMapper = new EmployeeMapper();
        String trainerJobTitle = "";
        int trainerTeachingHours = 0;
        String trainerCompanyName = "";
        logger.info("* Fields Are Mandatory.");

        PrefixEnum.Prefix prefixEnum = PrefixEnum.Prefix.valueOf(namePrefix().toUpperCase());
        String workerFirstName = employeeFirstName();
        String workerLastName = employeeLastName();
        String workerPhoneNumber = employeePhoneNumber();
        
        String workerEmail = employeeEmail();
        String workerAddress = employeeAddress();
        Date traineeDateOfBirth = employeeDateOfBirth();
        Set<String> traineeTechLanguage = employeeLanguage();
        
        float traineeCgpa = employeeCgpa();
        String traineeQualification = employeeQualification();
        int traineeBatchNumber = employeeBatchNumber();
        int traineeYearOfPass = employeeYearOfPass(); 

        int traineeId = employeeService.generateId();

        logger.info("Please Note Your Id Number For Future Use   ID:"+ traineeId);
        EmployeeDto employeeDto = new EmployeeDto(prefixEnum, workerFirstName, workerLastName, workerPhoneNumber, 
                                               workerEmail, workerAddress, traineeDateOfBirth, traineeId, traineeTechLanguage, 
                                               traineeCgpa, traineeQualification, traineeBatchNumber, traineeYearOfPass, trainerJobTitle,
                                               trainerTeachingHours, trainerCompanyName);
        
        if (employeeService.addTrainee(employeeMapper.dtoToEntity(employeeDto)) == traineeId) {
            logger.info(EmployeeConstants.VALID_INSERT_MESSAGE);
        } else {
            logger.warn(EmployeeConstants.FALSE_MESSAGE);
        }
    }

    /**
     * <p>
     * This method display all trainee from service.
     * </p>
     * 
     * @throws EmployeeException.
     */ 
    public void getTrainees() throws EmployeeException {
        Scanner scanner = new Scanner(System.in);
        EmployeeMapper employeeMapper = new EmployeeMapper();
        int activePage = 0;
        while (activePage < 3) {
            List<Employee> employees = employeeService.getTrainees(activePage);
            employees.forEach(employee -> logger.info(employeeMapper.entityToDto(employee)));
            if (employees.size() == 5) {
                logger.info("Enter 1.Next Page\t2.Previous.");
                String option = scanner.next();
                if (CommonUtil.isValidOption(option)) {
                    activePage = Integer.parseInt(option);
                }
            } else {
                logger.info("Enter 2.Previous Page.");
                String option = scanner.next();
                if (CommonUtil.isValidOption(option)) {
                    activePage = Integer.parseInt(option);
                }
            }
        }
    }

    /**
     * <p>
     * This method prints a trainer using Id.
     * </p>
     *
     * @throws EmployeeException.
     */
    public void searchTrainerById() throws EmployeeException {
        EmployeeMapper employeeMapper = new EmployeeMapper();
        Scanner scanner = new Scanner(System.in);
        int trainerId = 0;
        logger.info("Enter Your Id Number To Search");
        String employeeId = scanner.next();
        if (CommonUtil.isValidNumber(employeeId)) {
            trainerId = Integer.parseInt(employeeId);
            logger.info(employeeMapper.entityToDto(employeeService.searchTrainerById(trainerId))); 
        }
    }

    /**
     * <p>
     * This method prints a trainee using Id.
     * </p>
     * 
     * @throws EmployeeException.
     */
    public void searchTraineeById() throws EmployeeException {
        EmployeeMapper employeeMapper = new EmployeeMapper();
        Scanner scanner = new Scanner(System.in);
        int traineeId = 0;
        logger.info("Enter Your Id Number To Search.");
        String employeeId = scanner.next();
        if (CommonUtil.isValidNumber(employeeId)) {
            traineeId = Integer.parseInt(employeeId);
            logger.info(employeeMapper.entityToDto(employeeService.searchTraineeById(traineeId)));
        }
    }

    /**
     * <p>
     * This method removes a trainer using Id.
     * </p>
     *
     * @throws EmployeeException.
     */
    public void deleteTrainerById() throws EmployeeException {
        Scanner scanner = new Scanner(System.in);
        int trainerId = 0;
        logger.info("Enter Your Id Number To Delete.");
        String employeeId = scanner.next();
        if (CommonUtil.isValidNumber(employeeId)) {
            trainerId = Integer.parseInt(employeeId);
            employeeService.deleteTrainerById(trainerId);
        }
    }

    /**
     * <p>
     * This method removes a trainee using Id.
     * </p>
     *
     * @throws EmployeeException.
     */
    public void deleteTraineeById() throws EmployeeException {
        Scanner scanner = new Scanner(System.in);
        int traineeId = 0;
        logger.info("Enter Your Id Number To Delete.");
        String employeeId = scanner.next();
        if (CommonUtil.isValidNumber(employeeId)) {
            traineeId = Integer.parseInt(employeeId);
            employeeService.deleteTraineeById(traineeId);
        }
    }

    /**
     * <p>
     * This method modifies a trainer.
     * </p>
     *
     * @throws EmployeeException.
     */
    public void updateTrainerById() throws EmployeeException {
        EmployeeMapper employeeMapper = new EmployeeMapper();
        Scanner scanner = new Scanner(System.in); 
        int trainerId = 0;

        Set<String> traineeTechLanguage = new HashSet<>();
        float traineeCgpa = 0;
        String traineeQualification = "";
        int traineeBatchNumber = 0;
        int traineeYearOfPass = 0;

        logger.info("Please Enter Your Id Number To Update");
        String employeeId = scanner.nextLine();
        if (CommonUtil.isValidNumber(employeeId)) {
            trainerId = Integer.parseInt(employeeId);
        }
        PrefixEnum.Prefix prefixEnum = PrefixEnum.Prefix.valueOf(updatePrefix().toUpperCase());
        String employeeFirstName = updateFirstName();
        String employeeLastName = employeeLastName();
        String employeePhoneNumber = updatePhoneNumber();
        String employeeEmail = updateEmail();
        String employeeAddress = employeeAddress();
        Date dateOfBirth = updateBirthday();
        String trainerJobTitle = updateJobTitle();
        int teachingHours = employeeTeachingHours();
        String trainerCompanyName = employeeCompanyName(); 
                    
        EmployeeDto employeeDto = new EmployeeDto(prefixEnum, employeeFirstName, employeeLastName, employeePhoneNumber, employeeEmail, 
                                                  employeeAddress, dateOfBirth, trainerId, traineeTechLanguage, traineeCgpa, traineeQualification, traineeBatchNumber, traineeYearOfPass,
                                                  trainerJobTitle, teachingHours, trainerCompanyName);
        logger.info(employeeService.updateTrainerById(employeeMapper.dtoToEntity(employeeDto)));
    }

    /**
     * <p>
     * This method modifies a trainee using Id.
     * </p>
     *
     * @throws EmployeeException.
     */
    public void updateTraineeById() throws EmployeeException {
        EmployeeMapper employeeMapper = new EmployeeMapper();
        int traineeId = 0;
        String trainerJobTitle = "";
        int trainerTeachingHours = 0;
        String trainerCompanyName = "";

        Scanner scanner = new Scanner(System.in);
        logger.info("Enter Your Id Number To Update");
        String employeeId = scanner.nextLine();
        if (CommonUtil.isValidNumber(employeeId)) {
            traineeId = Integer.parseInt(employeeId);
        }
        PrefixEnum.Prefix prefixEnum = PrefixEnum.Prefix.valueOf(updatePrefix().toUpperCase());
        String workerFirstName = updateFirstName();
        String workerLastName = employeeLastName();
        String workerPhoneNumber = updatePhoneNumber();
        String workerEmail = updateEmail();
        String workerAddress = employeeAddress();
        Date dateOfBirth = updateBirthday();
        Set<String> traineeTechLanguage = employeeLanguage();
        float cgpa = updateCgpa();
        String traineeQualification = updateQualification();
        int traineeBatchNumber = employeeBatchNumber();
        int yearOfPass = updateYearOfPass();
        
        EmployeeDto employeeDto = new EmployeeDto(prefixEnum, workerFirstName, workerLastName, workerPhoneNumber, workerEmail, workerAddress,
                                                  dateOfBirth, traineeId, traineeTechLanguage, cgpa, traineeQualification, 
                                                  traineeBatchNumber, yearOfPass, trainerJobTitle, trainerTeachingHours, trainerCompanyName);

        logger.info(employeeService.updateTraineeById(employeeMapper.dtoToEntity(employeeDto)));
    }

    /**
     * <p>
     * This method shows trainer menu.
     * </p>
     *
     * @throws EmployeeException.
     */
    private void trainerMenu() throws EmployeeException {
        Scanner scanner = new Scanner(System.in); 
        int trainerOptions = 0;
        do {
            logger.info("Please Select a Option");
            logger.info("1.Add Trainer\n2.View All Trainer\n3.Search A Trainer\n4.Delete A Trainer\n5.Update A Trainer\n6.Exit.");
            String options = scanner.nextLine();
            if (CommonUtil.isValidMenu(options)) {
                trainerOptions = Integer.parseInt(options);
                logger.info("You Have Choosen Menu --"+ trainerOptions);
            } else {
                logger.warn(EmployeeConstants.INVALID_MENU_MESSAGE);
            }
            switch (trainerOptions) {
                case 1:
                    addTrainer();
                    break;
                case 2:
                    getTrainers();
                    break;
                case 3:
                    searchTrainerById();
                    break;
                case 4:
                    deleteTrainerById();
                    break;
                case 5:
                    updateTrainerById();
                    break;
                default:
                    logger.info(EmployeeConstants.MAIN_MESSAGE);
            }    
        } while (trainerOptions < 6 && trainerOptions > 0);
    }

    /**
     * <p>
     * This method shows trainee menu.
     * </p>
     *
     * @throws EmployeeException.
     */
    private void traineeMenu() throws EmployeeException {
        Scanner scanner = new Scanner(System.in);
        int traineeOptions = 0;
        do {
            logger.info("Please Select a Option");
            logger.info("1.Add Trainee\n2.View All Trainee.\n3.Search A Trainee\n4.Delete A Trainee\n5.Update A Trainee.\n6.Exit.");
            String options = scanner.nextLine();
            if (CommonUtil.isValidMenu(options)) {
                traineeOptions = Integer.parseInt(options);
                logger.info("You Have Choosen Menu --"+ traineeOptions);
            } else {
                logger.warn(EmployeeConstants.INVALID_MENU_MESSAGE);
            }
            switch (traineeOptions) {
                case 1:
                    addTrainee();
                    break;
                case 2:
                    getTrainees();
                    break;
                case 3:
                    searchTraineeById();
                    break;
                case 4:
                    deleteTraineeById();
                    break;
                case 5:
                    updateTraineeById();
                    break;
                default:
                    logger.info(EmployeeConstants.MAIN_MESSAGE);  
            }            
        } while (traineeOptions > 0 && traineeOptions < 6);
    }

    /**
     * <p>
     * This method is to get prefix.
     * </p>
     *
     * @return String prefix.
     */
    private String namePrefix() {
        Scanner scanner = new Scanner(System.in);
        boolean isValidInput = true;
        while (isValidInput) {
            logger.info("Select A Prefix(MR\tMRS\tMISS\tDR.)*");
            String namePrefix = scanner.nextLine();
            PrefixEnum.Prefix prifixArray[] = PrefixEnum.Prefix.values();
            for (PrefixEnum.Prefix prefix : prifixArray) {
                if (prefix.toString().equals(namePrefix.toUpperCase())) {
                    isValidInput = false;
                    return prefix.toString();
                    
                }
            }
        }
        return EmployeeConstants.INVALID_MESSAGE;
    }

    /**
     * <p>
     * This method is to get first name.
     * </p>
     *
     * @return String employeeFirstName.
     */
    private String employeeFirstName() {
        Scanner scanner = new Scanner(System.in).useDelimiter("\n");
        boolean isValidInput = true;
        String employeeFirstName = "";
        logger.info("Enter Your First Name , Minimum 3 Charactor.*");
        while (isValidInput) {	
            employeeFirstName = scanner.nextLine();
            if (StringUtil.isValidName(employeeFirstName)) { 
                isValidInput = false;
            } else {
                logger.warn(EmployeeConstants.INVALID_MESSAGE);
            }
        }
        return employeeFirstName;
    }

    /**
     * <p>
     * This method is to get last name.
     * </p>
     *
     * @return String employeeLastName.
     */
    private String employeeLastName() {
        Scanner scanner = new Scanner(System.in).useDelimiter("\n");
        boolean isValidInput = true;
        String employeeLastName = "";
        while (isValidInput) {
            isValidInput = false;
            logger.info("Enter Your Last Name, Minimum 3 Letters.");
            employeeLastName = scanner.nextLine();
            if (!employeeLastName.isEmpty()) {
                if (!StringUtil.isValidName(employeeLastName)) {
                    isValidInput = true;
                    logger.warn(EmployeeConstants.INVALID_MESSAGE);
                }
            } 
        }
        return employeeLastName;
    }

    /**
     * <p>
     * This method is to get phone number.
     * </p>
     *
     * @return String employeePhoneNumber.
     */
    private String employeePhoneNumber() {
        Scanner scanner = new Scanner(System.in).useDelimiter("\n");
        boolean isValidInput = true;
        String phoneNumber = "";
        logger.info("Enter Your Phone Number Without +91.*");
        while (isValidInput) {
            phoneNumber = scanner.nextLine();
            if (PhoneNumUtil.isValidPhoneNum(phoneNumber)) {
                isValidInput = false;
            } else {
                logger.warn(EmployeeConstants.INVALID_MESSAGE);
            }
        }
        return phoneNumber;
    }

    /**
     * <p>
     * This method is to get email.
     * </p>
     *
     * @return String employeeEmail.
     */
    private String employeeEmail() {
        Scanner scanner = new Scanner(System.in).useDelimiter("\n");
        boolean isValidInput = true;
        String email = "";
        logger.info("Enter Your Email Id.*");
        while (isValidInput) {
            email = scanner.next();
            if (EmailUtil.isValidEmail(email)) {
                isValidInput = false;
            } else {
                logger.warn(EmployeeConstants.INVALID_MESSAGE);
            }
        }
        return email;
    }
   
    /**
     * <p>
     * This method is to get address.
     * </p>
     *
     * @return String employeeAddress.
     */
    private String employeeAddress() {
        Scanner scanner = new Scanner(System.in).useDelimiter("\n");
        logger.info("Enter Your Address.");
        String employeeAddress = scanner.nextLine();
        return employeeAddress;
    }

    /**
     * <p>
     * This method is to get date of birth.
     * </p>
     *
     * @return Date employeeDateOfBirth.
     * @throws EmployeeException.
     */
    private Date employeeDateOfBirth() throws EmployeeException {
        Scanner scanner = new Scanner(System.in).useDelimiter("\n");
        boolean isValidInput = true;
        Date employeeDateOfBirth = null;
        while (isValidInput) {
            logger.info("Enter Your Date Of Birth.(DD-MM-YYYY)*");
            String dateOfBirth = scanner.nextLine();
            boolean validateDate = DateUtil.isValidDate(dateOfBirth);
            if (validateDate) {
                if (DateUtil.calculateAge(dateOfBirth) >= EmployeeConstants.LOWER_AGE_LIMIT && DateUtil.calculateAge(dateOfBirth) <= EmployeeConstants.UPPER_AGE_LIMIT) {
                    employeeDateOfBirth = DateUtil.convertDate(dateOfBirth);
                    isValidInput = false;
                } else {
                    logger.warn(EmployeeConstants.INVALID_AGE_MESSAGE);
                }
            } else {
                logger.warn(EmployeeConstants.INVALID_DATE_MESSAGE);
            }           
        }
        return employeeDateOfBirth;    
    }
 
    /**
     * <p>
     * This method is to get job title.
     * </p>
     *
     * @return String trainerJobTitle.
     */
    private String employeeJobTitle() {
        Scanner scanner = new Scanner(System.in).useDelimiter("\n");
        boolean isValidInput = true;
        String trainerJobTitle = "";
        logger.info("Enter Your Job Title, Minimum Of 2 Charactor*");
        while (isValidInput) {
            trainerJobTitle = scanner.nextLine();
            if (StringUtil.isValidWord(trainerJobTitle)) {
                isValidInput = false;
            } else {
                logger.warn(EmployeeConstants.INVALID_MESSAGE);
            }
        }
        return trainerJobTitle;
    }

    /**
     * <p>
     * This method is to get hour(s) of teaching.
     * </p>
     *
     * @return int trainerTeachingHours.
     */
    private int employeeTeachingHours() {
        Scanner scanner = new Scanner(System.in).useDelimiter("\n");
        boolean isValidInput = true;
        int trainerTeachingHours = 0;
        logger.info("Enter Your Teaching Hours.");
        while (isValidInput) {
            String teachingHours = scanner.nextLine();
            isValidInput = false;
            if (!teachingHours.isEmpty()) {
                if (CommonUtil.isValidHours(teachingHours)) {
                    trainerTeachingHours = Integer.parseInt(teachingHours);
                } else {
                    logger.warn(EmployeeConstants.INVALID_MESSAGE);
                    isValidInput = true;
                }
            }   
        }
        return trainerTeachingHours; 
    }

    /**
     * <p>
     * This method is to get previous company name.
     * </p>
     *
     * @return String trainerCompanyName.
     */
    private String employeeCompanyName() {
        Scanner scanner = new Scanner(System.in).useDelimiter("\n");
        logger.info("Enter Your Privious Company Name.");
        String trainerCompanyName = scanner.nextLine();
        return trainerCompanyName;
    }

    /**
     * <p>
     * This method is to get skills.
     * </p>
     *     
     * @return Set<String> skills.
     */
    private Set<String> employeeLanguage() {
        Scanner scanner = new Scanner(System.in).useDelimiter("\n");
        Set<String> skills = new HashSet<>();
        boolean isValidInput = false;
        int traineeLanguageMenu = 1;
        do {
            logger.info("Enter your Known Languages.(*No Special Charactors & Numeric Values Are Allowed.*)");
            String traineeLanguage = scanner.nextLine();
            if (traineeLanguage.isEmpty()) {
                traineeLanguageMenu = 2;
            } else {
                if (StringUtil.isValidWord(traineeLanguage)) {
                    skills.add(traineeLanguage);
                } else {
                    logger.info("Not Saved Due To Invalid Input.");
                }
                logger.info("Select\n1.To Enter\n2.Continue.");
                while (!isValidInput) {
                    String languageMenu = scanner.next();
                    if (CommonUtil.isValidOption(languageMenu)) {
                        traineeLanguageMenu = Integer.parseInt(languageMenu); 
                        isValidInput = true;
                    } else {
                        logger.warn(EmployeeConstants.INVALID_MESSAGE);
                    }
                }
            }
        } while (traineeLanguageMenu == 1);
        return skills;
    }

    /**
     * <p>
     * This method is to get cgpa.
     * </p>
     *
     * @return float traineeCgpa.
     */
    private float employeeCgpa() {
        Scanner scanner = new Scanner(System.in).useDelimiter("\n");
        boolean isValidInput = true;
        float traineeCgpa = 0;
        while (isValidInput) {
            logger.info("Enter your Cgpa.*");
            String cgpa = scanner.nextLine();
            if (CommonUtil.isValidCgpa(cgpa)) {
                traineeCgpa = Float.parseFloat(cgpa);
                isValidInput = false;
            } else {
                logger.warn(EmployeeConstants.INVALID_MESSAGE);
            }
        }
        return traineeCgpa;
    }
 
    /**
     * <p>
     * This method is to get qualification.
     * </p>
     *
     * @return String traineeQualification.
     */ 
    private String employeeQualification() {
        Scanner scanner = new Scanner(System.in).useDelimiter("\n");
        boolean isValidInput = true;
        String traineeQualification = "";
        logger.info("Enter your Highest Qualification, Minimum 2 Charactor.*");
        while (isValidInput) {
            traineeQualification = scanner.nextLine();
            if (StringUtil.isValidWord(traineeQualification)) {
                isValidInput = false;
            } else {
                logger.warn(EmployeeConstants.INVALID_MESSAGE);
            }
        }
        return traineeQualification;
    }

    /**
     * <p>
     * This method is to get batch number.
     * </p>
     *
     * @return int traineeBatchNumber.
     */
    private int employeeBatchNumber() {
        Scanner scanner = new Scanner(System.in).useDelimiter("\n");
        boolean isValidInput = true;
        int traineeBatchNumber = 0;
        logger.info("Enter your Batch Number.");
        while (isValidInput) {         
            String batchNumber = scanner.nextLine();
            if (!batchNumber.isEmpty()) {
                if (CommonUtil.isValidNumber(batchNumber)) {
                    traineeBatchNumber = Integer.parseInt(batchNumber);
                    isValidInput = false;
                }
            } else {
                isValidInput = false;
            }
        }
        return traineeBatchNumber;
    }

    /**
     * <p>
     * This method is to get passedout year.
     * </p>
     *
     * @return int traineeYearOfPass.
     */
    private int employeeYearOfPass() {
        Scanner scanner = new Scanner(System.in).useDelimiter("\n");
        boolean isValidInput = true;
        int traineeYearOfPass = 0;
        logger.info("Enter your year of pass out.*");
        while (isValidInput) {
            String yearOfPass = scanner.nextLine();
            if (DateUtil.isValidYear(yearOfPass)) {
                traineeYearOfPass = Integer.parseInt(yearOfPass);
                isValidInput = false;
            } else {
                logger.info(EmployeeConstants.INVALID_MESSAGE);
            }
        }
        return traineeYearOfPass;
    }

    /**
     * <p>
     * This method is to update first name.
     * </p>
     *
     * @return String employeeFirstName.
     */
    private String updateFirstName() {
        boolean isValidInput = false;
        String employeeFirstName = "";
        Scanner scanner = new Scanner(System.in).useDelimiter("\n");
        while (!isValidInput) {
            isValidInput = true;
            logger.info("Enter Your First Name, Minimum 3 Letters.");
            employeeFirstName = scanner.nextLine();
            if (!employeeFirstName.isEmpty()) {
                if (!StringUtil.isValidName(employeeFirstName)) {
                    logger.info(EmployeeConstants.INVALID_MESSAGE);
                    isValidInput = false;   
                }
            }
        }
        return employeeFirstName;
    }

    /**
     * <p>
     * This method is to update Phone Number.
     * </p>
     *
     * @return String employeePhoneNumber.
     */
    private String updatePhoneNumber() {
        boolean isValidInput = false;
        String employeePhoneNumber = "";
        Scanner scanner = new Scanner(System.in).useDelimiter("\n");
        while (!isValidInput) {
            isValidInput = true;
            logger.info("Enter Your Phone Number.");
            employeePhoneNumber = scanner.nextLine();
            if (!employeePhoneNumber.isEmpty()) {
                if (!PhoneNumUtil.isValidPhoneNum(employeePhoneNumber)) {
                    isValidInput = false;
                    logger.info(EmployeeConstants.INVALID_MESSAGE);
                } 
            } 
        }
        return employeePhoneNumber;
    }

    /**
     * <p>
     * This method is to update email.
     * </p>
     *
     * @return String employeeEmail.
     */
    private String updateEmail() {
        boolean isValidInput = false;
        String employeeEmail = "";
        Scanner scanner = new Scanner(System.in).useDelimiter("\n");
        while (!isValidInput) { 
            isValidInput = true; 
            logger.info("Enter Your Email Id.");
            employeeEmail = scanner.nextLine();
            if (!employeeEmail.isEmpty()) {
                if (!EmailUtil.isValidEmail(employeeEmail)) {
                    isValidInput = false;
                    logger.info(EmployeeConstants.INVALID_MESSAGE);
                }
            } 
        }
        return employeeEmail;
    }

    /**
     * <p>
     * This method is to update birthday.
     * </p>
     *
     * @return Date dateOfBirth.
     */
    private Date updateBirthday() {
        boolean isValidInput = false;
        Date dateOfBirth = null;
        Scanner scanner = new Scanner(System.in).useDelimiter("\n");
        while (!isValidInput) {
            logger.info("Enter Your Date Of Birth.(DD/MM/YYYY)");
            String birthDay = scanner.nextLine();
            if (!birthDay.isEmpty()) {
                boolean validateDate = DateUtil.isValidDate(birthDay);
                if (validateDate) {
                    if (DateUtil.calculateAge(birthDay) >= EmployeeConstants.LOWER_AGE_LIMIT && DateUtil.calculateAge(birthDay) <= EmployeeConstants.UPPER_AGE_LIMIT) {
                        dateOfBirth = DateUtil.convertDate(birthDay);
                    }
                } else {
                    logger.warn(EmployeeConstants.INVALID_DATE_MESSAGE);
                }
            } else {
                isValidInput = true; 
            } 
        }
        return dateOfBirth;
    }

    /**
     * <p>
     * This method is to update cgpa.
     * </p>
     *
     * @return float cgpa.
     */
    private float updateCgpa() {
        boolean isValidInput = false;
        float cgpa = 0;
        Scanner scanner = new Scanner(System.in).useDelimiter("\n");
        while (!isValidInput) {
            logger.info("Enter your Cgpa.");
            String traineeCgpa = scanner.nextLine();
            if (!traineeCgpa.isEmpty()) {
                if (CommonUtil.isValidCgpa(traineeCgpa)) { 
                    cgpa = Float.parseFloat(traineeCgpa);
                    isValidInput = true;
                } else {
                    logger.warn(EmployeeConstants.INVALID_MESSAGE);
                }
            } 
        }
        return cgpa;
    }

    /**
     * <p>
     * This method is to update qualification.
     * </p>
     *
     * @return String traineeQualification.
     */
    private String updateQualification() {
        boolean isValidInput = false;
        String traineeQualification = "";
        Scanner scanner = new Scanner(System.in).useDelimiter("\n");
        while (!isValidInput) {
            isValidInput = true;
            logger.info("Enter your Highest Qualification .");
            traineeQualification = scanner.nextLine();
            if (!traineeQualification.isEmpty()) {
                if (!StringUtil.isValidWord(traineeQualification)) {
                    isValidInput = false;
                    logger.info(EmployeeConstants.INVALID_MESSAGE);
                }
            } 
        }
        return traineeQualification;
    }

    /**
     * <p>
     * This method is to update passedout year.
     * </p>
     *
     * @return int yearOfPass.
     */
    private int updateYearOfPass() {
        boolean isValidInput = false;
        int yearOfPass = 0;
        Scanner scanner = new Scanner(System.in).useDelimiter("\n");
        while (!isValidInput) {
            logger.info("Enter your year of passing.");
            String traineeYearOfPass = scanner.nextLine();
            if (!traineeYearOfPass.isEmpty()) {
                if (DateUtil.isValidYear(traineeYearOfPass)) {
                    yearOfPass = Integer.parseInt(traineeYearOfPass);
                    isValidInput = true;
                } else {
                    logger.warn(EmployeeConstants.INVALID_MESSAGE);
                }
            } 
        }
        return yearOfPass;
    }

    /**
     * <p>
     * This method is to update job title.
     * </p>
     *
     * @return String trainerJobTitle.
     */
    private String updateJobTitle() {
        boolean isValidInput = true;
        String trainerJobTitle = "";
        Scanner scanner = new Scanner(System.in).useDelimiter("\n");
        logger.info("Enter Your Job Title, Minimum 2 Letters.");
        while (isValidInput) {
            isValidInput = false;
            trainerJobTitle = scanner.nextLine();
            if (!trainerJobTitle.isEmpty()) {
                if (!StringUtil.isValidWord(trainerJobTitle)) {
                    logger.info(EmployeeConstants.INVALID_MESSAGE);
                    isValidInput = true;
                }
            }
        }
        return trainerJobTitle;
    }

    /**
     * <p>
     * This method is to update prefix.
     * </p>
     *
     * @return String prefix.
     */
    private String updatePrefix() {
        boolean isValidInput = true;
        Scanner scanner = new Scanner(System.in).useDelimiter("\n");
        logger.info("Select A Prefix(MR\tMRS\tMISS\tDR.)");
        while (isValidInput) {
            String namePrefix = scanner.nextLine();
            if (!namePrefix.isEmpty()) {
                PrefixEnum.Prefix prifixArray[] = PrefixEnum.Prefix.values();
                for (PrefixEnum.Prefix prefix : prifixArray) {
                    if (prefix.toString().equals(namePrefix.toUpperCase())) {
                        isValidInput = false;
                        return prefix.toString();  
                    }
                }
            }
        }
        return EmployeeConstants.INVALID_MESSAGE;
    }
}