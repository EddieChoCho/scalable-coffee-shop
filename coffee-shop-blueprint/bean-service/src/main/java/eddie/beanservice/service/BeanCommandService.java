package eddie.beanservice.service;

import eddie.beanservice.store.BeanStorage;
import eddie.coffeeshopcomponent.model.events.*;
import eddie.coffeeshopcomponent.model.serializer.EventSerializer;
import eddie.coffeeshopcomponent.service.CommandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class BeanCommandService extends CommandService {

    public final String BEANS_TOPIC = "beans";

    @Autowired
    public BeanCommandService(final BeanStorage beanStorage, final EventSerializer eventSerializer, final KafkaTemplate<Integer, String> template){
        setCoffeeApplier(beanStorage);
        setEventSerializer(eventSerializer);
        setTopic(BEANS_TOPIC);
        setTemplate(template);
    }

    public void storeBeans(final String beanOrigin, final int amount) {
        CoffeeEvent event = new BeansStored(beanOrigin, amount);
        this.publishEvent(event);
    }

    public void reserveBeans(final String beanOrigin, final UUID orderId) {
        BeanStorage beanStorage = ((BeanStorage)getCoffeeApplier());
        if (beanStorage.getRemainingAmount(beanOrigin) > 0) {
            CoffeeEvent orderBeansReserved = new OrderBeansReserved(orderId);
            CoffeeEvent beansFetched = new BeansFetched(beanOrigin);
            this.publishEvent(orderBeansReserved);
            this.publishEvent(beansFetched);
        } else{
            CoffeeEvent orderFailedBeansNotAvailable = new OrderFailedBeansNotAvailable(orderId);
            this.publishEvent(orderFailedBeansNotAvailable);
        }
    }
}
