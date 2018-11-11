package eddie.orderservice.service;

import eddie.coffeeshopcomponent.model.OrderInfo;
import eddie.coffeeshopcomponent.model.events.*;
import eddie.coffeeshopcomponent.model.serializer.EventSerializer;
import eddie.coffeeshopcomponent.service.CommandService;
import eddie.orderservice.store.CoffeeOrders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class OrderCommandService extends CommandService {

    public final String ORDER_TOPIC = "order";

    @Autowired
    public OrderCommandService(final CoffeeOrders coffeeOrders, final EventSerializer eventSerializer, final KafkaTemplate<Integer, String> template){
        this.setCoffeeApplier(coffeeOrders);
        this.setEventSerializer(eventSerializer);
        this.setTemplate(template);
        this.setTopic(ORDER_TOPIC);
    }

    public void placeOrder(final OrderInfo orderInfo) {
        CoffeeEvent event = new OrderPlaced(orderInfo);
        this.publishEvent(event);
    }

    public void acceptOrder(final UUID orderId) {
        final CoffeeOrders coffeeOrders = (CoffeeOrders)getCoffeeApplier();
        final OrderInfo orderInfo = coffeeOrders.get(orderId).getOrderInfo();
        CoffeeEvent event = new OrderAccepted(orderInfo);
        this.publishEvent(event);
    }

    public void cancelOrder(final UUID orderId, final String reason) {
        CoffeeEvent event = new OrderCancelled(orderId, reason);
        this.publishEvent(event);
    }

    public void startOrder(final UUID orderId) {
        CoffeeEvent event = new OrderStarted(orderId);
        this.publishEvent(event);
    }

    public void finishOrder(final UUID orderId) {
        CoffeeEvent event = new OrderFinished(orderId);
        this.publishEvent(event);
    }

    public void deliverOrder(final UUID orderId) {
        CoffeeEvent event = new OrderDelivered(orderId);
        this.publishEvent(event);
    }

}
