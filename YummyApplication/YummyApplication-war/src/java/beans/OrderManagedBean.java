package beans;

import entity.CartItem;
import facades.RestaurantOrderFacadeRemote;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
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
import stateful.ShoppingCartRemote;

/**
 *
 * @author Prakriti
 */
@Named(value = "orderManagedBean")
@RequestScoped
public class OrderManagedBean {
    @Resource(mappedName = "jms/yummyqueue")
    private Queue yummyqueue;
    @Resource(mappedName = "jms/yummyqueueFactory")
    private ConnectionFactory yummyqueueFactory;
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
        HttpServletRequest request = (HttpServletRequest) FacesContext
                .getCurrentInstance().getExternalContext().getRequest();
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
        Integer orderId = restaurantOrderFacade.saveOrder(items, 
                loginBean.getCurrentUser());
        try {
            sendJMSMessageToYummyqueue(orderId.toString());
        } catch (JMSException ex) {
            System.out.println("Could not send message to order email queue: " 
                    + ex.getMessage());
            Logger.getLogger(OrderManagedBean.class.getName()).log(
                    Level.SEVERE, null, ex);
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

    private void sendJMSMessageToYummyqueue(Object messageData) 
            throws JMSException {
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
                    Logger.getLogger(this.getClass().getName()).log(Level.WARNING, 
                            "Cannot close session", e);
                }
            }
            if (connection != null) {
                connection.close();
            }
        }
    }
    
    
}
