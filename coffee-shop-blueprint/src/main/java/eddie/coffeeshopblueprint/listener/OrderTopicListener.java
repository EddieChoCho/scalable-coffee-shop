package eddie.coffeeshopblueprint.listener;

import eddie.coffeeshopblueprint.events.CoffeeEvent;
import eddie.coffeeshopblueprint.handler.BaristaEventHandler;
import eddie.coffeeshopblueprint.handler.BeanEventHandler;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class OrderTopicListener {

    private BeanEventHandler beanEventHandler;
    private BaristaEventHandler baristaEventHandler;
    private CoffeeEventListener coffeeEventListener;

    public OrderTopicListener(final BeanEventHandler beanEventHandler, final BaristaEventHandler baristaEventHandler, final CoffeeEventListener coffeeEventListener){
        this.beanEventHandler = beanEventHandler;
        this.baristaEventHandler = baristaEventHandler;
        this.coffeeEventListener = coffeeEventListener;
    }

    @KafkaListener(topics = "order")
    public void listenOrderEvent(ConsumerRecord<Integer,String> record) throws ClassNotFoundException {
        CoffeeEvent event = coffeeEventListener.listenEvent(record);
        beanEventHandler.handle(event);
        baristaEventHandler.handle(event);
    }
}
