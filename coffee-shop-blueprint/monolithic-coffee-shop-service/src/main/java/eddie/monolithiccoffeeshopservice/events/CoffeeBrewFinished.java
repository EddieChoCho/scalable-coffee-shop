package eddie.monolithiccoffeeshopservice.events;

import javax.json.JsonObject;
import java.time.Instant;
import java.util.UUID;

public class CoffeeBrewFinished extends CoffeeEvent {

    private final UUID orderId;

    public CoffeeBrewFinished(final UUID orderId) {
        this.orderId = orderId;
    }

    public CoffeeBrewFinished(final UUID orderId, Instant instant) {
        super(instant);
        this.orderId = orderId;
    }

    public CoffeeBrewFinished(JsonObject jsonObject) {
        this(UUID.fromString(jsonObject.getString("orderId")), Instant.parse(jsonObject.getString("instant")));
    }

    public UUID getOrderId() {
        return orderId;
    }

}
