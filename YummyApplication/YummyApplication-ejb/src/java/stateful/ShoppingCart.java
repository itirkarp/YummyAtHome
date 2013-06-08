package stateful;

import entity.CartItem;
import facades.MenuItemFacadeLocal;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateful;

/**
 *
 * @author Prakriti
 */
@Stateful
public class ShoppingCart implements ShoppingCartRemote, ShoppingCartLocal {
    @EJB
    private MenuItemFacadeLocal menuItemFacade;
    private List<CartItem> items;

    public ShoppingCart() {
        items = new ArrayList<CartItem>();
    }
    
    @Override
    public boolean add(Integer itemid, Integer quantity) {
        if (menuItemFacade.find(itemid) == null) {
            return false;
        }
        items.add(new CartItem(itemid, quantity));
        return true;
    }

}
