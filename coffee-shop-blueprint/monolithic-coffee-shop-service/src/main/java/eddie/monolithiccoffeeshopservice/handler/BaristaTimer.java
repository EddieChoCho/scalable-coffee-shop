package eddie.monolithiccoffeeshopservice.handler;

import eddie.monolithiccoffeeshopservice.service.BaristaCommandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class BaristaTimer {

    private BaristaCommandService baristaService;

    @Autowired
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
