package eddie.coffeeshopblueprint.store;

import eddie.coffeeshopblueprint.events.CoffeeBrewFinished;
import eddie.coffeeshopblueprint.events.CoffeeBrewStarted;
import eddie.coffeeshopblueprint.events.CoffeeDelivered;

import java.util.Collection;
import java.util.Iterator;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.ConcurrentSkipListSet;

import static java.util.Collections.unmodifiableCollection;

public class CoffeeBrews {

    private final Set<UUID> unfinishedBrews = new ConcurrentSkipListSet<>();
    private final Set<UUID> undeliveredOrders = new ConcurrentSkipListSet<>();

    public Collection<UUID> getUnfinishedBrews() {
        return unmodifiableCollection(unfinishedBrews);
    }

    public Collection<UUID> getUndeliveredOrders() {
        return unmodifiableCollection(undeliveredOrders);
    }

    public void apply(CoffeeBrewStarted event) {
        unfinishedBrews.add(event.getOrderInfo().getOrderId());
    }

    public void apply(CoffeeBrewFinished event) {
        final Iterator<UUID> iterator = unfinishedBrews.iterator();
        while (iterator.hasNext()) {
            final UUID orderId = iterator.next();
            if (orderId.equals(event.getOrderId())) {
                iterator.remove();
                undeliveredOrders.add(orderId);
            }
        }
    }

    public void apply(CoffeeDelivered event) {
        undeliveredOrders.removeIf(i -> i.equals(event.getOrderId()));
    }

}
