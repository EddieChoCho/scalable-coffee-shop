package eddie.coffeeshopblueprint.handler;

import eddie.coffeeshopblueprint.events.CoffeeEvent;

public interface CoffeeEventHandler {

    void handle(final CoffeeEvent event);
}
