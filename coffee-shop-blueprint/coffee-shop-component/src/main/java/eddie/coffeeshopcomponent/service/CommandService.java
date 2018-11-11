package eddie.coffeeshopcomponent.service;

import eddie.coffeeshopcomponent.model.events.CoffeeEvent;
import eddie.coffeeshopcomponent.model.serializer.EventSerializer;
import eddie.coffeeshopcomponent.store.CoffeeApplier;
import lombok.Data;
import org.springframework.kafka.core.KafkaTemplate;

@Data
public abstract class CommandService {

    private CoffeeApplier coffeeApplier;
    private EventSerializer eventSerializer;
    private KafkaTemplate<Integer, String> template;
    private String topic;

    public void publishEvent(final CoffeeEvent event){
        coffeeApplier.apply(event);
        template.send(topic,eventSerializer.serialize(event));
    }
}
