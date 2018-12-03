package worker;

import Constants.Type;
import model.Entity;
import model.Skill;
import org.jetbrains.annotations.NotNull;

public class SkillsCalculator {
    private Entity entity;

    public SkillsCalculator(Entity entity) {
        this.entity = entity;
    }

    public Integer addMainSkill() {
        return changeSkill(entity.getAddiction(), this::mainSkillCalculating);
    }

    public void subtractMainSkill() {
        changeSkill(entity.getAddiction(), this::subtractMainSkillCalculating);
    }

    public Integer addSkill(Type type) {
        return changeSkill(type,
                changed -> {
                    if (entity.getVitality() < 80)
                        return changed + (int) Math.round(entity.getVitality() / 80.0);
                    else
                        return changed + 1;
                });
    }

    public void subtractSkill(Type type) {
        changeSkill(type, changed -> changed - 1);
    }

    private Integer changeSkill(@NotNull Type type, Operating operation) {
        var skill = entity.getSkill();
        switch (type) {
            case MILITARY:
                return skill.setMilitary(operation.change(skill.getMilitary())).getMilitary();
            case TRADER:
                return skill.setTrading(operation.change(skill.getTrading())).getTrading();
            case ARTISAN:
                return skill.setCrafting(operation.change(skill.getCrafting())).getCrafting();
            case FARMER:
                return skill.setFarming(operation.change(skill.getFarming())).getFarming();
            default:
                return null;
        }
    }

    private interface Operating {
        int change(Integer changed);
    }

    private int mainSkillCalculating(Integer changed) {
        var result = 5 * (Math.random() / 2 + 0.5);

        if (entity.getColony().getType() == entity.getAddiction())
            result *= 1.5;
        if (entity.getVitality() < 80)
            result *= entity.getVitality() / 80.0;

        return (int) (changed + Math.round(result)) +
                entity.getSkillFromType(entity.getAddiction()) / 20;
    }

    private int subtractMainSkillCalculating(Integer changed) {
        var result = 5 * (Math.random() / 2 + 0.5);

        if (entity.getColony().getType() == entity.getAddiction())
            result *= 0.5;

        return changed - (int) Math.round(result);
    }
}
