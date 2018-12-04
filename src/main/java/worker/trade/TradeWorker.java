package worker.trade;

import model.Colony;
import model.Entity;
import repository.ColonyRepository;
import repository.PriceListRepository;

import java.util.AbstractMap;
import java.util.Comparator;
import java.util.HashMap;
import java.util.concurrent.atomic.AtomicInteger;

import static Constants.Type.MILITARY;
import static Constants.Type.TRADER;
import static worker.trade.Buyer.buyFood;
import static worker.trade.Buyer.buyTools;
import static worker.trade.Buyer.buyWeapon;
import static worker.trade.Seller.sellFood;
import static worker.trade.Seller.sellTools;
import static worker.trade.Seller.sellWeapon;

public class TradeWorker {

    public static void trading(Entity entity, double value) {
        var colony = entity.getColony();

        var tradePoints = new AtomicInteger((int) Math.round(30 +
                value * entity.getSkill().getTrading() / 5.0 * (colony.getType() == TRADER ? 1.25 : 1)));

        while (tradePoints.get() > 0) {
            var started = tradePoints.get();

            var foodPerDay = colony.getNeededFoodPerDay();

            var toolsPerDay = colony.getAliveEntities().size() * 2;

            var weaponPerDay = colony.getAliveEntities().size() * (colony.getType() == MILITARY ? 6 : 2);

            if (colony.getFood() < foodPerDay) {
                if (colony.getType() != MILITARY)
                    weaponPerDay = 0;
                toolsPerDay /= 2;
            }

            if (colony.getMoney() > 0) {
                if (colony.getFood() < foodPerDay * 2)
                    buyFood(tradePoints, colony, foodPerDay * 2 - colony.getFood(), entity);

                if (colony.getTools() < toolsPerDay && tradePoints.get() > 0)
                    buyTools(tradePoints, colony, toolsPerDay * 2 - colony.getTools(), entity);

                if (colony.getWeapon() < weaponPerDay && tradePoints.get() > 0)
                    buyWeapon(tradePoints, colony, weaponPerDay - colony.getWeapon(), entity);
            }


            if (colony.getFood() > foodPerDay * 2 && tradePoints.get() > 0)
                sellFood(tradePoints, colony, colony.getFood() - foodPerDay * 2, entity);

            if (colony.getTools() > toolsPerDay && tradePoints.get() > 0)
                sellTools(tradePoints, colony, colony.getTools() - toolsPerDay, entity);

            if (colony.getWeapon() > weaponPerDay && tradePoints.get() > 0)
                sellWeapon(tradePoints, colony, colony.getWeapon() - weaponPerDay, entity);

            if (started == tradePoints.get())
                break;
        }
    }

}
