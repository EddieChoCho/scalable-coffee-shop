package eddie.coffeeshopblueprint.store;

import eddie.coffeeshopblueprint.events.CoffeeEvent;

public interface CoffeeApplier {
    void apply(final CoffeeEvent event);
}
