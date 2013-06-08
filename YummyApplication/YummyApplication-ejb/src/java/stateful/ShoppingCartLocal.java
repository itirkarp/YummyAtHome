package stateful;

import entity.CartItem;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Prakriti
 */
@Local
public interface ShoppingCartLocal {

    boolean add(Integer itemid, Integer quantity);
    
    public List<CartItem> getItems();

    void clear();
    
}
