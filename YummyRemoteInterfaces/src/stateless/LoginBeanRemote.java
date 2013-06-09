package stateless;

import entity.RestaurantUser;
import javax.ejb.Remote;

/**
 *
 * @author Prakriti
 */
@Remote
public interface LoginBeanRemote {

    RestaurantUser login(String email, String password);
    
}
