package com.i2t.util;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.i2t.constants.EmployeeConstants;

public class DbConnection {
    private static SessionFactory sessionFactory = null;

    private DbConnection() {}
    
    public static SessionFactory getSession() {
        if (sessionFactory == null) {
            try {
                sessionFactory = new Configuration().configure("com/i2t/util/hibernate.cfg.xml").buildSessionFactory();
            } catch (HibernateException hibernateException) {
                System.err.println(hibernateException + EmployeeConstants.FALSE_MESSAGE);
            }
        }
        return sessionFactory;
    }    

    
}