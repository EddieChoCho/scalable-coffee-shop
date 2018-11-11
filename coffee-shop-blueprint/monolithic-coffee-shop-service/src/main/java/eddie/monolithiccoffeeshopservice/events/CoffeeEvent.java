package eddie.monolithiccoffeeshopservice.events;

import com.google.gson.GsonBuilder;
import lombok.Data;

import javax.json.bind.annotation.JsonbProperty;
import java.time.Instant;
import java.util.Objects;

@Data
public abstract class CoffeeEvent {

    private final String eventName = this.getClass().getSimpleName();

    @JsonbProperty
    private final Instant instant;

    protected CoffeeEvent() {
        instant = Instant.now();
    }

    protected CoffeeEvent(final Instant instant) {
        Objects.requireNonNull(instant);
        this.instant = instant;
    }

    public Instant getInstant() {
        return instant;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        final CoffeeEvent that = (CoffeeEvent) o;

        return instant.equals(that.instant);
    }

    @Override
    public int hashCode() {
        return instant.hashCode();
    }

    @Override
    public String toString() {
        return new GsonBuilder().create().toJson(this);
    }
}
