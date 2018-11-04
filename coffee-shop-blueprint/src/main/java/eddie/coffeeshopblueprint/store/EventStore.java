package eddie.coffeeshopblueprint.store;

import eddie.coffeeshopblueprint.events.*;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.List;

@Component
public class EventStore {
    private List<CoffeeEvent> events = new LinkedList<>();

    public void add(CoffeeEvent event){
        events.add(event);
    }

    public List<CoffeeEvent> getEvents() {
        return events;
    }
}
