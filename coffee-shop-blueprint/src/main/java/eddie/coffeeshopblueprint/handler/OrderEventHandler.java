package eddie.coffeeshopblueprint.handler;

import eddie.coffeeshopblueprint.events.*;
import eddie.coffeeshopblueprint.service.OrderCommandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderEventHandler implements CoffeeEventHandler {

    private OrderCommandService orderService;

    @Autowired
    public OrderEventHandler(final OrderCommandService orderService){
        this.orderService = orderService;
    }

    @Override
    public void handle(final CoffeeEvent event) {
        if(event instanceof OrderBeansReserved){
            this.handle((OrderBeansReserved)event);
        }else if(event instanceof OrderFailedBeansNotAvailable){
            this.handle((OrderFailedBeansNotAvailable)event);
        }else if(event instanceof CoffeeBrewStarted){
            this.handle((CoffeeBrewStarted)event);
        }else if(event instanceof CoffeeBrewFinished){
            this.handle((CoffeeBrewFinished)event);
        }else if(event instanceof CoffeeDelivered){
            this.handle((CoffeeDelivered)event);
        }
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
