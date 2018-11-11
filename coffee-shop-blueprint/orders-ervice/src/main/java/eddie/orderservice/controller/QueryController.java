package eddie.orderservice.controller;

import eddie.coffeeshopcomponent.model.CoffeeOrder;
import eddie.coffeeshopcomponent.model.events.CoffeeEvent;
import eddie.coffeeshopcomponent.store.EventStore;
import eddie.orderservice.store.CoffeeOrders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/order/query")
public class QueryController {

    private CoffeeOrders coffeeOrders;
    private EventStore eventStore;

    @Autowired
    public QueryController(final CoffeeOrders coffeeOrders, final EventStore eventStore){
        this.coffeeOrders = coffeeOrders;
        this.eventStore = eventStore;
    }

    @GetMapping
    public CoffeeOrder getOrder(@RequestParam(value = "orderId") String uuid){
        return coffeeOrders.get(UUID.fromString(uuid));
    }

    @GetMapping("events")
    public List<CoffeeEvent> getEvents(){
        return eventStore.getEvents();
    }
}
