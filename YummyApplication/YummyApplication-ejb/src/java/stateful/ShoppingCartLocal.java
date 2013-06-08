package stateful;

import javax.ejb.Local;

/**
 *
 * @author Prakriti
 */
@Local
public interface ShoppingCartLocal {

    boolean add(Integer itemid, Integer quantity);
    
}
