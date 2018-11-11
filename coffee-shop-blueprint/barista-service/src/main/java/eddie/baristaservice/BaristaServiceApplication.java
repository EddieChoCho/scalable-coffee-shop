package eddie.baristaservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication(scanBasePackages = "eddie.baristaservice")
@EnableScheduling
public class BaristaServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(BaristaServiceApplication.class, args);
    }
}
