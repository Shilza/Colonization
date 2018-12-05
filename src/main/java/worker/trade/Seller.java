package worker.trade;

import model.Colony;
import model.Entity;

import java.util.Comparator;
import java.util.HashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import static Constants.Type.MILITARY;

class Seller extends Trader{

    static void sellWeapon(AtomicInteger tradePoints, Colony colony, int surplus, Entity entity) {
        var colonies = colonyRepository.findAllAliveColonies();
        var price = priceListRepository.findAll().get(0).getWeapon() * calculatePriceCoefficient(entity);
        final var newSurplus = new AtomicInteger(Math.min(surplus, tradePoints.get()));

        var richColoniesStream = colonies.stream().filter(colony1 -> colony1.getMoney() > 0);
        richColoniesStream
                .map(colony1 -> new HashMap.SimpleEntry<>(colony1, Double.min(
                        colony1.getAliveEntities().size() * (colony1.getType() == MILITARY ? 3 : 1) - colony1.getWeapon(),
                        colony1.getMoney() / price)))
                .filter(pair -> pair.getValue() > 0)
                .sorted(Comparator.comparingDouble(pair -> -pair.getValue()))
                .takeWhile(pair -> {
                    var buyer = pair.getKey();

                    var sold = pair.getValue() < newSurplus.get() ? pair.getValue() : newSurplus.get();

                    buyer.setWeapon((int) Math.round(buyer.getWeapon() + sold));
                    colony.setWeapon((int) Math.round(colony.getWeapon() - sold));

                    buyer.setMoney(buyer.getMoney() - sold * price);
                    colony.setMoney(colony.getMoney() + sold * price);

                    return tradePoints.addAndGet((int)Math.round(-sold)) > 0 && newSurplus.addAndGet((int)Math.round(-sold)) > 0;
                }).collect(Collectors.toList());
    }

    static void sellTools(AtomicInteger tradePoints, Colony colony, int surplus, Entity entity) {
        var colonies = colonyRepository.findAllAliveColonies();
        var price = priceListRepository.findAll().get(0).getTools() * calculatePriceCoefficient(entity);
        final var newSurplus = new AtomicInteger(Math.min(surplus, tradePoints.get()));

        var richColoniesStream = colonies.stream().filter(colony1 -> colony1.getMoney() > 0);
        richColoniesStream
                .map(colony1 -> new HashMap.SimpleEntry<>(colony1, Double.min(
                        colony1.getAliveEntities().size() - colony1.getTools(),
                        colony1.getMoney() / price)))
                .filter(pair -> pair.getValue() > 0)
                .sorted(Comparator.comparingDouble(pair -> -pair.getValue()))
                .takeWhile(pair -> {
                    var buyer = pair.getKey();

                    var sold = pair.getValue() < newSurplus.get() ? pair.getValue() : newSurplus.get();

                    buyer.setTools((int) Math.round(buyer.getTools() + sold));
                    colony.setTools((int) Math.round(colony.getTools() - sold));

                    buyer.setMoney(buyer.getMoney() - sold * price);
                    colony.setMoney(colony.getMoney() + sold * price);

                    return tradePoints.addAndGet((int)Math.round(-sold)) > 0 && newSurplus.addAndGet((int)Math.round(-sold)) > 0;
                }).collect(Collectors.toList());
    }

    static void sellFood(AtomicInteger tradePoints, Colony colony, int surplus, Entity entity) {
        var colonies = colonyRepository.findAllAliveColonies();
        var price = priceListRepository.findAll().get(0).getFood() * calculatePriceCoefficient(entity);
        final var newSurplus = new AtomicInteger(Math.min(surplus, tradePoints.get()));

        colonies.stream().filter(colony1 -> colony1.getMoney() > 0)
                .map(colony1 -> new HashMap.SimpleEntry<>(colony1, Double.min(
                        colony1.getNeededFoodPerDay() - colony1.getFood(),
                        colony1.getMoney() / price)))
                .filter(pair -> pair.getValue() > 0)
                .sorted(Comparator.comparingDouble(pair -> -pair.getValue()))
                .takeWhile(pair -> {
                    var buyer = pair.getKey();

                    var sold = pair.getValue() < newSurplus.get() ? pair.getValue() : newSurplus.get();

                    buyer.setFood((int)Math.round(buyer.getFood() + sold));
                    colony.setFood((int)Math.round(colony.getFood() - sold));

                    buyer.setMoney(buyer.getMoney() - sold * price);
                    colony.setMoney(colony.getMoney() + sold * price);

                    return tradePoints.addAndGet((int)Math.round(-sold)) > 0 && newSurplus.addAndGet((int)Math.round(-sold)) > 0;
                }).collect(Collectors.toList());
    }
}
