package eddie.beanservice.controller;

import eddie.beanservice.store.BeanStorage;
import eddie.coffeeshopcomponent.model.CoffeeOrder;
import eddie.coffeeshopcomponent.model.events.CoffeeEvent;
import eddie.coffeeshopcomponent.store.EventStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/bean/query")
public class QueryController {

    private BeanStorage beanStorage;
    private EventStore eventStore;

    @Autowired
    public QueryController(final BeanStorage beanStorage, final EventStore eventStore){
        this.beanStorage = beanStorage;
        this.eventStore = eventStore;
    }

    @GetMapping
    public int getBean(@RequestParam(value = "origin") String beanOrigin){
        return beanStorage.getRemainingAmount(beanOrigin);
    }

    @GetMapping("events")
    public List<CoffeeEvent> getEvents(){
        return eventStore.getEvents();
    }
}
