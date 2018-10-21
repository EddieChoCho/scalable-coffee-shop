package eddie.coffeeshopblueprint.events;

import java.util.stream.Stream;

public enum CoffeeType {

    ESPRESSO, POUR_OVER, FRENCH_PRESS;

    public static CoffeeType fromString(final String name) {
        return Stream.of(values())
                .filter(v -> v.name().equalsIgnoreCase(name))
                .findAny().orElse(null);
    }

}
