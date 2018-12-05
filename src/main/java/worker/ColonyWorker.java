package worker;

import model.Colony;
import model.Entity;
import org.jetbrains.annotations.NotNull;

import java.util.Comparator;
import java.util.List;
import java.util.Random;

public class ColonyWorker {

    private static Random random = new Random(System.currentTimeMillis());

    public static void actions(@NotNull Colony colony) {
        var entities = colony.getAliveEntities();

        if(entities.size() > 0) {
            for (var entity : entities)
                EntityWorker.actions(entity, colony);

            foodDistribution(colony);

            colony.setAge(colony.getAge() + 1);
        } else
            colony.dead();
    }

    private static void foodDistribution(@NotNull Colony colony) {
        var entities = colony.getAliveEntities();

        if(entities.size() > 0) {
            var neededFood = calculateNeededFood(entities);

            var colonyFood = colony.getFood();

            if (colonyFood < neededFood) {
                entities.sort(Comparator.comparingInt(entity -> entity.getAddiction().getFoodPerDay()));
                int foodForOnePerson = colonyFood / entities.size();

                for (var i = 0; i < entities.size(); i++) {
                    var entity = entities.get(i);
                    if (foodForOnePerson >= entity.getAddiction().getFoodPerDay()) {
                        var eaten = entity.getAddiction().getFoodPerDay();
                        colonyFood -= eaten;
                        foodForOnePerson = colonyFood / (entities.size() - i);
                        entity.setVitality(entity.getVitality() + eaten + livingLevelCoefficient(colony));
                    } else {
                        colonyFood -= foodForOnePerson;
                        entity.setVitality(entity.getVitality() + foodForOnePerson);
                    }
                }
            } else
                entities.forEach(entity -> entity.setVitality(
                        entity.getVitality() + entity.getAddiction().getFoodPerDay() + colony.getLivingLevel() / 2));

            colony.setFood(colony.getFood() - neededFood);
        }
    }

    private static int calculateNeededFood(List<Entity> entities) {
        return entities.stream()
                .mapToInt(entity -> entity.getAddiction().getFoodPerDay())
                .reduce((first, second) -> first + second).getAsInt();
    }

    private static int livingLevelCoefficient(@NotNull Colony colony) {
        var level = colony.getLivingLevel();
        return level >= 5 && random.nextBoolean() ? level : 0;
    }
}
