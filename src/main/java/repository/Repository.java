package repository;

import util.HibernateSessionFactoryUtil;

import java.util.List;

public abstract class Repository <T>{
    private Class<T> type;

    protected Repository(Class<T> type){
        this.type = type;
    }

    public void save(T model) {
        var session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        var transaction = session.beginTransaction();
        session.save(model);
        transaction.commit();
        session.close();
    }

    public void update(T model) {
        var session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        var transaction = session.beginTransaction();
        session.update(model);
        transaction.commit();
        session.close();
    }

    public void delete(T model) {
        var session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        var transaction = session.beginTransaction();
        session.delete(model);
        transaction.commit();
        session.close();
    }

    public T findById(int id) {
        return HibernateSessionFactoryUtil.getSessionFactory().openSession().get(type, id);
    }

    public List<T> findAll() {
        return HibernateSessionFactoryUtil.getSessionFactory().openSession().createQuery("From " + type.getName()).list();
    }
}
