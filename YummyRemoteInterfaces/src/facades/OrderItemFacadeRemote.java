package facades;

import entity.OrderItem;
import java.util.List;
import javax.ejb.Remote;

/**
 *
 * @author Prakriti
 */
@Remote
public interface OrderItemFacadeRemote {

    void create(OrderItem orderItem);

    void edit(OrderItem orderItem);

    void remove(OrderItem orderItem);

    OrderItem find(Object id);

    List<OrderItem> findAll();

    List<OrderItem> findRange(int[] range);

    int count();
    
}
