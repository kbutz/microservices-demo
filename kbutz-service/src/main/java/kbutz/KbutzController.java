package kbutz;

import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/kbutz")
@RefreshScope  //POST to :8881/refresh to load in new properties from config-server
public class KbutzController {
    @RequestMapping("/")
    String helloWorld() {
        return "Hello, World called from kbutz-service/kbutz!";
    }
}
