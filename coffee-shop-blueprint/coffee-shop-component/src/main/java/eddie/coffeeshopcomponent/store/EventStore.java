package eddie.coffeeshopcomponent.store;


import eddie.coffeeshopcomponent.model.events.CoffeeEvent;

import java.util.ArrayList;
import java.util.List;

public class EventStore {
    private List<CoffeeEvent> events = new ArrayList<>();

    public void add(CoffeeEvent event){
        events.add(event);
    }

    public List<CoffeeEvent> getEvents() {
        return events;
    }
}
