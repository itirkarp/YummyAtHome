package facades;

import entity.RestaurantOrder;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Prakriti
 */
@Stateless
public class RestaurantOrderFacade extends AbstractFacade<RestaurantOrder> implements RestaurantOrderFacadeLocal, facades.RestaurantOrderFacadeRemote {
    @PersistenceContext(unitName = "YummyApplication-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public RestaurantOrderFacade() {
        super(RestaurantOrder.class);
    }
    
}
