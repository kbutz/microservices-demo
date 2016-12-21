package grocery;

import com.mysema.query.BooleanBuilder;
import com.mysema.query.types.Predicate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpStatus;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.util.StringUtils.hasText;
import static org.springframework.web.bind.annotation.RequestMethod.GET;

@RestController
@RequestMapping("/grocery")
@RefreshScope  //POST to :8881/refresh to load in new properties from config-server
public class GroceryController {

    private GroceryRepository groceryRepository;

    @Autowired
    public GroceryController(GroceryRepository groceryRepository) {
        this.groceryRepository = groceryRepository;
    }

    @RequestMapping(method = GET)
    List<Grocery> findGroceries()  {
        return (List<Grocery>) groceryRepository.findAll();
    }

//    static class GroceryCriteria {
//
//        static QGrocery talk = QGrocery.grocery;
//
//        String name;
//        String speaker;
//
//        Predicate toPredicate() {
//            BooleanBuilder builder = new BooleanBuilder();
//            if (hasText(name)) {
//                builder.and(talk.title.containsIgnoreCase(name));
//            }
//            if (speaker != null) {
//                builder.and(talk.speaker.containsIgnoreCase(speaker));
//            }
//            return builder.getValue();
//        }
//
//        public void setName(String name) {
//            this.name = name;
//        }
//
//        public void setSpeaker(String speaker) {
//            this.speaker = speaker;
//        }
//    }

}
