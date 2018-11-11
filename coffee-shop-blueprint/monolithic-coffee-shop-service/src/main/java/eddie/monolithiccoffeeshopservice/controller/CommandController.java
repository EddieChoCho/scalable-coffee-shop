package eddie.monolithiccoffeeshopservice.controller;


import eddie.monolithiccoffeeshopservice.events.CoffeeType;
import eddie.monolithiccoffeeshopservice.model.OrderInfo;
import eddie.monolithiccoffeeshopservice.service.BeanCommandService;
import eddie.monolithiccoffeeshopservice.service.OrderCommandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/command/")
public class CommandController {

    private OrderCommandService orderCommandService;
    private BeanCommandService beanCommandService;

    @Autowired
    public CommandController(final OrderCommandService orderCommandService, final BeanCommandService beanCommandService){
        this.orderCommandService = orderCommandService;
        this.beanCommandService = beanCommandService;
    }

    @PostMapping("order")
    public UUID placeOrder(@RequestParam(value = "type") CoffeeType type, @RequestParam(value = "bean") String beanOrigin){
        OrderInfo orderInfo = new OrderInfo(type,beanOrigin);
        orderCommandService.placeOrder(orderInfo);
        return orderInfo.getOrderId();
    }


    @PostMapping("beans")
    public String storeBeans(@RequestParam(value = "bean") String beanOrigin, @RequestParam(value = "amount") int amount){
        beanCommandService.storeBeans(beanOrigin, amount);
        return beanOrigin;
    }

}
