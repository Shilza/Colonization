import repository.ColonyRepository;

public class Main {
    public static void main(String[] args){
        var repository = new ColonyRepository();

        System.out.println(repository.findById(2).getEntities().get(0).getName());

//        var repository = new ColonyRepository();

//        repository.save(new Colony()
//                .setAge(1)
//                .setExperience(1)
//                .setFertility(1)
//                .setFood(1)
//                .setLifespan(5)
//                .setLivingLevel(1)
//                .setMetalAvailability(1)
//                .setMoney(1)
//                .setName("sis")
//                .setPopulationCount(1)
//                .setTools(1)
//                .setType(1)
//                .setWar(false)
//                .setWaterAvailability(1)
//                .setWeapon(1)
//                .setWoodAvailability(2));

//        var repository = new EntityRepository();
//
//        var entity = new Entity();
//
//        entity.setName("s");
//        entity.setStrength(1);
//        entity.setMilitancy(1);
//        entity.setIntelligence(1);
//        entity.setEnterprise(1);
//        entity.setDiplomacy(1);
//        entity.setBirthDate(1124441);
//
//        repository.save(entity);
    }
}
