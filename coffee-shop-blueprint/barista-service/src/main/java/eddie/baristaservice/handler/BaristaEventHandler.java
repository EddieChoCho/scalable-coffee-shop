package eddie.baristaservice.handler;

import eddie.baristaservice.service.BaristaCommandService;
import eddie.coffeeshopcomponent.handler.CoffeeEventHandler;
import eddie.coffeeshopcomponent.model.events.CoffeeEvent;
import eddie.coffeeshopcomponent.model.events.OrderAccepted;
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
