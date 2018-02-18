package ru.pilot.tracks.dao;

import org.hibernate.Session;

import javax.persistence.criteria.CriteriaBuilder;

public class BaseDao {
    protected Session session;
    protected CriteriaBuilder criteriaBuilder;
    protected BaseDao (){
        session = HibernateUtil.getSessionFactory().openSession();
        criteriaBuilder = session.getCriteriaBuilder();
    }
}
