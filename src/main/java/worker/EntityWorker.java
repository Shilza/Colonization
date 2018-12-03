package worker;

import Constants.Type;
import model.Colony;
import model.Entity;
import org.jetbrains.annotations.NotNull;

import java.util.AbstractMap.SimpleEntry;
import java.util.Arrays;
import java.util.Random;

import static Constants.Type.MILITARY;
import static Constants.Type.TRADER;

public class EntityWorker {

    private static Random random = new Random(System.currentTimeMillis());

    public static void actions(Entity entity, Colony colony) {
        entity.setVitality(
                entity.getVitality() - foodCoefficient(entity, colony)
        );

        work(entity);

        if (entity.getVitality() > 0){}
        else
            entity.dead();
    }

    private static int foodCoefficient(@NotNull Entity entity, @NotNull Colony colony) {
        return entity.getAddiction().getFoodPerDay() * coefficient(entity, colony);
    }

    private static int coefficient(@NotNull Entity entity, @NotNull Colony colony) {
        int lifespan = colony.getLifespan() / 100 * 80;
        return entity.getAge() <= lifespan ? 1 : 500 / lifespan / entity.getAddiction().getFoodPerDay() + 1;
    }

    private static void work(Entity entity) {
        var pair = skillsUpdating(entity);

        var colony = entity.getColony();
        switch (pair.getKey()) {
            case FARMER:
                colony.setFood(colony.getFood() + foodIncreasing(colony, pair.getValue()));
                colony.setTools(colony.getTools() - 1);
                break;
            case ARTISAN:
                var increasing = toolsIncreasing(colony, pair.getValue()) - 1;
                colony.setTools(colony.getTools() + (int)Math.round(increasing / 2.0));
                colony.setWeapon(colony.getWeapon() + increasing - (int)Math.round(increasing / 2.0));
                break;
            case TRADER:
                trading(entity, pair.getValue());
                colony.setTools(colony.getTools() - 1);
                break;
            case MILITARY:
                break;
        }
    }

    private static void trading(Entity entity, int value) {
        var colony = entity.getColony();
        var foodPerDay = colony.getEntities().stream()
                .mapToInt(entity1 -> entity1.getAddiction().getFoodPerDay())
                .reduce((first, second) -> first + second)
                .getAsInt();

        var toolsPerDay = colony.getEntities().size();

        var weaponPerDay = colony.getWeapon() * (colony.getType() == MILITARY ? 5 : 1);

        var tradePoints = (int)Math.round(30 +
                value * entity.getSkill().getTrading() / 5.0 * (colony.getType() == TRADER ? 1.25 : 1));

        if(colony.getMoney() > 0) {
            if (colony.getFood() < foodPerDay * 2 && tradePoints > 0)
                tradePoints -= buyFood(tradePoints);

            if (colony.getTools() < toolsPerDay * 2 && tradePoints > 0)
                tradePoints -= buyTools(tradePoints);

            if (colony.getWeapon() < weaponPerDay && tradePoints > 0)
                tradePoints -= buyWeapon(tradePoints);
        }

        if(colony.getFood() > foodPerDay * 2 && tradePoints > 0)
            tradePoints -= sellFood(tradePoints);

        if(colony.getTools() > toolsPerDay * 2 && tradePoints > 0)
            tradePoints -= sellTools(tradePoints);

        if(colony.getWeapon() > weaponPerDay && tradePoints > 0)
            tradePoints -= sellWeapon(tradePoints);

    }

    private static int sellWeapon(int tradePoints) {
        return 0;
    }

    private static int sellTools(int tradePoints) {
        return 0;
    }

    private static int sellFood(int tradePoints) {
        return 0;
    }

    private static int buyWeapon(int tradePoints) {
        return 0;
    }

    private static int buyTools(int tradePoints) {
        return 0;
    }

    private static int buyFood(int tradePoints) {

        return 0;
    }


    private static SimpleEntry<Type, Integer> skillsUpdating(Entity entity) {
        var rand = random.nextInt(9);
        var types = (Type[]) Arrays.stream(Type.values())
                .filter(type -> type != entity.getAddiction())
                .toArray();

        var calculator = new SkillsCalculator(entity);

        return rand <= 6
                ? mainSkillUpdating(calculator, types, entity.getAddiction())
                : skillUpdating(calculator, types, types[9 - rand]);
    }

    private static SimpleEntry<Type, Integer> mainSkillUpdating(SkillsCalculator calculator, Type[] types, Type type) {
        var value = calculator.addMainSkill();
        for (var innerType : types)
            calculator.subtractSkill(innerType);

        return new SimpleEntry<>(type, value);
    }

    private static SimpleEntry<Type, Integer> skillUpdating(SkillsCalculator calculator, Type[] types, Type type) {
        var value = calculator.addSkill(type);
        calculator.subtractMainSkill();
        Arrays.stream(types)
                .filter(type1 -> type1 != type)
                .forEach(calculator::subtractSkill);

        return new SimpleEntry<>(type, value);
    }


    private static int foodIncreasing(@NotNull Colony colony, int value) {

        final int minimumFoodIncreasing = 40;

        var toolsCoefficient = colony.getTools() > 0 ? 1.25 : 1;

        return (int) Math.round(minimumFoodIncreasing
                + 2 * (colony.getFertility() + colony.getWaterAvailability()) * value * toolsCoefficient);
    }

    private static int toolsIncreasing(@NotNull Colony colony, int value) {

        final int minimumToolsIncreasing = 2;

        var toolsCoefficient = colony.getTools() > 0 ? 1.25 : 1;

        return (int) Math.round(minimumToolsIncreasing + value * toolsCoefficient / 12
                * (colony.getMetalAvailability() + colony.getWoodAvailability() + colony.getWaterAvailability()));
    }

}
