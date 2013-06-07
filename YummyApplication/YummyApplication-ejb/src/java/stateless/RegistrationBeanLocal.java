/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package stateless;

import javax.ejb.Local;

/**
 *
 * @author Prakriti
 */
@Local
public interface RegistrationBeanLocal {

    boolean register(String email, String password, String phone, String name, String address);
    
}
