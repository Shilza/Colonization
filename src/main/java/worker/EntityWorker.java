package worker;

import model.Colony;
import model.Entity;
import org.jetbrains.annotations.NotNull;

public class EntityWorker {

    public static void actions(Entity entity, Colony colony) {
        entity.setVitality(
                entity.getVitality() - foodCoefficient(entity, colony)
        );

        if (entity.getVitality() > 0)
            switch (entity.getAddiction()) {
                case MILITARY:
                    militaryDayCycle(entity, colony);
                case FARMER:
                    farmerDayCycle(entity, colony);
                case TRADER:
                    traderDayCycle(entity, colony);
                case ARTISAN:
                    artisanDayCycle(entity, colony);
            }
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



    private static void militaryDayCycle(Entity entity, Colony colony) {

    }


    private static void artisanDayCycle(Entity entity, Colony colony) {

    }

    private static void traderDayCycle(Entity entity, Colony colony) {

    }

    private static void farmerDayCycle(Entity entity, Colony colony) {


    }
}
