package model;

import javax.persistence.*;
import javax.persistence.Entity;
import java.io.Serializable;
import java.util.Objects;

@SuppressWarnings("unused")
@Entity
@Table(name = "price_list")
public class PriceList implements Serializable {
    public int getId() {
        return id;
    }

    public PriceList setId(int id) {
        this.id = id;
        return this;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private double weapon;
    private double tools;
    private double food;

    public PriceList(){}

    public double getWeapon() {
        return weapon;
    }

    public PriceList setWeapon(double weapon) {
        this.weapon = weapon;
        return this;
    }

    public double getTools() {
        return tools;
    }

    public PriceList setTools(double tools) {
        this.tools = tools;
        return this;
    }

    public double getFood() {
        return food;
    }

    public PriceList setFood(double food) {
        this.food = food;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PriceList priceList = (PriceList) o;
        return weapon == priceList.weapon &&
                tools == priceList.tools &&
                food == priceList.food;
    }

    @Override
    public int hashCode() {
        return Objects.hash(weapon, tools, food);
    }

    @Override
    public String toString() {
        return "PriceList{" +
                "weapon=" + weapon +
                ", tools=" + tools +
                ", food=" + food +
                '}';
    }
}
