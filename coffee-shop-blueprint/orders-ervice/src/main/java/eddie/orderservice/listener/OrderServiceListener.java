package eddie.orderservice.listener;

import eddie.coffeeshopcomponent.listener.CoffeeEventListener;
import eddie.coffeeshopcomponent.model.serializer.EventDeserializer;
import eddie.coffeeshopcomponent.store.EventStore;
import eddie.orderservice.handler.OrderEventHandler;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class OrderServiceListener extends CoffeeEventListener {

    public OrderServiceListener(OrderEventHandler orderEventHandler, EventDeserializer eventDeserializer, EventStore eventStore) {
        super(orderEventHandler, eventDeserializer, eventStore);
    }

    @KafkaListener(topics = {"beans","barista"})
    public void listenEvent(ConsumerRecord<Integer,String> record) throws ClassNotFoundException {
        super.listenEvent(record);
    }
}
