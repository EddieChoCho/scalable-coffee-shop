package eddie.coffeeshopcomponent.store;

import eddie.coffeeshopcomponent.model.events.CoffeeEvent;

public interface CoffeeApplier {
    void apply(final CoffeeEvent event);
}
