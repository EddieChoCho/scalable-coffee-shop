package eddie.coffeeshopblueprint.handler;


import eddie.coffeeshopblueprint.events.OrderAccepted;
import eddie.coffeeshopblueprint.service.BaristaCommandService;

public class BaristaEventHandler {

    private BaristaCommandService baristaService;

    public BaristaEventHandler(final BaristaCommandService baristaService){
        this.baristaService = baristaService;
    }

    public void handle(OrderAccepted event) {
        baristaService.makeCoffee(event.getOrderInfo());
    }

}
