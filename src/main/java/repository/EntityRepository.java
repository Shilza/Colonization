package repository;

import model.Entity;

public class EntityRepository extends Repository<Entity>{
    public EntityRepository() {
        super(Entity.class);
    }
}