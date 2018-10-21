package eddie.coffeeshopblueprint.handler;

import eddie.coffeeshopblueprint.events.OrderPlaced;
import eddie.coffeeshopblueprint.service.BeanCommandService;

public class BeanEventHandler {

    private BeanCommandService beanService;

    public BeanEventHandler(final BeanCommandService beanService){
        this.beanService = beanService;
    }

    public void handle(final OrderPlaced event) {
        beanService.reserveBeans(event.getOrderInfo().getBeanOrigin(), event.getOrderInfo().getOrderId());
    }


}
