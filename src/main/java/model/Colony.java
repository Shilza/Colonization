package model;

import Constants.Type;

import javax.persistence.*;
import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

@SuppressWarnings("unused")
@javax.persistence.Entity
@Table(name = "colonies")
public class Colony implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    private Type type;

    @Column(name = "water_availability")
    private int waterAvailability;

    @Column(name = "wood_availability")
    private int woodAvailability;

    @Column(name = "metal_availability")
    private int metalAvailability;

    private int fertility;

    private boolean war;

    @Column(name = "living_level")
    private int livingLevel;

    private int money;

    private int lifespan;

    @Column(name = "population_count")
    private int populationCount;

    private int age;

    private int experience;

    private int food;

    private int weapon;

    private int tools;

    private String location;

    @Override
    public String toString() {
        return "Colony{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", type=" + type +
                ", waterAvailability=" + waterAvailability +
                ", woodAvailability=" + woodAvailability +
                ", metalAvailability=" + metalAvailability +
                ", fertility=" + fertility +
                ", war=" + war +
                ", livingLevel=" + livingLevel +
                ", money=" + money +
                ", lifespan=" + lifespan +
                ", populationCount=" + populationCount +
                ", age=" + age +
                ", experience=" + experience +
                ", food=" + food +
                ", weapon=" + weapon +
                ", tools=" + tools +
                ", location='" + location + '\'' +
                ", color='" + color + '\'' +
                ", dead=" + dead +
                ", entities=" + entities +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Colony colony = (Colony) o;
        return id == colony.id &&
                waterAvailability == colony.waterAvailability &&
                woodAvailability == colony.woodAvailability &&
                metalAvailability == colony.metalAvailability &&
                fertility == colony.fertility &&
                war == colony.war &&
                livingLevel == colony.livingLevel &&
                money == colony.money &&
                lifespan == colony.lifespan &&
                populationCount == colony.populationCount &&
                age == colony.age &&
                experience == colony.experience &&
                food == colony.food &&
                weapon == colony.weapon &&
                tools == colony.tools &&
                dead == colony.dead &&
                Objects.equals(name, colony.name) &&
                type == colony.type &&
                Objects.equals(location, colony.location) &&
                Objects.equals(color, colony.color) &&
                Objects.equals(entities, colony.entities);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, type, waterAvailability, woodAvailability, metalAvailability, fertility, war, livingLevel, money, lifespan, populationCount, age, experience, food, weapon, tools, location, color, dead, entities);
    }

    private String color;

    public boolean isDead() {
        return dead;
    }

    public Colony setDead(boolean dead) {
        this.dead = dead;
        return this;
    }

    private boolean dead;

    @OneToMany(mappedBy = "colony", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<model.Entity> entities = new LinkedList<>();


    public Colony() {}

    public String getLocation() {
        return location;
    }

    public Colony setLocation(String location) {
        this.location = location;
        return this;
    }

    public String getColor() {
        return color;
    }

    public Colony setColor(String color) {
        this.color = color;
        return this;
    }

    public List<model.Entity> getEntities() {
        return entities;
    }

    public Colony setEntities(List<model.Entity> entities) {
        this.entities = entities;
        return this;
    }

    public int getId() {
        return id;
    }

    public Colony setId(int id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public Colony setName(String name) {
        this.name = name;
        return this;
    }

    public Type getType() {
        return type;
    }

    public Colony setType(int type) {
        this.type = Type.getType(type);
        return this;
    }

    public Colony setType(Type type){
        this.type = type;
        return this;
    }

    public int getWaterAvailability() {
        return waterAvailability;
    }

    public Colony setWaterAvailability(int waterAvailability) {
        this.waterAvailability = waterAvailability;
        return this;
    }

    public int getWoodAvailability() {
        return woodAvailability;
    }

    public Colony setWoodAvailability(int woodAvailability) {
        this.woodAvailability = woodAvailability;
        return this;
    }

    public int getMetalAvailability() {
        return metalAvailability;
    }

    public Colony setMetalAvailability(int metalAvailability) {
        this.metalAvailability = metalAvailability;
        return this;
    }

    public int getFertility() {
        return fertility;
    }

    public Colony setFertility(int fertility) {
        this.fertility = fertility;
        return this;
    }

    public boolean isWar() {
        return war;
    }

    public Colony setWar(boolean war) {
        this.war = war;
        return this;
    }

    public int getLivingLevel() {
        return livingLevel;
    }

    public Colony setLivingLevel(int livingLevel) {
        this.livingLevel = livingLevel;
        return this;
    }

    public int getMoney() {
        return money;
    }

    public Colony setMoney(int money) {
        this.money = money;
        return this;
    }

    public int getLifespan() {
        return lifespan;
    }

    public Colony setLifespan(int lifespan) {
        this.lifespan = lifespan;
        return this;
    }

    public int getPopulationCount() {
        return populationCount;
    }

    public Colony setPopulationCount(int populationCount) {
        this.populationCount = populationCount;
        return this;
    }

    public int getAge() {
        return age;
    }

    public Colony setAge(int age) {
        this.age = age;
        return this;
    }

    public int getExperience() {
        return experience;
    }

    public Colony setExperience(int experience) {
        this.experience = experience;
        return this;
    }

    public int getFood() {
        return food;
    }

    public Colony setFood(int food) {
        this.food = food;
        return this;
    }

    public int getWeapon() {
        return weapon;
    }

    public Colony setWeapon(int weapon) {
        this.weapon = weapon;
        return this;
    }

    public int getTools() {
        return tools;
    }

    public Colony setTools(int tools) {
        this.tools = tools;
        return this;
    }


    public void dead() {
        this.setDead(true);
    }
}
