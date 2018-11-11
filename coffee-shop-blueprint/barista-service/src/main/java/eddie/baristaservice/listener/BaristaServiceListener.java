package eddie.baristaservice.listener;

import eddie.baristaservice.handler.BaristaEventHandler;
import eddie.coffeeshopcomponent.listener.CoffeeEventListener;
import eddie.coffeeshopcomponent.model.serializer.EventDeserializer;
import eddie.coffeeshopcomponent.store.EventStore;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class BaristaServiceListener extends CoffeeEventListener {

    public BaristaServiceListener(BaristaEventHandler baristaEventHandler, EventDeserializer eventDeserializer, EventStore eventStore) {
        super(baristaEventHandler, eventDeserializer, eventStore);
    }

    @KafkaListener(topics = "order")
    public void listenEvent(ConsumerRecord<Integer,String> record) throws ClassNotFoundException {
        super.listenEvent(record);
    }
}
