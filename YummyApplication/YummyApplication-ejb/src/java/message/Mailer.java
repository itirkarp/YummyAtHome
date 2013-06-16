package message;

import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author Prakriti
 */
public class Mailer {
    
    public static void sendOrderEmail(Session mailSession, String emailId, 
            Integer id, float total) {
        try {
            MimeMessage email = new MimeMessage(mailSession);
            email.setRecipients(MimeMessage.RecipientType.TO,
                               InternetAddress.parse(emailId, false));
            email.setSubject("Home Delivery from Yummy in my Tummy");
            email.setText("Thank you for ordering from Yummy in my Tummy!\n" 
                    + "Your order number is: " + id
                    + " and you will receive your order within 30 minutes.\n" 
                    + "The total amount due is: $" + total);
            email.setSentDate(new Date());
            Transport.send(email);
            System.out.println("Order email was sent successfully");
        } catch (MessagingException ex) {
            System.out.println("Could not send order email: " + ex.getMessage());
            Logger.getLogger(OrderEmailBean.class.getName()).log(
                    Level.SEVERE, null, ex);
        }
    }    
}
