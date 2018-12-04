package worker.trade;

import model.Colony;
import model.Entity;

import java.util.Comparator;
import java.util.HashMap;
import java.util.concurrent.atomic.AtomicInteger;

import static Constants.Type.MILITARY;

class Seller extends Trader{

    static void sellWeapon(AtomicInteger tradePoints, Colony colony, int surplus, Entity entity) {
        var colonies = colonyRepository.findAllAliveColonies();
        var price = priceListRepository.findAll().get(0).getWeapon() * calculatePriceCoefficient(entity);
        final var newSurplus = new AtomicInteger(Math.min(surplus, tradePoints.get()));

        var richColoniesStream = colonies.stream().filter(colony1 -> colony1.getMoney() > 0);
        richColoniesStream
                .map(colony1 -> new HashMap.SimpleEntry<>(colony1, (int) Math.min(
                        colony1.getAliveEntities().size() * (colony1.getType() == MILITARY ? 3 : 1),
                        colony1.getMoney() / price)))
                .sorted(Comparator.comparingInt(pair -> -pair.getValue()))
                .takeWhile(pair -> {
                    var buyer = pair.getKey();

                    var sold = pair.getValue() < newSurplus.get() ? pair.getValue() : newSurplus.get();

                    buyer.setWeapon(buyer.getWeapon() + sold);
                    colony.setWeapon(colony.getWeapon() - sold);

                    buyer.setMoney((int) (buyer.getMoney() - sold * price));
                    colony.setMoney((int) (colony.getMoney() + sold * price));

                    return tradePoints.addAndGet(-sold) > 0 && newSurplus.get() > 0;
                });
    }

    static void sellTools(AtomicInteger tradePoints, Colony colony, int surplus, Entity entity) {
        var colonies = colonyRepository.findAllAliveColonies();
        var price = priceListRepository.findAll().get(0).getTools() * calculatePriceCoefficient(entity);
        final var newSurplus = new AtomicInteger(Math.min(surplus, tradePoints.get()));

        var richColoniesStream = colonies.stream().filter(colony1 -> colony1.getMoney() > 0);
        richColoniesStream
                .map(colony1 -> new HashMap.SimpleEntry<>(colony1, (int) Math.min(
                        colony1.getAliveEntities().size(),
                        colony1.getMoney() / price)))
                .sorted(Comparator.comparingInt(pair -> -pair.getValue()))
                .takeWhile(pair -> {
                    var buyer = pair.getKey();

                    var sold = pair.getValue() < newSurplus.get() ? pair.getValue() : newSurplus.get();

                    buyer.setTools(buyer.getTools() + sold);
                    colony.setTools(colony.getTools() - sold);

                    buyer.setMoney((int) (buyer.getMoney() - sold * price));
                    colony.setMoney((int) (colony.getMoney() + sold * price));

                    return tradePoints.addAndGet(-sold) > 0 && newSurplus.get() > 0;
                });
    }

    static void sellFood(AtomicInteger tradePoints, Colony colony, int surplus, Entity entity) {
        var colonies = colonyRepository.findAllAliveColonies();
        var price = priceListRepository.findAll().get(0).getFood() * calculatePriceCoefficient(entity);
        final var newSurplus = new AtomicInteger(Math.min(surplus, tradePoints.get()));

        colonies.stream().filter(colony1 -> colony1.getMoney() > 0)
                .map(colony1 -> new HashMap.SimpleEntry<>(colony1, (int) Math.min(
                        colony1.getAliveEntities().size(), //TODO
                        colony1.getMoney() / price)))
                .sorted(Comparator.comparingInt(pair -> -pair.getValue()))
                .takeWhile(pair -> {
                    var buyer = pair.getKey();

                    var sold = pair.getValue() < newSurplus.get() ? pair.getValue() : newSurplus.get();

                    buyer.setFood(buyer.getFood() + sold);
                    colony.setFood(colony.getFood() - sold);

                    buyer.setMoney((int) (buyer.getMoney() - sold * price));
                    colony.setMoney((int) (colony.getMoney() + sold * price));

                    return tradePoints.addAndGet(-sold) > 0 && newSurplus.get() > 0;
                });
    }
}
