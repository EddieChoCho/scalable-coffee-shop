package eddie.coffeeshopblueprint.service;

import eddie.coffeeshopblueprint.events.CoffeeBrewFinished;
import eddie.coffeeshopblueprint.events.CoffeeBrewStarted;
import eddie.coffeeshopblueprint.events.CoffeeDelivered;
import eddie.coffeeshopblueprint.model.OrderInfo;
import eddie.coffeeshopblueprint.store.CoffeeBrews;
import eddie.coffeeshopblueprint.store.EventStore;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collection;
import java.util.Random;
import java.util.UUID;

public class BaristaCommandService {
    private static final Logger log = LoggerFactory.getLogger(BaristaCommandService.class);
    private EventStore eventStore;
    private CoffeeBrews coffeeBrews;

    public BaristaCommandService(final EventStore eventStore, final CoffeeBrews coffeeBrews){
        this.eventStore = eventStore;
        this.coffeeBrews = coffeeBrews;
    }


    public void makeCoffee(final OrderInfo orderInfo) {
        eventStore.publish(new CoffeeBrewStarted(orderInfo));
    }

    public void checkCoffee() {
        final Collection<UUID> unfinishedBrews = coffeeBrews.getUnfinishedBrews();
        log.info("checking " + unfinishedBrews.size() + " unfinished brews");
        unfinishedBrews.forEach(i -> {
            if (new Random().nextBoolean())
                eventStore.publish(new CoffeeBrewFinished(i));
        });
    }

    public void checkCustomerDelivery() {
        final Collection<UUID> undeliveredOrder = coffeeBrews.getUndeliveredOrders();
        log.info("checking " + undeliveredOrder.size() + " un-served orders");
        undeliveredOrder.forEach(i -> {
            if (new Random().nextBoolean())
                eventStore.publish(new CoffeeDelivered(i));
        });
    }

}
