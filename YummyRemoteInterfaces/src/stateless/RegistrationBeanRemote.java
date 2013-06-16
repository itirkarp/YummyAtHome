package stateless;

import entity.RestaurantUser;
import javax.ejb.Remote;

/**
 *
 * @author Prakriti
 */
@Remote
public interface RegistrationBeanRemote {

    RestaurantUser register(String email, String password, String phone, 
            String name, String address);
    
}
