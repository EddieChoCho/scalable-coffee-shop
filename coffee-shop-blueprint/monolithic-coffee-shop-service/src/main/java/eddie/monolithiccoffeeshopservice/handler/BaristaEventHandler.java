package eddie.monolithiccoffeeshopservice.handler;

import eddie.monolithiccoffeeshopservice.events.CoffeeEvent;
import eddie.monolithiccoffeeshopservice.events.OrderAccepted;
import eddie.monolithiccoffeeshopservice.service.BaristaCommandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BaristaEventHandler implements CoffeeEventHandler {

    private BaristaCommandService baristaService;

    @Autowired
    public BaristaEventHandler(final BaristaCommandService baristaService){
        this.baristaService = baristaService;
    }

    @Override
    public void handle(final CoffeeEvent event) {
        if(event instanceof OrderAccepted){
            this.handle((OrderAccepted)event);
        }
    }

    public void handle(final OrderAccepted event) {
        baristaService.makeCoffee(event.getOrderInfo());
    }
}
