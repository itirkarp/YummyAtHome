package beans;

import entity.CartItem;
import entity.MenuItem;
import enums.Category;
import facades.MenuItemFacadeRemote;
import facades.RestaurantOrderFacadeRemote;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Prakriti
 */
@Named(value = "menuManagedBean")
@SessionScoped
public class MenuManagedBean implements Serializable {
    @EJB
    private MenuItemFacadeRemote menuItemFacade;    
    @Resource(mappedName = "jms/yummyqueue")
    private Queue yummyqueue;
    @Resource(mappedName = "jms/yummyqueueFactory")
    private ConnectionFactory yummyqueueFactory;
    @EJB
    private RestaurantOrderFacadeRemote restaurantOrderFacade;
    @Inject
    LoginManagedBean loginBean;

    private HashMap<Category, List<MenuItem>> menu;
    private HashMap<Integer, String> selectedItems;
    private List<CartItem> cartItems;

    public List<CartItem> getCartItems() {
        return cartItems;
    }

    public void setCartItems(List<CartItem> cartItems) {
        this.cartItems = cartItems;
    }

    public MenuManagedBean() {
        menu = new HashMap<Category, List<MenuItem>>();   
        selectedItems = new HashMap<Integer, String>();
        cartItems = new ArrayList<CartItem>();
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
    public void init() {
        loadMenuItems();
        loadSelectedItems();
    }
    
    private void loadMenuItems() {
        for (Category category : Category.values()) {
            menu.put(category, menuItemFacade.findByCategory(category.toString()));
        }
        for (MenuItem item : menuItemFacade.findAll()) {
            selectedItems.put(item.getId(), "0");
        }
    }
    
    private void loadSelectedItems() {
        for (CartItem cartItem : cartItems) {
            selectedItems.put(cartItem.getId(), cartItem.quantity.toString());
        }
    }
    
    public String addToCart() {
        cartItems.clear();
        int numberSelected = 0;
        for (Map.Entry<Integer, String> entry : selectedItems.entrySet()) {
            Integer itemid = entry.getKey();
            Integer quantity = Integer.parseInt(entry.getValue());
            if (quantity != null && quantity > 0) {
                MenuItem item = menuItemFacade.find(itemid);
                if (item != null) {
                    cartItems.add(new CartItem(itemid, quantity, item.getName(), item.getPrice()));
                }
                numberSelected++;
            }
        }
        if (numberSelected == 0) {
            FacesContext.getCurrentInstance().addMessage(null, 
                    new FacesMessage("Please select some items to add."));
            return "failure";
        }
        return "success";
    }
    
    public List<CartItem> getItems() {
        return cartItems;
    }

    public float getOrderTotal() {
        float total = 0;
        for (CartItem item : cartItems) {
            total += item.getPrice() * item.getQuantity();
        }
        return total;
    }
    
    public String checkout() {
        Integer orderId = restaurantOrderFacade.saveOrder(cartItems, loginBean.getCurrentUser());
        try {
            sendJMSMessageToYummyqueue(orderId.toString());
        } catch (JMSException ex) {
            System.out.println("Could not send message to order email queue: " + ex.getMessage());
            Logger.getLogger(MenuManagedBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        HttpServletRequest request = (HttpServletRequest) FacesContext
                .getCurrentInstance().getExternalContext().getRequest();
        HttpSession session = request.getSession();
        session.invalidate();
        return "checkout";
    }

    private Message createJMSMessageForjmsYummyqueue(Session session, 
            Object messageData) throws JMSException {
        TextMessage tm = session.createTextMessage();
        tm.setText(messageData.toString());
        return tm;
    }

    private void sendJMSMessageToYummyqueue(Object messageData) throws JMSException {
        Connection connection = null;
        Session session = null;
        try {
            connection = yummyqueueFactory.createConnection();
            session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            MessageProducer messageProducer = session.createProducer(yummyqueue);
            messageProducer.send(createJMSMessageForjmsYummyqueue(session, messageData));
        } finally {
            if (session != null) {
                try {
                    session.close();
                } catch (JMSException e) {
                    Logger.getLogger(this.getClass().getName()).log(Level.WARNING, "Cannot close session", e);
                }
            }
            if (connection != null) {
                connection.close();
            }
        }
    }

}
