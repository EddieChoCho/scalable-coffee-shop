package eddie.orderservice.controller;

import eddie.coffeeshopcomponent.model.OrderInfo;
import eddie.coffeeshopcomponent.model.events.CoffeeType;
import eddie.orderservice.service.OrderCommandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/order/command")
public class CommandController {

    private OrderCommandService orderCommandService;

    @Autowired
    public CommandController(final OrderCommandService orderCommandService){
        this.orderCommandService = orderCommandService;
    }

    @PostMapping
    public UUID placeOrder(@RequestParam(value = "type") CoffeeType type, @RequestParam(value = "bean") String beanOrigin){
        OrderInfo orderInfo = new OrderInfo(type,beanOrigin);
        orderCommandService.placeOrder(orderInfo);
        return orderInfo.getOrderId();
    }
}
