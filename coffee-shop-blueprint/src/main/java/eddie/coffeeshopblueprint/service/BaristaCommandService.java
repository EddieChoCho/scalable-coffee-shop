package eddie.coffeeshopblueprint.service;

import eddie.coffeeshopblueprint.events.CoffeeBrewFinished;
import eddie.coffeeshopblueprint.events.CoffeeBrewStarted;
import eddie.coffeeshopblueprint.events.CoffeeDelivered;
import eddie.coffeeshopblueprint.events.CoffeeEvent;
import eddie.coffeeshopblueprint.listener.EventSerializer;
import eddie.coffeeshopblueprint.model.OrderInfo;
import eddie.coffeeshopblueprint.store.CoffeeBrews;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Random;
import java.util.UUID;

@Service
public class BaristaCommandService {
    private static final Logger log = LoggerFactory.getLogger(BaristaCommandService.class);

    public final String BARISTA_TOPIC = "barista";
    private CoffeeBrews coffeeBrews;
    private EventSerializer eventSerializer;
    private KafkaTemplate<Integer, String> template;

    @Autowired
    public BaristaCommandService(final CoffeeBrews coffeeBrews, final EventSerializer eventSerializer, final KafkaTemplate<Integer, String> template){
        this.coffeeBrews = coffeeBrews;
        this.eventSerializer = eventSerializer;
        this.template = template;
    }


    public void makeCoffee(final OrderInfo orderInfo) {
        CoffeeEvent event = new CoffeeBrewStarted(orderInfo);
        template.send(BARISTA_TOPIC,eventSerializer.serialize(event));
    }

    public void checkCoffee() {
        final Collection<UUID> unfinishedBrews = coffeeBrews.getUnfinishedBrews();
        log.info("checking " + unfinishedBrews.size() + " unfinished brews");
        unfinishedBrews.forEach(i -> {
            if (new Random().nextBoolean()){
                CoffeeEvent event = new CoffeeBrewFinished(i);
                template.send(BARISTA_TOPIC,eventSerializer.serialize(event));
            }
        });
    }

    public void checkCustomerDelivery() {
        final Collection<UUID> undeliveredOrder = coffeeBrews.getUndeliveredOrders();
        log.info("checking " + undeliveredOrder.size() + " un-served orders");
        undeliveredOrder.forEach(i -> {
            if (new Random().nextBoolean()){
                CoffeeEvent event = new CoffeeDelivered(i);
                template.send(BARISTA_TOPIC,eventSerializer.serialize(event));
            }
        });
    }

}
