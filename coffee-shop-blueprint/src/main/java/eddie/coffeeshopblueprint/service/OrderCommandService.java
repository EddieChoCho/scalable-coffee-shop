package eddie.coffeeshopblueprint.service;

import eddie.coffeeshopblueprint.events.*;
import eddie.coffeeshopblueprint.model.OrderInfo;
import eddie.coffeeshopblueprint.store.CoffeeOrders;
import eddie.coffeeshopblueprint.store.EventStore;

import java.util.UUID;

public class OrderCommandService {

    private EventStore eventStore;
    private CoffeeOrders coffeeOrders;

    public OrderCommandService(final EventStore eventStore, final CoffeeOrders coffeeOrders){
        this.eventStore = eventStore;
        this.coffeeOrders = coffeeOrders;
    }

    public void placeOrder(final OrderInfo orderInfo) {
        eventStore.publish(new OrderPlaced(orderInfo));
    }

    public void acceptOrder(final UUID orderId) {
        final OrderInfo orderInfo = coffeeOrders.get(orderId).getOrderInfo();
        eventStore.publish(new OrderAccepted(orderInfo));
    }

    public void cancelOrder(final UUID orderId, final String reason) {
        eventStore.publish(new OrderCancelled(orderId, reason));
    }

    public void startOrder(final UUID orderId) {
        eventStore.publish(new OrderStarted(orderId));
    }

    public void finishOrder(final UUID orderId) {
        eventStore.publish(new OrderFinished(orderId));
    }

    public void deliverOrder(final UUID orderId) {
        eventStore.publish(new OrderDelivered(orderId));
    }

}
