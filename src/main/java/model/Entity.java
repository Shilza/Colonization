package model;


import Constants.Type;

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

    private String name;

    private int strength;

    private int intelligence;

    private int militancy;

    private int diplomacy;

    private int leadership;

    private int age;

    private int enterprise;

    private Type addiction;

    private int vitality;

    @Override
    public String toString() {
        return "Entity{" +
                "id=" + id +
                ", name='" + name + '\'' +
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
                '}';
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
                Objects.equals(name, entity.name) &&
                addiction == entity.addiction &&
                Objects.equals(colony, entity.colony);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, strength, intelligence, militancy, diplomacy, leadership, age, enterprise, addiction, vitality, dead, colony);
    }

    private boolean dead;

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
        return this;
    }

    public Colony getColony() {
        return colony;
    }

    public Entity setColony(Colony colony) {
        this.colony = colony;
        return this;
    }


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "colony_id")
    private Colony colony;


    public Entity() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getStrength() {
        return strength;
    }

    public void setStrength(int strength) {
        this.strength = strength;
    }

    public int getIntelligence() {
        return intelligence;
    }

    public void setIntelligence(int intelligence) {
        this.intelligence = intelligence;
    }

    public int getMilitancy() {
        return militancy;
    }

    public void setMilitancy(int militancy) {
        this.militancy = militancy;
    }

    public int getDiplomacy() {
        return diplomacy;
    }

    public void setDiplomacy(int diplomacy) {
        this.diplomacy = diplomacy;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getEnterprise() {
        return enterprise;
    }

    public void setEnterprise(int enterprise) {
        this.enterprise = enterprise;
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
    }
}
