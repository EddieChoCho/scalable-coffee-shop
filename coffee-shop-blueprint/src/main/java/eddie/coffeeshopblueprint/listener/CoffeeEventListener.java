package eddie.coffeeshopblueprint.listener;

import com.google.gson.Gson;
import eddie.coffeeshopblueprint.events.CoffeeEvent;
import eddie.coffeeshopblueprint.handler.BaristaEventHandler;
import eddie.coffeeshopblueprint.handler.BeanEventHandler;
import eddie.coffeeshopblueprint.handler.OrderEventHandler;
import eddie.coffeeshopblueprint.store.BeanStorage;
import eddie.coffeeshopblueprint.store.CoffeeBrews;
import eddie.coffeeshopblueprint.store.CoffeeOrders;
import eddie.coffeeshopblueprint.store.EventStore;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class CoffeeEventListener {

    private Logger logger = LoggerFactory.getLogger(CoffeeEventListener.class);

    private EventDeserializer eventDeserializer;
    private Gson gson = new Gson();
    private EventStore eventStore;
    private OrderEventHandler orderEventHandler;
    private BeanEventHandler beanEventHandler;
    private BaristaEventHandler baristaEventHandler;
    private CoffeeOrders coffeeOrders;
    private CoffeeBrews coffeeBrews;
    private BeanStorage beanStorage;

    public CoffeeEventListener(final EventDeserializer eventDeserializer, final OrderEventHandler orderEventHandler, final BeanEventHandler beanEventHandler, final BaristaEventHandler baristaEventHandler,
                               final CoffeeOrders coffeeOrders, final CoffeeBrews coffeeBrews, final BeanStorage beanStorage,
                               final EventStore eventStore){
        this.eventDeserializer = eventDeserializer;
        this.orderEventHandler = orderEventHandler;
        this.beanEventHandler = beanEventHandler;
        this.baristaEventHandler = baristaEventHandler;
        this.coffeeOrders = coffeeOrders;
        this.coffeeBrews = coffeeBrews;
        this.beanStorage = beanStorage;
        this.eventStore = eventStore;
    }

    @KafkaListener(topics = "order")
    public void listenOrderEvent(ConsumerRecord<Integer,String> record) throws ClassNotFoundException {
        CoffeeEvent event = eventDeserializer.deserialize(record.value());
        eventStore.add(event);
        this.handleAndApply(event);
    }

    @KafkaListener(topics = "beans")
    public void listenBeansEvent(ConsumerRecord<Integer,String> record) throws ClassNotFoundException {
        CoffeeEvent event = eventDeserializer.deserialize(record.value());
        eventStore.add(event);
        this.handleAndApply(event);
    }

    @KafkaListener(topics = "barista")
    public void listenBaristaEvent(ConsumerRecord<Integer,String> record) throws ClassNotFoundException {
        CoffeeEvent event = eventDeserializer.deserialize(record.value());
        eventStore.add(event);
        this.handleAndApply(event);
    }

    private void handleAndApply(CoffeeEvent event){
        logger.info(event.getClass().getName() + ": " + event.toString());
        orderEventHandler.handle(event);
        beanEventHandler.handle(event);
        baristaEventHandler.handle(event);
        coffeeOrders.apply(event);
        coffeeBrews.apply(event);
        beanStorage.apply(event);
    }


}
