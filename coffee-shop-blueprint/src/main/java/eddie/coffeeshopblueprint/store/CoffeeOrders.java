package eddie.coffeeshopblueprint.store;

import eddie.coffeeshopblueprint.events.*;
import eddie.coffeeshopblueprint.model.CoffeeOrder;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Consumer;

@Component
public class CoffeeOrders implements CoffeeApplier {

    private Map<UUID, CoffeeOrder> coffeeOrders = new ConcurrentHashMap<>();

    public CoffeeOrder get(final UUID orderId) {
        return coffeeOrders.get(orderId);
    }

    public void apply(final CoffeeEvent event){
        if(event instanceof OrderPlaced){
            this.apply((OrderPlaced)event);
        }else if(event instanceof OrderCancelled){
            this.apply((OrderCancelled)event);
        }else if(event instanceof OrderAccepted){
            this.apply((OrderAccepted)event);
        }else if(event instanceof OrderStarted){
            this.apply((OrderStarted)event);
        }else if(event instanceof OrderFinished){
            this.apply((OrderFinished)event);
        }else if(event instanceof OrderDelivered){
            this.apply((OrderDelivered)event);
        }
    }

    public void apply(final OrderPlaced event) {
        coffeeOrders.putIfAbsent(event.getOrderInfo().getOrderId(), new CoffeeOrder());
        applyFor(event.getOrderInfo().getOrderId(), o -> o.place(event.getOrderInfo()));
    }

    public void apply(final OrderCancelled event) {
        applyFor(event.getOrderId(), CoffeeOrder::cancel);
    }

    public void apply(final OrderAccepted event) {
        applyFor(event.getOrderInfo().getOrderId(), CoffeeOrder::accept);
    }

    public void apply(final OrderStarted event) {
        applyFor(event.getOrderId(), CoffeeOrder::start);
    }

    public void apply(final OrderFinished event) {
        applyFor(event.getOrderId(), CoffeeOrder::finish);
    }

    public void apply(final OrderDelivered event) {
        applyFor(event.getOrderId(), CoffeeOrder::deliver);
    }

    private void applyFor(final UUID orderId, final Consumer<CoffeeOrder> consumer) {
        final CoffeeOrder coffeeOrder = coffeeOrders.get(orderId);
        if (coffeeOrder != null)
            consumer.accept(coffeeOrder);
    }

}
