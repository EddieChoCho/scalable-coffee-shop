package eddie.beanservice.controller;

import eddie.beanservice.service.BeanCommandService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/bean/command")
public class CommandController {

    private BeanCommandService beanCommandService;

    public CommandController(final BeanCommandService beanCommandService){
        this.beanCommandService = beanCommandService;
    }

    @PostMapping
    public String storeBeans(@RequestParam(value = "bean") String beanOrigin, @RequestParam(value = "amount") int amount){
        beanCommandService.storeBeans(beanOrigin, amount);
        return beanOrigin;
    }
}
