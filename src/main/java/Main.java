import repository.ColonyRepository;
import worker.*;

public class Main {
    private static ColonyRepository colonyRepository = new ColonyRepository();

    public static void main(String[] args) throws InterruptedException{
        while(true){
            var colonies = colonyRepository.findAllAliveColonies();

            colonies.forEach(ColonyWorker::actions);

            PriceCalculator.calculate(colonies);

            LifespanCalculator.calculate(colonies);

            LivingLevelCalculator.calculate(colonies);

            colonies.forEach(colony -> colony.setMoney(colony.getMoney() + 2));

            PeopleGenerator.generate(colonies);

            colonies.forEach(colonyRepository::update);

            Thread.sleep(1000);
        }
    }
}
