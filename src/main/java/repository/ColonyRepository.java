package repository;

import model.Colony;
import model.Entity;
import util.HibernateSessionFactoryUtil;

import java.util.List;
import java.util.stream.Collectors;

public class ColonyRepository extends Repository<Colony>{
    public ColonyRepository() {
        super(Colony.class);
    }

    public List<Colony> findAllAliveColonies(){
        return findAll().stream().filter(Colony::isAlive).collect(Collectors.toList());
    }
}
