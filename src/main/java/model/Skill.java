package model;


import Constants.Type;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.util.Objects;

@SuppressWarnings("unused")
@javax.persistence.Entity
@Table(name = "entities")
public class Skill {

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "entity_id")
    private Entity entity;

    private Integer military;

    private Integer trading;

    private Integer farming;

    private Integer crafting;

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
        return this;
    }

    public int getTrading() {
        return trading;
    }

    public Skill setTrading(int trading) {
        this.trading = trading >= 100 ? 100 : trading;
        return this;
    }

    public int getFarming() {
        return farming;
    }

    public Skill setFarming(int farming) {
        this.farming = farming >= 100 ? 100 : trading;
        return this;
    }

    public int getCrafting() {
        return crafting;
    }

    public Skill setCrafting(int crafting) {
        this.crafting = crafting >= 100 ? 100 : crafting;
        return this;
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
