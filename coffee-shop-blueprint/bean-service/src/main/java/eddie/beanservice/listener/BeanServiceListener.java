package eddie.beanservice.listener;

import eddie.beanservice.handler.BeanEventHandler;
import eddie.coffeeshopcomponent.listener.CoffeeEventListener;
import eddie.coffeeshopcomponent.model.serializer.EventDeserializer;
import eddie.coffeeshopcomponent.store.EventStore;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class BeanServiceListener extends CoffeeEventListener {

    public BeanServiceListener(BeanEventHandler beanEventHandler, EventDeserializer eventDeserializer, EventStore eventStore) {
        super(beanEventHandler, eventDeserializer, eventStore);
    }

    @KafkaListener(topics = {"order","barista"})
    public void listenEvent(ConsumerRecord<Integer,String> record) throws ClassNotFoundException {
        super.listenEvent(record);
    }
}
