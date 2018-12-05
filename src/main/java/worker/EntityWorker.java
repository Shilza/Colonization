package worker;

import Constants.Type;
import model.Colony;
import model.Entity;
import org.jetbrains.annotations.NotNull;
import worker.trade.TradeWorker;

import java.util.AbstractMap.SimpleEntry;
import java.util.Arrays;
import java.util.Random;

public class EntityWorker {

    private static Random random = new Random(System.currentTimeMillis());

    public static void actions(Entity entity, Colony colony) {
        entity.setVitality(
                (int) Math.round(entity.getVitality() - foodCoefficient(entity, colony))
        );

        if (entity.getVitality() > 0) {
            entity.setAge(entity.getAge() + 1);
            work(entity);
        } else
            entity.dead();
    }

    private static double foodCoefficient(@NotNull Entity entity, @NotNull Colony colony) {
        return entity.getAddiction().getFoodPerDay() * ageCoefficient(entity, colony);
    }

    //TODO
    private static double ageCoefficient(@NotNull Entity entity, @NotNull Colony colony) {
        double lifespan = colony.getLifespan() / 100.0 * 80.0;
        return entity.getAge() <= lifespan ? 1 : (500 / lifespan / entity.getAddiction().getFoodPerDay() + 1);
    }

    private static void work(Entity entity) {
        var pair = skillsUpdating(entity);

        var colony = entity.getColony();
        switch (pair.getKey()) {
            case FARMER:
                colony.setFood((int) (colony.getFood() + foodIncreasing(colony, pair.getValue())));
                colony.setTools(colony.getTools() - 1);
                break;
            case ARTISAN:
                var increasing = toolsIncreasing(colony, pair.getValue()) - 1;
                colony.setTools(colony.getTools() + (int) Math.round(increasing / 2.0));
                colony.setWeapon((int) (colony.getWeapon() + increasing - (int) Math.round(increasing / 2.0)));
                break;
            case TRADER:
                TradeWorker.trading(entity, pair.getValue());
                colony.setTools(colony.getTools() - 1);
                break;
            case MILITARY:
                break;
        }
    }


    private static SimpleEntry<Type, Double> skillsUpdating(Entity entity) {
        var rand = random.nextInt(9);
        var types = Arrays.stream(Type.values())
                .filter(type -> type != entity.getAddiction())
                .toArray(Type[]::new);

        var calculator = new SkillsCalculator(entity);

        return rand <= 6
                ? mainSkillUpdating(calculator, types, entity.getAddiction())
                : skillUpdating(calculator, types, types[9 - rand]);
    }

    private static SimpleEntry<Type, Double> mainSkillUpdating(SkillsCalculator calculator, Type[] types, Type type) {
        var value = calculator.addMainSkill();
        for (var innerType : types)
            calculator.subtractSkill(innerType);

        return new SimpleEntry<>(type, value);
    }

    private static SimpleEntry<Type, Double> skillUpdating(SkillsCalculator calculator, Type[] types, Type type) {
        var value = calculator.addSkill(type);
        calculator.subtractMainSkill();
        Arrays.stream(types)
                .filter(type1 -> type1 != type)
                .forEach(calculator::subtractSkill);

        return new SimpleEntry<>(type, value);
    }


    private static double foodIncreasing(@NotNull Colony colony, double value) {
        var toolsCoefficient = colony.getTools() > 0 ? 1.25 : 1;

        return 25 + (colony.getFertility() + colony.getWaterAvailability()) / 2.0 * value * toolsCoefficient;
    }

    private static double toolsIncreasing(@NotNull Colony colony, double value) {

        var toolsCoefficient = colony.getTools() > 0 ? 1.25 : 1;

        return 1 + value * toolsCoefficient / 12
                * (colony.getMetalAvailability() + colony.getWoodAvailability() + colony.getWaterAvailability());
    }
}
