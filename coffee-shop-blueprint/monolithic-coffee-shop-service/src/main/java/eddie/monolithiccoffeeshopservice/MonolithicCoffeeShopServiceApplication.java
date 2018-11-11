package eddie.monolithiccoffeeshopservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication(scanBasePackages = "eddie.monolithiccoffeeshopservice")
@EnableScheduling
public class MonolithicCoffeeShopServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(MonolithicCoffeeShopServiceApplication.class, args);
    }
}
