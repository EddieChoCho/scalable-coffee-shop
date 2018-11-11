package eddie.monolithiccoffeeshopservice.store;

import eddie.monolithiccoffeeshopservice.events.CoffeeEvent;

public interface CoffeeApplier {
    void apply(final CoffeeEvent event);
}
