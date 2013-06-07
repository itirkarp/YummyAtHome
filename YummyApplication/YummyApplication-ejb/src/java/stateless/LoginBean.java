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
public class LoginBean implements LoginBeanRemote, LoginBeanLocal {
    @EJB
    private RestaurantUserFacadeLocal restaurantUserFacade;

    @Override
    public boolean login(String email, String password) {
        RestaurantUser user = restaurantUserFacade.findByEmail(email);
        if (user == null) {
            return false;
        }
        return user.getPassword().equals(password);
    }

}
