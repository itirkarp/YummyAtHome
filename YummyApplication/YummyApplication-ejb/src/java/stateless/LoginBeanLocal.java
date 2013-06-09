package stateless;

import entity.RestaurantUser;
import javax.ejb.Local;

/**
 *
 * @author Prakriti
 */
@Local
public interface LoginBeanLocal {

    RestaurantUser login(String email, String password);
    
}
