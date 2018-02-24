package ru.pilot.tracks.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import ru.pilot.tracks.dto.TrackDto;

import javax.persistence.criteria.CriteriaBuilder;

public abstract class BaseDao {
    protected static CriteriaBuilder cb;
    protected static SessionFactory sf;
    protected static Session s;
    
    static {
        sf = HibernateUtil.getSessionFactory();
        s = sf.openSession();
        cb = s.getCriteriaBuilder();
    }
    
    protected Session newSession(){
        return s; //sf.openSession();
    }

    
    public void delete(Class<?> objClass, Long trackId){
        Session session = newSession();
        session.beginTransaction();
        session.remove(session.load(objClass, trackId));
        session.getTransaction().commit();
    }
}
