package worker;

import model.Colony;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class LivingLevelCalculator {
    public static void calculate(List<Colony> colonies) {
        if(colonies.size() == 1)
            colonies.get(0).setLivingLevel(10);
        else if (colonies.size() > 1) {
            var indexes = colonies.stream()
                    .map(colony -> new HashMap.SimpleEntry<>(colony, calculateIndex(colony)))
                    .sorted((first, second) -> -Double.compare(first.getValue(), second.getValue()))
                    .collect(Collectors.toList());

            var indexPerLvl = indexes.size() > 0
                    ? (indexes.get(0).getValue() - indexes.get(indexes.size() - 1).getValue()) / 11
                    : 0;
            if (indexPerLvl > 0)
                indexes.forEach(pair -> pair.getKey()
                        .setLivingLevel((int) Math.round((pair.getValue() - indexes.get(indexes.size() - 1).getValue()) / indexPerLvl)));
        }
    }

    private static double calculateIndex(Colony colony) {
        var aliveEntitiesCount = (double) colony.getAliveEntities().size();

        return colony.getTools() / aliveEntitiesCount
                + colony.getMoney() / aliveEntitiesCount
                + colony.getWeapon() / aliveEntitiesCount / (colony.isWar() ? 3 : 1)
                + colony.getFood() / (double) colony.getNeededFoodPerDay();
    }
}
