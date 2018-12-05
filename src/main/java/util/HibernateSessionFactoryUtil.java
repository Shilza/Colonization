package util;

import model.Colony;
import model.Entity;
import model.PriceList;
import model.Skill;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

public class HibernateSessionFactoryUtil {
    private static SessionFactory sessionFactory;

    private HibernateSessionFactoryUtil() {}

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                var configuration = new Configuration().configure("hibernate.cfg.xml");
                configuration.addAnnotatedClass(Entity.class);
                configuration.addAnnotatedClass(Colony.class);
                configuration.addAnnotatedClass(Skill.class);
                configuration.addAnnotatedClass(PriceList.class);
                var builder = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
                sessionFactory = configuration.buildSessionFactory(builder.build());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return sessionFactory;
    }
}
