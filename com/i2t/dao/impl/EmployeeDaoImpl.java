package com.i2t.dao.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import org.hibernate.Query;
import org.hibernate.HibernateException; 
import org.hibernate.Session;

import com.i2t.constants.EmployeeConstants;
import com.i2t.dao.EmployeeDao;
import com.i2t.employeeEnum.PrefixEnum;
import com.i2t.exception.EmployeeException;
import com.i2t.model.Employee;
import com.i2t.model.Role;
import com.i2t.util.DbConnection;

/**
 * <p>
 * This class Stores the information of Trainees.
 * </p>
 *
 */
public class EmployeeDaoImpl implements EmployeeDao {
    Logger logger = LogManager.getLogger(EmployeeDaoImpl.class);
    private static int initialLimit = 0;
    private static final int finalLimit = 5;

    /**
     * {@inheritDoc}
     *
     */
    public int insertEmployee(Employee employee) throws EmployeeException {
        int result = 0;
        Session session = DbConnection.getSession().openSession();
        try {
            session.beginTransaction();
            
            result = (Integer)session.save(employee);
            session.getTransaction().commit();
        } catch(HibernateException hibernateException) {
            logger.fatal(hibernateException);
            throw new EmployeeException(EmployeeConstants.FALSE_MESSAGE + hibernateException.getMessage());
        } finally {
            session.close();
        }
        return result;
    }

    /**
     * {@inheritDoc}
     *
     */
    public Employee updateEmployee(Employee employee) throws EmployeeException{
        Session session = DbConnection.getSession().openSession();
        try {
            session.beginTransaction();
            session.saveOrUpdate(employee);

            session.getTransaction().commit();
            return employee;
        } catch(HibernateException hibernateException) {
            logger.fatal(hibernateException);
            throw new EmployeeException(EmployeeConstants.FALSE_MESSAGE + hibernateException.getMessage());
        } finally {
            session.close();
        }
    }

    /**
     * {@inheritDoc}
     *
     */
    public Employee searchEmployeeById(int employeeId, String role) throws EmployeeException {
        Session session = DbConnection.getSession().openSession();
        Employee employee;
        try {
            session.beginTransaction();

            Query query = session.createQuery("select emp from Employee emp join emp.roles roles where roles.roleName = :name AND emp.id = :id");
            query.setParameter("name", role);
            query.setParameter("id", employeeId);
            employee = (Employee)query.uniqueResult();
            session.getTransaction().commit();
        } catch (HibernateException hibernateException) {
            logger.fatal(hibernateException);
            throw new EmployeeException(EmployeeConstants.FALSE_MESSAGE + hibernateException.getMessage());
        } finally {
            session.close();
        }
        return employee; 
    }

    /**
     * {@inheritDoc}
     *
     */
    public void deleteEmployeeById(int employeeId, String role) throws EmployeeException {
        Session session = DbConnection.getSession().openSession();
        Employee employee = searchEmployeeById(employeeId, role);
        try {
            session.beginTransaction();
            
            session.delete(employee);
            session.getTransaction().commit();
        } catch (HibernateException hibernateException) {
            logger.fatal(hibernateException);
            throw new EmployeeException(EmployeeConstants.FALSE_MESSAGE + hibernateException.getMessage());
        } finally {
            session.close();
        }
    }

    /**
     * {@inheritDoc}
     *
     */
    public List<Employee> retrieveEmployeesByRole(String role, int activePage) throws EmployeeException {
        Session session = DbConnection.getSession().openSession(); 
        List<Employee> employees;
        try {
            if (activePage == 1) {
                initialLimit += 5;
            } else if (activePage == 2 ){
                if (initialLimit != 0) {
                    initialLimit -= 5;
                }
            } else {
                initialLimit = 0;
            }
            Query query = session.createQuery("select emp from Employee emp join emp.roles roles where roles.roleName = :name");
            query.setParameter("name", role);
            query.setFirstResult(initialLimit);
            query.setMaxResults(finalLimit);
            employees = query.list();   
        } catch (HibernateException hibernateException) {
            logger.fatal(hibernateException);
            throw new EmployeeException(EmployeeConstants.FALSE_MESSAGE + hibernateException.getMessage());
        } finally {
            session.close();
        }
        return employees;
    }

    /**
     * {@inheritDoc}
     *
     */
    public Role getRoleByName(String roleName) throws EmployeeException {
        Session session = DbConnection.getSession().openSession();
        Role role;
        try {
            Query query = session.createQuery("select role from Role role where role.roleName = :name");
            query.setParameter("name", roleName);
            role = (Role)query.uniqueResult();
        } catch (HibernateException hibernateException) {
            logger.fatal(hibernateException);
            throw new EmployeeException(EmployeeConstants.FALSE_MESSAGE + hibernateException.getMessage());
        } finally {
            session.close();
        }
        return role;
    }

    /**
     * {@inheritDoc}
     *
     */
    public int getLastId() throws EmployeeException {
        Session session = DbConnection.getSession().openSession();
        int lastId = 0;
        try {
            Query query = session.createQuery("select max(emp.id) from Employee emp");
            lastId = (Integer)query.uniqueResult();
        } catch (HibernateException hibernateException) {
            logger.fatal(hibernateException);
            throw new EmployeeException(EmployeeConstants.FALSE_MESSAGE + hibernateException.getMessage());
        } finally {
            session.close();
        }
        return lastId;
    }
} 