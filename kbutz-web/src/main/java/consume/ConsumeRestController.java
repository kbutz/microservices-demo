package consume;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/api")
public class ConsumeRestController {

    @Autowired
    private GroceryService groceryService;

    // GET ALL
    @RequestMapping(value = "/groceries", method = RequestMethod.GET)
    public Grocery[] getGroceryList() {
        Grocery[] groceries = groceryService.allGroceries();
        return groceries;
    }
}
