package beans;

import entity.MenuItem;
import enums.Category;
import facades.MenuItemFacadeRemote;
import javax.inject.Named;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import stateful.ShoppingCartRemote;

/**
 *
 * @author Prakriti
 */
@Named(value = "menuManagedBean")
@RequestScoped
public class MenuManagedBean implements Serializable {
    @EJB
    private ShoppingCartRemote shoppingCart;
    @EJB
    private MenuItemFacadeRemote menuItemFacade;    
    private HashMap<Category, List<MenuItem>> menu;
    private HashMap<Integer, String> selectedItems;

    public MenuManagedBean() {
        menu = new HashMap<Category, List<MenuItem>>();   
        selectedItems = new HashMap<Integer, String>();
    }

    public HashMap<Integer, String> getSelectedItems() {
        return selectedItems;
    }

    public void setSelectedItems(HashMap<Integer, String> selectedItems) {
        this.selectedItems = selectedItems;
    }

    public HashMap<Category, List<MenuItem>> getMenu() {
        return menu;
    }

    public void setMenu(HashMap<Category, List<MenuItem>> menu) {
        this.menu = menu;
    }
    
    public Category[] getCategories() {
        return Category.values();
    }
        
    @PostConstruct
    public void loadMenuItems() {
        for (Category category : Category.values()) {
            menu.put(category, menuItemFacade.findByCategory(category.toString()));
        }
        for (MenuItem item : menuItemFacade.findAll()) {
            selectedItems.put(item.getId(), "0");
        }
    }
    
    public String addToCart() {
        for (Map.Entry<Integer, String> entry : selectedItems.entrySet()) {
            Integer itemid = entry.getKey();
            Integer quantity = Integer.parseInt(entry.getValue());
            if (quantity != null && quantity > 0) {
                shoppingCart.add(itemid, quantity);
            }
        }
        return "success";
    }
}
