package facades;

import entity.RestaurantUser;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Prakriti
 */
@Local
public interface RestaurantUserFacadeLocal {

    void create(RestaurantUser restaurantUser);

    void edit(RestaurantUser restaurantUser);

    void remove(RestaurantUser restaurantUser);

    RestaurantUser find(Object id);

    List<RestaurantUser> findAll();

    List<RestaurantUser> findRange(int[] range);

    int count();

    RestaurantUser findByEmail(String email);
    
}
