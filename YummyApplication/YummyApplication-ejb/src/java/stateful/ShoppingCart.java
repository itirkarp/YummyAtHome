package stateful;

import entity.CartItem;
import entity.MenuItem;
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
    public List<CartItem> getItems() {
        return items;
    }
    
    @Override
    public boolean add(Integer itemid, Integer quantity) {
        MenuItem item = menuItemFacade.find(itemid);
        if (item == null) {
            return false;
        }
        items.add(new CartItem(itemid, quantity, item.getName(), 
                item.getPrice()));
        return true;
    }

    @Override
    public void clear() {
        items = new ArrayList<CartItem>();
    }

}
