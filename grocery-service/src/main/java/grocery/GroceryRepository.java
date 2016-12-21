package grocery;

import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by kylebutz1 on 12/13/2016.
 */
public interface GroceryRepository extends CrudRepository<Grocery, Integer> {
}
