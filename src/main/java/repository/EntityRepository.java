package repository;

import model.Entity;
import util.HibernateSessionFactoryUtil;

import java.util.List;

public class EntityRepository {

    public Entity findById(int id) {
        return HibernateSessionFactoryUtil.getSessionFactory().openSession().get(Entity.class, id);
    }

    public void save(Entity user) {
        var session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        var transaction = session.beginTransaction();
        session.save(user);
        transaction.commit();
        session.close();
    }

    public void update(Entity user) {
        var session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        var transaction = session.beginTransaction();
        session.update(user);
        transaction.commit();
        session.close();
    }

    public void delete(Entity user) {
        var session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        var transaction = session.beginTransaction();
        session.delete(user);
        transaction.commit();
        session.close();
    }

    public List<Entity> findAll() {
        return HibernateSessionFactoryUtil.getSessionFactory().openSession().createQuery("From Entity").list();
    }
}