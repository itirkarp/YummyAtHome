package stateful;

import entity.CartItem;
import java.util.List;
import javax.ejb.Remote;

/**
 *
 * @author Prakriti
 */
@Remote
public interface ShoppingCartRemote {

    boolean add(Integer itemid, Integer quantity);
    
    public List<CartItem> getItems();

    void clear();
    
}
