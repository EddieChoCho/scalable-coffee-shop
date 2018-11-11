package eddie.monolithiccoffeeshopservice.handler;

import eddie.monolithiccoffeeshopservice.events.CoffeeEvent;

public interface CoffeeEventHandler {

    void handle(final CoffeeEvent event);
}
