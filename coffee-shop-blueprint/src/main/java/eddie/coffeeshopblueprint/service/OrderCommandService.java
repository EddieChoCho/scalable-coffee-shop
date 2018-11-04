package eddie.coffeeshopblueprint.service;

import eddie.coffeeshopblueprint.events.*;
import eddie.coffeeshopblueprint.listener.EventSerializer;
import eddie.coffeeshopblueprint.model.OrderInfo;
import eddie.coffeeshopblueprint.store.CoffeeOrders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class OrderCommandService {

    public final String ORDER_TOPIC = "order";
    private CoffeeOrders coffeeOrders;
    private EventSerializer eventSerializer;
    private KafkaTemplate<Integer, String> template;

    @Autowired
    public OrderCommandService(final CoffeeOrders coffeeOrders, final EventSerializer eventSerializer, final KafkaTemplate<Integer, String> template){
        this.coffeeOrders = coffeeOrders;
        this.eventSerializer = eventSerializer;
        this.template = template;
    }

    public void placeOrder(final OrderInfo orderInfo) {
        CoffeeEvent event = new OrderPlaced(orderInfo);
        template.send(ORDER_TOPIC,eventSerializer.serialize(event));
    }

    public void acceptOrder(final UUID orderId) {
        final OrderInfo orderInfo = coffeeOrders.get(orderId).getOrderInfo();
        CoffeeEvent event = new OrderAccepted(orderInfo);
        template.send(ORDER_TOPIC,eventSerializer.serialize(event));
    }

    public void cancelOrder(final UUID orderId, final String reason) {
        CoffeeEvent event = new OrderCancelled(orderId, reason);
        template.send(ORDER_TOPIC,eventSerializer.serialize(event));
    }

    public void startOrder(final UUID orderId) {
        CoffeeEvent event = new OrderStarted(orderId);
        template.send(ORDER_TOPIC,eventSerializer.serialize(event));
    }

    public void finishOrder(final UUID orderId) {
        CoffeeEvent event = new OrderFinished(orderId);
        template.send(ORDER_TOPIC,eventSerializer.serialize(event));
    }

    public void deliverOrder(final UUID orderId) {
        CoffeeEvent event = new OrderDelivered(orderId);
        template.send(ORDER_TOPIC,eventSerializer.serialize(event));
    }

}
