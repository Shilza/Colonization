package worker;

import Constants.Type;
import model.Colony;
import model.Entity;
import model.Skill;
import repository.EntityRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;

import static Constants.Type.*;

public class PeopleGenerator {
    private static Random random = new Random(System.currentTimeMillis());
    private static EntityRepository repository = new EntityRepository();

    public static void generate(List<Colony> colonies) {
        colonies.stream().filter(colony -> colony.getAge() > 0 && colony.getAge() % 10 == 0)
                .forEach(PeopleGenerator::generate);
    }

    private static void generate(Colony colony) {
        var count = calculateGenerateCount(colony);
        for (var i = 0; i < count; i++)
            repository.save(create(colony));
    }

    private static int calculateGenerateCount(Colony colony) {
        var result = Math.round(colony.getAliveEntities().size() * (15 + colony.getLivingLevel()) / 100.0);
        return (int) result;
    }

    private static Entity create(Colony colony) {
        var entity = new Entity();
        entity.setStrength(generateIndex())
                .setLeadership(generateIndex())
                .setIntelligence(generateIndex())
                .setMilitancy(generateIndex())
                .setDiplomacy(generateIndex())
                .setEnterprise(generateIndex())
                .setColony(colony)
                .setSkill(new Skill().setEntity(entity));
        entity.setAddiction(generateAddiction(entity));
        return entity;
    }

    private static Type generateAddiction(Entity entity) {
        var arr = new HashMap<>(Map.ofEntries(
                Map.entry(MILITARY, generateMilitaryAddiction(entity)),
                Map.entry(FARMER, generateFarmerAddiction(entity)),
                Map.entry(TRADER, generateTraderAddiction(entity)),
                Map.entry(ARTISAN, generateArtisanAddiction(entity))
        ));

//        arr.put(entity.getColony().getType(),
//                (int) Math.round(arr.get(entity.getColony().getType()) * 1.1));

        var list = arr.entrySet().stream()
                .sorted((first, second) -> -Double.compare(first.getValue(), second.getValue()))
                .collect(Collectors.toList());

        return list.get(0).getKey();
    }

    private static int generateIndex() {
        return random.nextInt(10);
    }

    private static double generateMilitaryAddiction(Entity entity) {
        return entity.getMilitancy() / 100.0 * 50
                + entity.getStrength() / 100.0 * 20
                + entity.getLeadership() / 100.0 * 20
                + entity.getIntelligence() / 100.0 * 5
                + entity.getEnterprise() / 100.0 * 5
                - entity.getDiplomacy() / 100.0 * 30;
    }

    private static double generateFarmerAddiction(Entity entity) {
        return entity.getStrength() / 100.0 * 50
                + entity.getIntelligence() / 100.0 * 20
                + entity.getDiplomacy() / 100.0 * 20
                + entity.getEnterprise() / 100.0 * 5
                + entity.getLeadership() / 100.0 * 5
                - entity.getMilitancy() / 100.0 * 30;
    }

    private static double generateTraderAddiction(Entity entity) {
        return entity.getEnterprise() / 100.0 * 50
                + entity.getDiplomacy() / 100.0 * 20
                + entity.getIntelligence() / 100.0 * 20
                + entity.getStrength() / 100.0 * 5
                + entity.getLeadership() / 100.0 * 5
                - entity.getMilitancy() / 100.0 * 30;
    }

    private static double generateArtisanAddiction(Entity entity) {
        return entity.getIntelligence() / 100.0 * 50
                + entity.getEnterprise() / 100.0 * 20
                + entity.getStrength() / 100.0 * 20
                + entity.getDiplomacy() / 100.0 * 5
                + entity.getLeadership() / 100.0 * 5
                - entity.getMilitancy() / 100.0 * 25;
    }
}
