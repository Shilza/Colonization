package worker;

import Constants.Type;
import model.Entity;
import org.jetbrains.annotations.NotNull;

public class SkillsCalculator {
    private Entity entity;

    public SkillsCalculator(Entity entity) {
        this.entity = entity;
    }

    public Double addMainSkill() {
        return changeSkill(entity.getAddiction(), this::mainSkillCalculating);
    }

    public void subtractMainSkill() {
        changeSkill(entity.getAddiction(), this::subtractMainSkillCalculating);
    }

    public Double addSkill(Type type) {
        return changeSkill(type,
                changed -> {
                    if (entity.getVitality() < 80)
                        return changed + entity.getVitality() / 80.0;
                    else
                        return changed + 2;
                });
    }

    public void subtractSkill(Type type) {
        changeSkill(type, changed -> changed - 1);
    }

    private Double changeSkill(@NotNull Type type, Operating operation) {
        var skill = entity.getSkill();
        switch (type) {
            case MILITARY:
                return skill.setMilitary(operation.change(skill.getMilitary())).getMilitary() / 4.0;
            case TRADER:
                return skill.setTrading(operation.change(skill.getTrading())).getTrading() / 4.0;
            case ARTISAN:
                return skill.setCrafting(operation.change(skill.getCrafting())).getCrafting() / 4.0;
            case FARMER:
                return skill.setFarming(operation.change(skill.getFarming())).getFarming() / 4.0;
            default:
                return null;
        }
    }

    private interface Operating {
        double change(Integer changed);
    }

    private double mainSkillCalculating(Integer changed) {
        var result = 4 * (Math.random() / 2 + 0.5);

        if (entity.getColony().getType() == entity.getAddiction())
            result *= 1.5;
        if (entity.getVitality() < 80)
            result *= entity.getVitality() / 80.0;

        return changed + result;
    }

    private double subtractMainSkillCalculating(Integer changed) {
        var result = 5 * (Math.random() / 2 + 0.5);

        if (entity.getColony().getType() == entity.getAddiction())
            result *= 0.5;

        return changed - result;
    }
}
