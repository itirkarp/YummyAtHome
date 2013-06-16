package facades;

import entity.RestaurantUser;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Prakriti
 */
@Stateless
public class RestaurantUserFacade extends AbstractFacade<RestaurantUser> 
    implements RestaurantUserFacadeLocal, facades.RestaurantUserFacadeRemote {
    
    @PersistenceContext(unitName = "YummyApplication-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public RestaurantUserFacade() {
        super(RestaurantUser.class);
    }

    @Override
    public RestaurantUser findByEmail(String email) {
        List<RestaurantUser> users = em.createNamedQuery("RestaurantUser.findByEmail")
                .setParameter("email", email).getResultList();
        if (users.isEmpty()) {
            return null;
        } else {
            return users.get(0);
        }
    }
    
}
