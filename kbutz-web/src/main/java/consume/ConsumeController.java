package consume;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ConsumeController {

    private KbutzService kbutzService;
    private GroceryService groceryService;

    @Autowired
    public ConsumeController(KbutzService kbutzService, GroceryService groceryService) {
        this.kbutzService = kbutzService;
        this.groceryService = groceryService;
    }

    @RequestMapping("/helloworld")
    String getHello(Model model) {
        model.addAttribute("helloString", kbutzService.helloConsumed());
        return "index";
    }

    @RequestMapping("/")
    String getGroceryList(Model model) {
        model.addAttribute("groceries", groceryService.allGroceries());
        return "groceries";
    }
}
