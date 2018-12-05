package worker;

import model.Colony;
import model.PriceList;
import repository.PriceListRepository;

import java.util.List;

public class PriceCalculator {
    private static PriceListRepository repository = new PriceListRepository();

    static public void calculate(List<Colony> colonies) {
        var prices = repository.findAll().get(0);
        var newPrices =  new PriceList()
                .setId(1)
                .setFood(1)
                .setTools(1)
                .setWeapon(1);
//                .setFood(calculateFoodPrice(colonies, prices))
//                .setTools(calculateToolsPrice(colonies, prices))
//                .setWeapon(calculateWeaponPrice());

        repository.update(newPrices);
    }

    static private double calculateFoodPrice(List<Colony> colonies, PriceList prices) {
        double demand = 0;
        double offer = 0;

        //money isn't counts
        var arr = colonies.stream().mapToDouble(colony ->
                colony.getNeededFoodPerDay() * 2 - colony.getFood()
        ).toArray();

        for (var value : arr) {
            if(value > 0)
                demand += value;
            else if(value < 0)
                offer -= value;
        }

        return offer == 0 ? prices.getFood() : prices.getFood() * demand / offer;
    }

    static private double calculateToolsPrice(List<Colony> colonies, PriceList prices) {

        double demand = 0;
        double offer = 0;

        //money isn't counts
        var arr = colonies.stream().mapToDouble(colony ->
                colony.getAliveEntities().size() * 2 - colony.getTools()
        ).toArray();

        for (var value : arr) {
            if(value > 0)
                demand += value;
            else if(value < 0)
                offer -= value;
        }

        return offer == 0 ? prices.getTools() : prices.getFood() * demand / offer;
    }

    //TODO
    static private int calculateWeaponPrice() {
        return 1;
    }
}
