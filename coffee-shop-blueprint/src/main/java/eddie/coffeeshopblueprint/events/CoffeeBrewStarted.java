package eddie.coffeeshopblueprint.events;

import eddie.coffeeshopblueprint.model.OrderInfo;

import javax.json.JsonObject;
import javax.json.bind.annotation.JsonbProperty;
import java.time.Instant;

public class CoffeeBrewStarted extends CoffeeEvent {

    @JsonbProperty
    private final OrderInfo orderInfo;

    public CoffeeBrewStarted(OrderInfo orderInfo) {
        this.orderInfo = orderInfo;
    }

    public CoffeeBrewStarted(OrderInfo orderInfo, Instant instant) {
        super(instant);
        this.orderInfo = orderInfo;
    }

    public CoffeeBrewStarted(JsonObject jsonObject) {
        this(new OrderInfo(jsonObject.getJsonObject("orderInfo")), Instant.parse(jsonObject.getString("instant")));
    }

    public OrderInfo getOrderInfo() {
        return orderInfo;
    }

}
