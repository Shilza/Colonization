import repository.ColonyRepository;
import repository.EntityRepository;
import worker.ColonyWorker;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Optional;
import java.util.function.BinaryOperator;

public class Main {
    private static EntityRepository entityRepository = new EntityRepository();
    private static ColonyRepository colonyRepository = new ColonyRepository();

    public static void main(String[] args) {
        while(true){
            var colonies = colonyRepository.findAll();

            colonies.forEach(ColonyWorker::actions);

            colonies.forEach(colonyRepository::update);
        }
    }

    private static int time(){
        return (int) System.currentTimeMillis() / 1000;
    }
}
