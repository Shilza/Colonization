import repository.ColonyRepository;
import repository.EntityRepository;
import worker.ColonyWorker;
import worker.PriceCalculator;

public class Main {
    private static EntityRepository entityRepository = new EntityRepository();
    private static ColonyRepository colonyRepository = new ColonyRepository();

    public static void main(String[] args) {
        while(true){
            var colonies = colonyRepository.findAll();

            colonies.forEach(ColonyWorker::actions);

            colonies.forEach(colonyRepository::update);

            PriceCalculator.calculate(colonies);
        }
    }

    private static int time(){
        return (int) System.currentTimeMillis() / 1000;
    }
}
