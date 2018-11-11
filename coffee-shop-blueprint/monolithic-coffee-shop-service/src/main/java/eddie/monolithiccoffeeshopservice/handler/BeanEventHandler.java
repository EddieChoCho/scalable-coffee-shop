package eddie.monolithiccoffeeshopservice.handler;

import eddie.monolithiccoffeeshopservice.events.CoffeeEvent;
import eddie.monolithiccoffeeshopservice.events.OrderPlaced;
import eddie.monolithiccoffeeshopservice.service.BeanCommandService;
import eddie.monolithiccoffeeshopservice.store.BeanStorage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BeanEventHandler implements CoffeeEventHandler {

    private BeanCommandService beanService;

    @Autowired
    public BeanEventHandler(final BeanCommandService beanService, final BeanStorage beanStorage){
        this.beanService = beanService;
    }

    @Override
    public void handle(final CoffeeEvent event) {
        if(event instanceof OrderPlaced){
            this.handle((OrderPlaced)event);
        }
    }

    public void handle(final OrderPlaced event) {
        beanService.reserveBeans(event.getOrderInfo().getBeanOrigin(), event.getOrderInfo().getOrderId());
    }

}
