package eddie.monolithiccoffeeshopservice.service;

import eddie.monolithiccoffeeshopservice.events.CoffeeEvent;
import eddie.monolithiccoffeeshopservice.serializer.EventSerializer;
import eddie.monolithiccoffeeshopservice.store.CoffeeApplier;
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
