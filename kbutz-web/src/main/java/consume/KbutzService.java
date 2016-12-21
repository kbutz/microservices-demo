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
public class KbutzService {

    private RestTemplate restTemplate;


    private Logger logger = LoggerFactory.getLogger(getClass());
    private volatile String helloString = "Default Hello World.";

    @Autowired  // @LoadBalanced is used to tell spring cloud to use Ribbon.
    public KbutzService(@LoadBalanced RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @HystrixCommand(fallbackMethod = "cachedHelloWorld", threadPoolProperties = @HystrixProperty(name = "coreSize", value = "20"))
    public String helloConsumed() {
        helloString = restTemplate.getForObject("http://kbutz-service/kbutz/", String.class);
        logger.info("Returning latest Hello World");
        return helloString;
    }

    String cachedHelloWorld() {
        logger.info("Returning cached calls");
        return helloString;
    }

//    public Talk talkDetails(Long talkId) {
//        Talk talk = restTemplate.getForObject("http://talk-service/talk/{id}", Talk.class, talkId);
//        talk.setReviews(reviewService.reviewsFor(talkId));
//        return talk;
//    }
}

