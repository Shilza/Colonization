package worker.trade;

import model.Entity;
import repository.ColonyRepository;
import repository.PriceListRepository;

class Trader {

    static ColonyRepository colonyRepository = new ColonyRepository();
    static PriceListRepository priceListRepository = new PriceListRepository();

    static double calculatePriceCoefficient(Entity entity) {
        return (1 + entity.getSkill().getTrading() / 100.0);
    }
}
