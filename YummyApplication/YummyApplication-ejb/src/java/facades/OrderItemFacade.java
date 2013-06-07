package facades;

import entity.OrderItem;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Prakriti
 */
@Stateless
public class OrderItemFacade extends AbstractFacade<OrderItem> implements OrderItemFacadeLocal, facades.OrderItemFacadeRemote {
    @PersistenceContext(unitName = "YummyApplication-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public OrderItemFacade() {
        super(OrderItem.class);
    }
    
}
