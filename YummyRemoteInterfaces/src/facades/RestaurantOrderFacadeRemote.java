package facades;

import entity.CartItem;
import entity.RestaurantOrder;
import entity.RestaurantUser;
import java.util.List;
import javax.ejb.Remote;

/**
 *
 * @author Prakriti
 */
@Remote
public interface RestaurantOrderFacadeRemote {

    void create(RestaurantOrder restaurantOrder);

    void edit(RestaurantOrder restaurantOrder);

    void remove(RestaurantOrder restaurantOrder);

    RestaurantOrder find(Object id);

    List<RestaurantOrder> findAll();

    List<RestaurantOrder> findRange(int[] range);

    int count();

    void saveOrder(List<CartItem> items, RestaurantUser user);
}
