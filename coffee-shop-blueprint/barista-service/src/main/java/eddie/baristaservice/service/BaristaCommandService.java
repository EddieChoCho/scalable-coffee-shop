package eddie.baristaservice.service;

import eddie.baristaservice.store.CoffeeBrews;
import eddie.coffeeshopcomponent.model.OrderInfo;
import eddie.coffeeshopcomponent.model.events.CoffeeBrewFinished;
import eddie.coffeeshopcomponent.model.events.CoffeeBrewStarted;
import eddie.coffeeshopcomponent.model.events.CoffeeDelivered;
import eddie.coffeeshopcomponent.model.events.CoffeeEvent;
import eddie.coffeeshopcomponent.model.serializer.EventSerializer;
import eddie.coffeeshopcomponent.service.CommandService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Random;
import java.util.UUID;

@Service
public class BaristaCommandService extends CommandService {
    private static final Logger log = LoggerFactory.getLogger(BaristaCommandService.class);

    public final String BARISTA_TOPIC = "barista";

    @Autowired
    public BaristaCommandService(final CoffeeBrews coffeeBrews, final EventSerializer eventSerializer, final KafkaTemplate<Integer, String> template){
        this.setCoffeeApplier(coffeeBrews);
        this.setEventSerializer(eventSerializer);
        this.setTemplate(template);
        this.setTopic(BARISTA_TOPIC);
    }

    public void makeCoffee(final OrderInfo orderInfo) {
        CoffeeEvent event = new CoffeeBrewStarted(orderInfo);
        this.publishEvent(event);
    }

    public void checkCoffee() {
        final CoffeeBrews coffeeBrews = (CoffeeBrews)getCoffeeApplier();
        final Collection<UUID> unfinishedBrews = coffeeBrews.getUnfinishedBrews();
        log.info("checking " + unfinishedBrews.size() + " unfinished brews");
        unfinishedBrews.forEach(i -> {
            if (new Random().nextBoolean()){
                CoffeeEvent event = new CoffeeBrewFinished(i);
                this.publishEvent(event);
            }
        });
    }

    public void checkCustomerDelivery() {
        final CoffeeBrews coffeeBrews = (CoffeeBrews)getCoffeeApplier();
        final Collection<UUID> undeliveredOrder = coffeeBrews.getUndeliveredOrders();
        log.info("checking " + undeliveredOrder.size() + " un-served orders");
        undeliveredOrder.forEach(i -> {
            if (new Random().nextBoolean()){
                CoffeeEvent event = new CoffeeDelivered(i);
                this.publishEvent(event);
            }
        });
    }
}
