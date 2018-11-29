package model;


import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@javax.persistence.Entity
@Table(name="entities")
public class Entity implements Serializable {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;

    private String name;

    private int strength;

    private int intelligence;

    private int militancy;

    private int diplomacy;

    @Column(name="birth_date")
    private int birthDate;

    private int enterprise;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "colony_id")
    private Colony colony;


    public Entity() {}

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

    public int getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(int birthDate) {
        this.birthDate = birthDate;
    }

    public int getEnterprise() {
        return enterprise;
    }

    public void setEnterprise(int enterprise) {
        this.enterprise = enterprise;
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
                birthDate == entity.birthDate &&
                enterprise == entity.enterprise &&
                Objects.equals(name, entity.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, strength, intelligence, militancy, diplomacy, birthDate, enterprise);
    }

    @Override
    public String toString() {
        return "Entity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", strength=" + strength +
                ", intelligence=" + intelligence +
                ", militancy=" + militancy +
                ", diplomacy=" + diplomacy +
                ", birthDate=" + birthDate +
                ", enterprise=" + enterprise +
                '}';
    }
}
