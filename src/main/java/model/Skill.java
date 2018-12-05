package model;


import org.hibernate.annotations.ColumnDefault;
import org.jetbrains.annotations.Contract;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@SuppressWarnings("unused")
@javax.persistence.Entity
@Table(name = "skills")
public class Skill implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToOne
    @JoinColumn(name = "entity_id")
    private Entity entity;

    @ColumnDefault("0")
    private Integer military = 0;

    @ColumnDefault("0")
    private Integer trading = 0;

    @ColumnDefault("0")
    private Integer farming = 0;

    @ColumnDefault("0")
    private Integer crafting = 0;

    public Skill() {}

    public Entity getEntity() {
        return entity;
    }

    public Skill setEntity(Entity entity) {
        this.entity = entity;
        return this;
    }

    public int getMilitary() {
        return military;
    }

    public Skill setMilitary(int military) {
        this.military = military >= 100 ? 100 : military;
        this.military = this.military <= 0 ? 0 : this.military;
        return this;
    }

    public Skill setMilitary(double military){
        return setMilitary((int) Math.round(military));
    }

    public int getTrading() {
        return trading;
    }

    public Skill setTrading(int trading) {
        this.trading = trading >= 100 ? 100 : trading;
        this.trading = this.trading <= 0 ? 0 : this.trading;
        return this;
    }

    public Skill setTrading(double trading) {
        return setTrading((int) Math.round(trading));
    }

    public int getFarming() {
        return farming;
    }

    public Skill setFarming(int farming) {
        this.farming = farming >= 100 ? 100 : farming;
        this.farming = this.farming <= 0 ? 0 : this.farming;
        return this;
    }

    public Skill setFarming(double farming){
        return setFarming((int) Math.round(farming));
    }

    public int getCrafting() {
        return crafting;
    }

    public Skill setCrafting(int crafting) {
        this.crafting = crafting >= 100 ? 100 : crafting;
        this.crafting = this.crafting <= 0 ? 0 : this.crafting;
        return this;
    }

    public Skill setCrafting(double crafting) {
        return setCrafting((int)Math.round(crafting));
    }


    @Contract(value = "null -> false", pure = true)
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Skill skill = (Skill) o;
        return military == skill.military &&
                trading == skill.trading &&
                farming == skill.farming &&
                crafting == skill.crafting &&
                Objects.equals(entity, skill.entity);
    }

    @Override
    public int hashCode() {
        return Objects.hash(entity, military, trading, farming, crafting);
    }

    @Override
    public String toString() {
        return "Skill{" +
                "entity=" + entity +
                ", military=" + military +
                ", trading=" + trading +
                ", farming=" + farming +
                ", crafting=" + crafting +
                '}';
    }
}
