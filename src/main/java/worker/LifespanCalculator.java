package worker;

import model.Colony;
import model.Entity;

import java.util.List;
import java.util.stream.Collectors;

public class LifespanCalculator {
    public static void calculate(List<Colony> colonies) {
        colonies.forEach(colony -> {
            var dead = colony.getEntities().stream()
                    .filter(Entity::isDead).collect(Collectors.toList());
            var skipped = dead.size() < 100 ? 0 : Math.round(dead.size() / 100.0 * 70);

            var sumAge = dead.stream()
                    .skip(skipped)
                    .mapToInt(Entity::getAge)
                    .reduce(Integer::sum);

            if(sumAge.isPresent())
                colony.setLifespan(sumAge.getAsInt() / (double) (dead.size() - skipped));
        });
    }
}
