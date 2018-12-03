package repository;

import model.Colony;
import model.Entity;
import util.HibernateSessionFactoryUtil;

import java.util.List;

public class ColonyRepository extends Repository<Colony>{
    public ColonyRepository() {
        super(Colony.class);
    }
}
