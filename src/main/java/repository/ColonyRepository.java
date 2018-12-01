package repository;

import model.Colony;
import model.Entity;
import util.HibernateSessionFactoryUtil;

import java.util.List;

public class ColonyRepository {
    public void save(Colony colony) {
        var session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        var transaction = session.beginTransaction();
        session.save(colony);
        transaction.commit();
        session.close();
    }

    public void update(Colony colony) {
        var session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        var transaction = session.beginTransaction();
        session.update(colony);
        transaction.commit();
        session.close();
    }

    public void delete(Colony colony) {
        var session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        var transaction = session.beginTransaction();
        session.delete(colony);
        transaction.commit();
        session.close();
    }

    public Colony findById(int id) {
        return HibernateSessionFactoryUtil.getSessionFactory().openSession().get(Colony.class, id);
    }

    public List<Colony> findAll() {
        return HibernateSessionFactoryUtil.getSessionFactory().openSession().createQuery("From Colony").list();
    }
}
