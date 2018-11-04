package eddie.coffeeshopblueprint.service;

import eddie.coffeeshopblueprint.events.*;
import eddie.coffeeshopblueprint.listener.EventSerializer;
import eddie.coffeeshopblueprint.store.BeanStorage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class BeanCommandService {

    public final String BEANS_TOPIC = "beans";
    private BeanStorage beanStorage;
    private EventSerializer eventSerializer;
    private KafkaTemplate<Integer, String> template;

    @Autowired
    public BeanCommandService(final BeanStorage beanStorage, final EventSerializer eventSerializer, final KafkaTemplate<Integer, String> template){
        this.beanStorage = beanStorage;
        this.eventSerializer = eventSerializer;
        this.template = template;
    }

    public void storeBeans(final String beanOrigin, final int amount) {
        CoffeeEvent event = new BeansStored(beanOrigin, amount);
        template.send(BEANS_TOPIC,eventSerializer.serialize(event));
    }

    public void reserveBeans(final String beanOrigin, final UUID orderId) {
        if (beanStorage.getRemainingAmount(beanOrigin) > 0) {
            CoffeeEvent orderBeansReserved = new OrderBeansReserved(orderId);
            CoffeeEvent beansFetched = new BeansFetched(beanOrigin);
            template.send(BEANS_TOPIC,eventSerializer.serialize(orderBeansReserved));
            template.send(BEANS_TOPIC,eventSerializer.serialize(beansFetched));
        } else{
            CoffeeEvent orderFailedBeansNotAvailable = new OrderFailedBeansNotAvailable(orderId);
            template.send(BEANS_TOPIC,eventSerializer.serialize(orderFailedBeansNotAvailable));
        }
    }

}
