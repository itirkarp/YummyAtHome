package facades;

import entity.RestaurantOrder;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Prakriti
 */
@Local
public interface RestaurantOrderFacadeLocal {

    void create(RestaurantOrder restaurantOrder);

    void edit(RestaurantOrder restaurantOrder);

    void remove(RestaurantOrder restaurantOrder);

    RestaurantOrder find(Object id);

    List<RestaurantOrder> findAll();

    List<RestaurantOrder> findRange(int[] range);

    int count();
    
}
