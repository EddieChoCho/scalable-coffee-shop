package eddie.coffeeshopblueprint;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication(scanBasePackages = "eddie.coffeeshopblueprint")
@EnableScheduling
public class CoffeeShopBlueprintApplication {

    public static void main(String[] args) {
        SpringApplication.run(CoffeeShopBlueprintApplication.class, args);
    }
}
