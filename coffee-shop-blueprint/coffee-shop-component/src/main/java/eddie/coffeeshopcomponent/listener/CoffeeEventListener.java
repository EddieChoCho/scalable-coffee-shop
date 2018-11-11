package eddie.coffeeshopcomponent.listener;

import eddie.coffeeshopcomponent.handler.CoffeeEventHandler;
import eddie.coffeeshopcomponent.model.events.CoffeeEvent;
import eddie.coffeeshopcomponent.model.serializer.EventDeserializer;
import eddie.coffeeshopcomponent.store.EventStore;
import lombok.Data;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Data
public class CoffeeEventListener {

    private Logger logger = LoggerFactory.getLogger(CoffeeEventListener.class);

    private CoffeeEventHandler coffeeEventHandler;
    private EventDeserializer eventDeserializer;
    private EventStore eventStore;

    public CoffeeEventListener(final CoffeeEventHandler coffeeEventHandler, final EventDeserializer eventDeserializer, final EventStore eventStore){
        this.coffeeEventHandler = coffeeEventHandler;
        this.eventDeserializer = eventDeserializer;
        this.eventStore = eventStore;
    }

    public void listenEvent(ConsumerRecord<Integer,String> record) throws ClassNotFoundException {
        CoffeeEvent event = eventDeserializer.deserialize(record.value());
        logger.info(event.getClass().getName() + ": " + event.toString());
        eventStore.add(event);
        coffeeEventHandler.handle(event);
    }
}
