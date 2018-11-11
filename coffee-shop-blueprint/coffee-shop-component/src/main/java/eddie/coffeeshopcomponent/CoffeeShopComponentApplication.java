package eddie.coffeeshopcomponent;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication(scanBasePackages = "eddie.coffeeshopcomponent")
@EnableScheduling
public class CoffeeShopComponentApplication {

    public static void main(String[] args) {
        SpringApplication.run(CoffeeShopComponentApplication.class, args);
    }
}
