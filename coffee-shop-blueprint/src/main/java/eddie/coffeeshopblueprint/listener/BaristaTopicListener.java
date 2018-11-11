package eddie.coffeeshopblueprint.listener;

import eddie.coffeeshopblueprint.events.CoffeeEvent;
import eddie.coffeeshopblueprint.handler.BeanEventHandler;
import eddie.coffeeshopblueprint.handler.OrderEventHandler;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class BaristaTopicListener {

    private CoffeeEventListener coffeeEventListener;
    private BeanEventHandler beanEventHandler;
    private OrderEventHandler orderEventHandler;

    public BaristaTopicListener(final CoffeeEventListener coffeeEventListener, final BeanEventHandler beanEventHandler, final OrderEventHandler orderEventHandler){
        this.coffeeEventListener = coffeeEventListener;
        this.beanEventHandler = beanEventHandler;
        this.orderEventHandler = orderEventHandler;
    }


    @KafkaListener(topics = "barista")
    public void listenBaristaEvent(ConsumerRecord<Integer,String> record) throws ClassNotFoundException {
        CoffeeEvent event = coffeeEventListener.listenEvent(record);
        beanEventHandler.handle(event);
        orderEventHandler.handle(event);
    }
}
