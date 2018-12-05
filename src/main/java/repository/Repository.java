package repository;

import model.Colony;
import util.HibernateSessionFactoryUtil;

import java.util.List;

public abstract class Repository <T>{
    private Class<T> type;

    protected Repository(Class<T> type){
        this.type = type;
//        HibernateSessionFactoryUtil.getSessionFactory().openSession();
    }

    public void save(T model) {
        var session = HibernateSessionFactoryUtil.getSessionFactory().getCurrentSession();
        var transaction = session.beginTransaction();
        session.save(model);
        transaction.commit();
    }

    public void update(T model) {
        var session = HibernateSessionFactoryUtil.getSessionFactory().getCurrentSession();
        var transaction = session.beginTransaction();
        session.update(model);
        transaction.commit();
    }

    public void delete(T model) {
        var session = HibernateSessionFactoryUtil.getSessionFactory().getCurrentSession();
        var transaction = session.beginTransaction();
        session.delete(model);
        transaction.commit();
    }

    public List<T> findAll() {
        var session = HibernateSessionFactoryUtil.getSessionFactory().getCurrentSession();
        var transaction = session.beginTransaction();
        var result = session.createQuery("From " + type.getName()).list();
        transaction.commit();
        return result;
    }
}
