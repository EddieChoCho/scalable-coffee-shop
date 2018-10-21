package eddie.coffeeshopblueprint.config;

import eddie.coffeeshopblueprint.handler.BaristaEventHandler;
import eddie.coffeeshopblueprint.handler.BaristaTimer;
import eddie.coffeeshopblueprint.handler.BeanEventHandler;
import eddie.coffeeshopblueprint.handler.OrderEventHandler;
import eddie.coffeeshopblueprint.service.BaristaCommandService;
import eddie.coffeeshopblueprint.service.BeanCommandService;
import eddie.coffeeshopblueprint.service.OrderCommandService;
import eddie.coffeeshopblueprint.store.BeanStorage;
import eddie.coffeeshopblueprint.store.CoffeeBrews;
import eddie.coffeeshopblueprint.store.CoffeeOrders;
import eddie.coffeeshopblueprint.store.EventStore;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeansConfig {

    @Bean
    public CoffeeOrders coffeeOrders(){return new CoffeeOrders();}

    @Bean
    public BeanStorage beanStorage(){return new BeanStorage();}

    @Bean
    public CoffeeBrews coffeeBrews(){return new CoffeeBrews();}

    @Bean
    public EventStore eventStore(final CoffeeOrders coffeeOrders, final BeanStorage beanStorage, final CoffeeBrews coffeeBrews){
        EventStore eventStore = new EventStore();
        eventStore.setCoffeeOrders(coffeeOrders);
        eventStore.setBeanStorage(beanStorage);
        eventStore.setCoffeeBrews(coffeeBrews);
        return eventStore;
    }

    @Bean
    public OrderCommandService orderCommandService(final EventStore eventStore, final CoffeeOrders coffeeOrders){
        return new OrderCommandService(eventStore, coffeeOrders);
    }

    @Bean
    public BeanCommandService beanCommandService(final EventStore eventStore, final BeanStorage beanStorage){
        return new BeanCommandService(eventStore, beanStorage);
    }

    @Bean
    public BaristaCommandService baristaCommandService(final EventStore eventStore, final CoffeeBrews coffeeBrews){
        return new BaristaCommandService(eventStore, coffeeBrews);
    }

    @Bean
    public OrderEventHandler orderEventHandler(final OrderCommandService orderCommandService, final EventStore eventStore){
        OrderEventHandler orderEventHandler = new OrderEventHandler(orderCommandService);
        eventStore.setOrderEventHandler(orderEventHandler);
        return orderEventHandler;
    }

    @Bean
    public BeanEventHandler beanEventHandler(final BeanCommandService beanCommandService, final EventStore eventStore){
        BeanEventHandler beanEventHandler = new BeanEventHandler(beanCommandService);
        eventStore.setBeanEventHandler(beanEventHandler);
        return beanEventHandler;
    }

    @Bean
    public BaristaEventHandler baristaEventHandler(final BaristaCommandService baristaCommandService, final EventStore eventStore){
        BaristaEventHandler baristaEventHandler = new BaristaEventHandler(baristaCommandService);
        eventStore.setBaristaEventHandler(baristaEventHandler);
        return baristaEventHandler;
    }

    @Bean
    public BaristaTimer baristaTimer(final BaristaCommandService baristaCommandService){
        return new BaristaTimer(baristaCommandService);
    }


}
