package eddie.beanservice.handler;

import eddie.beanservice.service.BeanCommandService;
import eddie.beanservice.store.BeanStorage;
import eddie.coffeeshopcomponent.handler.CoffeeEventHandler;
import eddie.coffeeshopcomponent.model.events.*;
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
