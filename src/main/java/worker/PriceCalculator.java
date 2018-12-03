package worker;

import model.Colony;
import model.PriceList;
import repository.PriceListRepository;

import java.util.List;

public class PriceCalculator {
    private static PriceListRepository repository = new PriceListRepository();

    static public PriceList calculate(List<Colony> colonies) {
        var prices = repository.findAll().get(0);
        return new PriceList()
                .setFood(calculateFoodPrice(colonies, prices))
                .setTools(calculateToolsPrice())
                .setWeapon(calculateWeaponPrice());
    }

    static private double calculateFoodPrice(List<Colony> colonies, PriceList prices) {
        double demand = 0;
        double offer = 0;

        //money isn't counts
        var arr = colonies.stream().mapToDouble(colony ->
                colony.getEntities().stream()
                        .mapToInt(entity -> entity.getAddiction().getFoodPerDay() * 2)
                        .reduce(Integer::sum)
                        .getAsInt() - colony.getFood()
        ).toArray();

        for (var value : arr) {
            if(value > 0)
                demand += value;
            else if(value < 0)
                offer -= value;
        }

        return prices.getFood() * demand / offer;
    }

    static private int calculateToolsPrice() {
        return 0;
    }

    static private int calculateWeaponPrice() {
        return 0;
    }
}
