package eddie.monolithiccoffeeshopservice.controller;

import eddie.monolithiccoffeeshopservice.events.CoffeeEvent;
import eddie.monolithiccoffeeshopservice.model.CoffeeOrder;
import eddie.monolithiccoffeeshopservice.store.BeanStorage;
import eddie.monolithiccoffeeshopservice.store.CoffeeOrders;
import eddie.monolithiccoffeeshopservice.store.EventStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/query/")
public class QueryController {

    private CoffeeOrders coffeeOrders;
    private BeanStorage beanStorage;
    private EventStore eventStore;

    @Autowired
    public QueryController(final CoffeeOrders coffeeOrders, final BeanStorage beanStorage, final EventStore eventStore){
        this.coffeeOrders = coffeeOrders;
        this.beanStorage = beanStorage;
        this.eventStore = eventStore;
    }

    @GetMapping("order")
    public CoffeeOrder getOrder(@RequestParam(value = "orderId") String uuid){
        return coffeeOrders.get(UUID.fromString(uuid));
    }

    @GetMapping("bean")
    public int getBean(@RequestParam(value = "origin") String beanOrigin){
        return beanStorage.getRemainingAmount(beanOrigin);
    }

    @GetMapping("events")
    public List<CoffeeEvent> getEvents(){
        return eventStore.getEvents();
    }
}
