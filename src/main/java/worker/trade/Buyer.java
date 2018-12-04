package worker.trade;

import model.Colony;
import model.Entity;

import java.util.AbstractMap;
import java.util.Comparator;
import java.util.HashMap;
import java.util.concurrent.atomic.AtomicInteger;

import static Constants.Type.MILITARY;

class Buyer extends Trader{

    static void buyWeapon(AtomicInteger tradePoints, Colony colony, int needed, Entity entity) {
        var colonies = colonyRepository.findAllAliveColonies();
        final var price = priceListRepository.findAll().get(0).getWeapon() / calculatePriceCoefficient(entity);

        var newNeeded = new AtomicInteger(Math.min(needed, tradePoints.get()));

        var manyFoodColoniesStream = colonies.stream()
                .filter(colony1 -> colony1.getWeapon() > colony1.getAliveEntities().size() *
                        (colony1.getType() == MILITARY && colony1.isWar() ? 6 : 2));

        manyFoodColoniesStream
                .map(colony1 -> new HashMap.SimpleEntry<>(colony1, Math.min(
                        colony.getMoney() / price,
                        colony.getWeapon() - colony1.getAliveEntities().size() *
                                (colony1.getType() == MILITARY && colony1.isWar() ? 6 : 2)
                ))).filter(pair -> pair.getValue() > 0)
                .sorted(Comparator.comparingDouble(AbstractMap.SimpleEntry::getValue))
                .takeWhile(pair -> {
                    var seller = pair.getKey();

                    var bought = pair.getValue() >= newNeeded.get() ? newNeeded.get() : pair.getValue();

                    colony.setMoney((int) Math.round(colony.getMoney() - bought * price));
                    seller.setMoney((int) Math.round(seller.getMoney() + bought * price));

                    colony.setWeapon((int) Math.round(colony.getWeapon() + bought * price));
                    seller.setWeapon((int) Math.round(seller.getWeapon() - bought * price));

                    newNeeded.addAndGet((int) bought);

                    return newNeeded.get() > 0 && tradePoints.addAndGet((int) -bought) > 0;
                });
    }

    static void buyTools(AtomicInteger tradePoints, Colony colony, int needed, Entity entity) {
        var colonies = colonyRepository.findAllAliveColonies();
        final var price = priceListRepository.findAll().get(0).getTools() / calculatePriceCoefficient(entity);

        var newNeeded = new AtomicInteger(Math.min(needed, tradePoints.get()));

        var manyFoodColoniesStream = colonies.stream()
                .filter(colony1 -> colony1.getTools() > colony1.getAliveEntities().size() * 2);

        manyFoodColoniesStream
                .map(colony1 -> new HashMap.SimpleEntry<>(colony1, Math.min(
                        colony.getMoney() / price,
                        colony.getTools() - colony1.getAliveEntities().size() * 2
                ))).filter(pair -> pair.getValue() > 0)
                .sorted(Comparator.comparingDouble(AbstractMap.SimpleEntry::getValue))
                .takeWhile(pair -> {
                    var seller = pair.getKey();

                    var bought = pair.getValue() >= newNeeded.get() ? newNeeded.get() : pair.getValue();

                    colony.setMoney((int) Math.round(colony.getMoney() - bought * price));
                    seller.setMoney((int) Math.round(seller.getMoney() + bought * price));

                    colony.setTools((int) Math.round(colony.getTools() + bought * price));
                    seller.setTools((int) Math.round(seller.getTools() - bought * price));

                    newNeeded.addAndGet((int)bought);

                    return newNeeded.get() > 0 && tradePoints.addAndGet((int)-bought) > 0;
                });
    }

    static void buyFood(AtomicInteger tradePoints, Colony colony, int needed, Entity entity) {
        var colonies = colonyRepository.findAllAliveColonies();
        final var price = priceListRepository.findAll().get(0).getFood() / calculatePriceCoefficient(entity);

        var newNeeded = new AtomicInteger(Math.min(needed, tradePoints.get()));

        var manyFoodColoniesStream = colonies.stream()
                .filter(colony1 -> colony1.getFood() > colony1.getNeededFoodPerDay() * 2);

        manyFoodColoniesStream
                .map(colony1 -> new HashMap.SimpleEntry<>(colony1, Math.min(
                        colony.getMoney() / price,
                        colony.getFood() - colony.getNeededFoodPerDay()
                ))).filter(pair -> pair.getValue() > 0)
                .sorted(Comparator.comparingDouble(AbstractMap.SimpleEntry::getValue))
                .takeWhile(pair -> {
                    var seller = pair.getKey();

                    var bought = pair.getValue() >= newNeeded.get() ? newNeeded.get() : pair.getValue();

                    colony.setMoney((int) Math.round(colony.getMoney() - bought * price));
                    seller.setMoney((int) Math.round(seller.getMoney() + bought * price));

                    colony.setFood((int) Math.round(colony.getFood() + bought * price));
                    seller.setFood((int) Math.round(seller.getFood() - bought * price));

                    newNeeded.addAndGet((int)bought);

                    return newNeeded.get() > 0 && tradePoints.addAndGet((int)-bought) > 0;
                });
    }

}
