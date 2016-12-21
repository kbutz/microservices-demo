package consume;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class GroceryService {

    private RestTemplate restTemplate;

    private Logger logger = LoggerFactory.getLogger(getClass());
    private volatile Grocery[] groceries = new Grocery[0];

    @Autowired
    public GroceryService(@LoadBalanced RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @HystrixCommand(fallbackMethod = "cachedGroceryList", threadPoolProperties = @HystrixProperty(name = "coreSize", value = "20"))
    public Grocery[] allGroceries() {
        groceries = restTemplate.getForObject("http://grocery-service/grocery", Grocery[].class);
        logger.info("Returning latest grocery list");
        return groceries;
    }

    Grocery[] cachedGroceryList() {
        logger.info("Returning cached grocery list");
        return groceries;
    }


}
