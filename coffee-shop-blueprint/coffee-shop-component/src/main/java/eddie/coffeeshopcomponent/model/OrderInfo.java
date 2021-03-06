package eddie.coffeeshopcomponent.model;

import eddie.coffeeshopcomponent.model.events.CoffeeType;

import javax.json.JsonObject;
import java.util.UUID;

public class OrderInfo {

    private final UUID orderId;
    private final CoffeeType type;
    private final String beanOrigin;

    public OrderInfo(final CoffeeType type, final String beanOrigin){
        this.type = type;
        this.beanOrigin = beanOrigin;
        orderId = UUID.randomUUID();
    }

    public OrderInfo(final UUID orderId, final CoffeeType type, final String beanOrigin) {
        this.orderId = orderId;
        this.type = type;
        this.beanOrigin = beanOrigin;
    }

    public OrderInfo(JsonObject jsonObject) {
        this(UUID.fromString(jsonObject.getString("orderId")),
                CoffeeType.fromString(jsonObject.getString("type")),
                jsonObject.getString("beanOrigin"));
    }

    public UUID getOrderId() {
        return orderId;
    }

    public CoffeeType getType() {
        return type;
    }

    public String getBeanOrigin() {
        return beanOrigin;
    }

}
