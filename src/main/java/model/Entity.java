package model;


import Constants.Type;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@SuppressWarnings("unused")
@javax.persistence.Entity
@Table(name = "entities")
public class Entity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private int strength;

    private int intelligence;

    private int militancy;

    private int diplomacy;

    private int leadership;

    private int age;

    private int enterprise;

    private Type addiction;

    @ColumnDefault("100")
    private int vitality = 100;

    private boolean dead;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "colony_id")
    private Colony colony;

    @OneToOne(mappedBy = "entity", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private Skill skill;


    public Entity() {}

    public int getId() {
        return id;
    }

    public int getStrength() {
        return strength;
    }

    public int getIntelligence() {
        return intelligence;
    }

    public int getMilitancy() {
        return militancy;
    }

    public Entity setMilitancy(int militancy) {
        this.militancy = militancy;
        return this;
    }

    public int getDiplomacy() {
        return diplomacy;
    }

    public int getAge() {
        return age;
    }

    public int getEnterprise() {
        return enterprise;
    }

    public boolean isDead() {
        return dead;
    }

    public Entity setDead(boolean dead) {
        this.dead = dead;
        return this;
    }

    public int getVitality() {
        return vitality;
    }

    public Entity setVitality(int vitality) {
        this.vitality = vitality >= 100 ? 100 : vitality;
        this.vitality = vitality <= 0 ? 0 : this.vitality;
        return this;
    }

    public Colony getColony() {
        return colony;
    }

    public Entity setColony(Colony colony) {
        this.colony = colony;
        return this;
    }

    public int getLeadership() {
        return leadership;
    }

    public Entity setLeadership(int leadership) {
        this.leadership = leadership;
        return this;
    }

    public Type getAddiction() {
        return addiction;
    }

    public Entity setAddiction(Type addiction) {
        this.addiction = addiction;
        return this;
    }

    public Entity setAddiction(int addiction) {
        this.addiction = Type.getType(addiction);
        return this;
    }

    public void dead() {
        setDead(true);
        setVitality(0);
    }


    public Skill getSkill() {
        return skill;
    }

    public Entity setSkill(Skill skill) {
        this.skill = skill;
        return this;
    }

    public Integer getSkillFromType(Type type) {
        switch (type) {
            case MILITARY:
                return skill.getMilitary();
            case TRADER:
                return skill.getTrading();
            case ARTISAN:
                return skill.getCrafting();
            case FARMER:
                return skill.getFarming();
            default:
                return null;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Entity entity = (Entity) o;
        return id == entity.id &&
                strength == entity.strength &&
                intelligence == entity.intelligence &&
                militancy == entity.militancy &&
                diplomacy == entity.diplomacy &&
                leadership == entity.leadership &&
                age == entity.age &&
                enterprise == entity.enterprise &&
                vitality == entity.vitality &&
                dead == entity.dead &&
                addiction == entity.addiction &&
                Objects.equals(colony, entity.colony) &&
                Objects.equals(skill, entity.skill);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, strength, intelligence, militancy, diplomacy, leadership, age, enterprise, addiction, vitality, dead, colony, skill);
    }

    @Override
    public String toString() {
        return "Entity{" +
                "id=" + id +
                ", strength=" + strength +
                ", intelligence=" + intelligence +
                ", militancy=" + militancy +
                ", diplomacy=" + diplomacy +
                ", leadership=" + leadership +
                ", age=" + age +
                ", enterprise=" + enterprise +
                ", addiction=" + addiction +
                ", vitality=" + vitality +
                ", dead=" + dead +
                ", colony=" + colony +
                ", skill=" + skill +
                '}';
    }

    public Entity setStrength(int strength) {
        this.strength = strength;
        return this;
    }

    public Entity setIntelligence(int intelligence) {
        this.intelligence = intelligence;
        return this;
    }

    public Entity setDiplomacy(int diplomacy) {
        this.diplomacy = diplomacy;
        return this;
    }

    public Entity setAge(int age) {
        this.age = age;
        return this;
    }

    public Entity setEnterprise(int enterprise) {
        this.enterprise = enterprise;
        return this;
    }
}
