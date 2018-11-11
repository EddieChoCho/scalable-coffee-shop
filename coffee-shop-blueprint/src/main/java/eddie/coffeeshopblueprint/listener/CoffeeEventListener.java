package eddie.coffeeshopblueprint.listener;

import eddie.coffeeshopblueprint.events.CoffeeEvent;
import eddie.coffeeshopblueprint.serializer.EventDeserializer;
import eddie.coffeeshopblueprint.store.EventStore;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class CoffeeEventListener {

    private Logger logger = LoggerFactory.getLogger(CoffeeEventListener.class);

    private EventDeserializer eventDeserializer;
    private EventStore eventStore;

    public CoffeeEventListener(final EventDeserializer eventDeserializer, final EventStore eventStore){
        this.eventDeserializer = eventDeserializer;
        this.eventStore = eventStore;
    }

    public CoffeeEvent listenEvent(ConsumerRecord<Integer,String> record) throws ClassNotFoundException {
        CoffeeEvent event = eventDeserializer.deserialize(record.value());
        logger.info(event.getClass().getName() + ": " + event.toString());
        eventStore.add(event);
        return event;
    }
}
