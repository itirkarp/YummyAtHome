package stateless;

import entity.RestaurantUser;
import facades.RestaurantUserFacadeLocal;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author Prakriti
 */
@Stateless
public class RegistrationBean implements RegistrationBeanRemote, RegistrationBeanLocal {
    @EJB
    private RestaurantUserFacadeLocal restaurantUserFacade;

    @Override
    public RestaurantUser register(String email, String password, String phone, 
    String name, String address) {
        if (restaurantUserFacade.findByEmail(email)!= null) {
            return null;
        }
        RestaurantUser user = new RestaurantUser(name, password, email, phone, address);
        restaurantUserFacade.create(user);
        return user;
    }


}
