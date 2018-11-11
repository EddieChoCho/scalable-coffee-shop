package eddie.beanservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication(scanBasePackages = "eddie.beanservice")
@EnableScheduling
public class BeanServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(BeanServiceApplication.class, args);
    }
}
