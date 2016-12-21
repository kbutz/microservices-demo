package consume;

import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;

@SpringCloudApplication  // <- 3-for-1
public class ConsumeApp {
    public static void main(String[] args) {
        SpringApplication.run(ConsumeApp.class, args);
    }
}