package eddie.coffeeshopblueprint.handler;

import eddie.coffeeshopblueprint.service.BaristaCommandService;
import org.springframework.scheduling.annotation.Scheduled;



public class BaristaTimer {

    private BaristaCommandService baristaService;

    public BaristaTimer(final BaristaCommandService baristaService){
        this.baristaService = baristaService;
    }

    @Scheduled(cron = "*/7 * * * * *")
    void checkCoffee() {
        baristaService.checkCoffee();
    }

    @Scheduled(cron = "*/8 * * * * *")
    void checkCustomerDelivery() {
        baristaService.checkCustomerDelivery();
    }

}
