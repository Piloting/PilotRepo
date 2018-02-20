package ru.pilot.tracks.dao;

import org.hibernate.Session;

import javax.persistence.criteria.CriteriaBuilder;

public abstract class BaseDao {
    protected static Session session;
    protected static CriteriaBuilder cb;
    
    static {
        session = HibernateUtil.getSessionFactory().openSession();
        cb = session.getCriteriaBuilder();
    }
}
