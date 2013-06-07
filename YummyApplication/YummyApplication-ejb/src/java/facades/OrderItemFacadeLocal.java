package facades;

import entity.OrderItem;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Prakriti
 */
@Local
public interface OrderItemFacadeLocal {

    void create(OrderItem orderItem);

    void edit(OrderItem orderItem);

    void remove(OrderItem orderItem);

    OrderItem find(Object id);

    List<OrderItem> findAll();

    List<OrderItem> findRange(int[] range);

    int count();
    
}
