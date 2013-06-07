package facades;

import entity.RestaurantUser;
import java.util.List;
import javax.ejb.Remote;

/**
 *
 * @author Prakriti
 */
@Remote
public interface RestaurantUserFacadeRemote {

    void create(RestaurantUser restaurantUser);

    void edit(RestaurantUser restaurantUser);

    void remove(RestaurantUser restaurantUser);

    RestaurantUser find(Object id);

    List<RestaurantUser> findAll();

    List<RestaurantUser> findRange(int[] range);

    int count();

    RestaurantUser findByEmail(String email);
    
}
