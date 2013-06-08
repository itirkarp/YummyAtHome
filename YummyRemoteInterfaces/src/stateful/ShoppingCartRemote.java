package stateful;

import javax.ejb.Remote;

/**
 *
 * @author Prakriti
 */
@Remote
public interface ShoppingCartRemote {

    boolean add(Integer itemid, Integer quantity);
    
}
