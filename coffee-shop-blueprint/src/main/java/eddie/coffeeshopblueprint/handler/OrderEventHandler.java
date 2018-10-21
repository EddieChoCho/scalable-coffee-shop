package eddie.coffeeshopblueprint.handler;

import eddie.coffeeshopblueprint.events.*;
import eddie.coffeeshopblueprint.service.OrderCommandService;

public class OrderEventHandler {

    private OrderCommandService orderService;

    public OrderEventHandler(final OrderCommandService orderService){
        this.orderService = orderService;
    }

    public void handle(final OrderBeansReserved event) {
        orderService.acceptOrder(event.getOrderId());
    }

    public void handle(final OrderFailedBeansNotAvailable event) {
        orderService.cancelOrder(event.getOrderId(), "No beans of the origin were available");
    }

    public void handle(final CoffeeBrewStarted event) {
        orderService.startOrder(event.getOrderInfo().getOrderId());
    }

    public void handle(final CoffeeBrewFinished event) {
        orderService.finishOrder(event.getOrderId());
    }

    public void handle(final CoffeeDelivered event) {
        orderService.deliverOrder(event.getOrderId());
    }

}
