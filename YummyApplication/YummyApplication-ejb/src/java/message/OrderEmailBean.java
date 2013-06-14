package message;

import entity.RestaurantOrder;
import facades.RestaurantOrderFacadeLocal;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.ejb.ActivationConfigProperty;
import javax.ejb.EJB;
import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author Prakriti
 */
@MessageDriven(mappedName = "jms/yummyqueue", activationConfig = {
    @ActivationConfigProperty(propertyName = "acknowledgeMode", propertyValue = "Auto-acknowledge"),
    @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue")
})
public class OrderEmailBean implements MessageListener {
    @EJB
    private RestaurantOrderFacadeLocal restaurantOrderFacade;
    @Resource(name = "mail/yummyMailSession")
    private Session mailSession;

    public OrderEmailBean() {
    }
    
    @Override
    public void onMessage(Message message) {
        try {
            TextMessage msg = (TextMessage) message;
            RestaurantOrder order;
            order = restaurantOrderFacade.find(Integer.parseInt(msg.getText()));
            Mailer.sendOrderEmail(mailSession, order.getUserid().getEmail(), order.getId(), order.getTotal());
        } catch (JMSException ex) {
                System.out.println("Could not parse the order email message: " + ex.getMessage());
                Logger.getLogger(OrderEmailBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
