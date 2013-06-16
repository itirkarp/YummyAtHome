package facades;

import entity.CartItem;
import entity.MenuItem;
import entity.OrderItem;
import entity.RestaurantOrder;
import entity.RestaurantUser;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Prakriti
 */
@Stateless
public class RestaurantOrderFacade extends AbstractFacade<RestaurantOrder> 
    implements RestaurantOrderFacadeLocal, facades.RestaurantOrderFacadeRemote {
    
    @EJB
    private OrderItemFacadeLocal orderItemFacade;
    @EJB
    private RestaurantUserFacadeLocal restaurantUserFacade;
    @EJB
    private MenuItemFacadeLocal menuItemFacade;
    @PersistenceContext(unitName = "YummyApplication-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public RestaurantOrderFacade() {
        super(RestaurantOrder.class);
    }

    @Override
    public Integer saveOrder(List<CartItem> items, RestaurantUser user) {
        RestaurantOrder order = new RestaurantOrder();
        order.setOrderdate(new Date());
        order.setUserid(user);
        create(order);
        
        float total = 0;
        for (CartItem cartItem : items) {
            total += cartItem.getPrice() * cartItem.getQuantity();
            MenuItem menuItem = menuItemFacade.find(cartItem.getId());
            OrderItem orderItem = new OrderItem(cartItem.getQuantity(), order, menuItem);
            orderItemFacade.create(orderItem);
        }
        order.setTotal(total);
        edit(order);
        em.flush();
        return order.getId();
    }
}
