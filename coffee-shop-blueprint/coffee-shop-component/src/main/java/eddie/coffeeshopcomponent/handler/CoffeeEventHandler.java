package eddie.coffeeshopcomponent.handler;

import eddie.coffeeshopcomponent.model.events.CoffeeEvent;

public interface CoffeeEventHandler {

    void handle(final CoffeeEvent event);
}
