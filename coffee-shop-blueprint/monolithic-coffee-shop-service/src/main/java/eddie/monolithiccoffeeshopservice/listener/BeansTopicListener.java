package eddie.monolithiccoffeeshopservice.listener;

import eddie.monolithiccoffeeshopservice.events.CoffeeEvent;
import eddie.monolithiccoffeeshopservice.handler.OrderEventHandler;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class BeansTopicListener {

    private CoffeeEventListener coffeeEventListener;
    private OrderEventHandler orderEventHandler;

    public BeansTopicListener(final CoffeeEventListener coffeeEventListener, OrderEventHandler orderEventHandler){
        this.coffeeEventListener = coffeeEventListener;
        this.orderEventHandler = orderEventHandler;
    }

    @KafkaListener(topics = "beans")
    public void listenBeansEvent(ConsumerRecord<Integer,String> record) throws ClassNotFoundException {
        CoffeeEvent event = coffeeEventListener.listenEvent(record);
        orderEventHandler.handle(event);
    }
}
