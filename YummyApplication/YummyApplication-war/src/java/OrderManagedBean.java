
import beans.LoginManagedBean;
import entity.CartItem;
import facades.RestaurantOrderFacadeRemote;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import stateful.ShoppingCartRemote;

/**
 *
 * @author Prakriti
 */
@Named(value = "orderManagedBean")
@RequestScoped
public class OrderManagedBean {
    @EJB
    private RestaurantOrderFacadeRemote restaurantOrderFacade;
    @EJB
    private ShoppingCartRemote shoppingCart;
    private List<CartItem> items;
    @Inject
    LoginManagedBean loginBean;

    public OrderManagedBean() {
        items = new ArrayList<CartItem>();
    }

    public List<CartItem> getItems() {
        return items;
    }

    public void setItems(List<CartItem> items) {
        this.items = items;
    }

    @PostConstruct
    public void loadCart() {
        // works only if menumanagedbean is request scoped
//        FacesContext context = FacesContext.getCurrentInstance();
//        MenuManagedBean menuBean = (MenuManagedBean) context.getApplication()
//                .evaluateExpressionGet(context, "#{menuManagedBean}", MenuManagedBean.class);
//        items = menuBean.getShoppingCart().getItems();
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        HttpSession session = request.getSession();
        shoppingCart = (ShoppingCartRemote) session.getAttribute("shoppingCart");
        if (shoppingCart != null) {
            items = shoppingCart.getItems();
        }
    }

    public float getOrderTotal() {
        float total = 0;
        for (CartItem item : items) {
            total += item.getPrice() * item.getQuantity();
        }
        return total;
    }
    
    public String checkout() {
        // TODO: Send email with final order
        restaurantOrderFacade.saveOrder(items, loginBean.getCurrentUser());
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        HttpSession session = request.getSession();
        session.invalidate();
        return "checkout";
    }
    
    
}
