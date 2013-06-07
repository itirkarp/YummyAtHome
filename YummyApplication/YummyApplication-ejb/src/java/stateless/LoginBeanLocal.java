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
public interface LoginBeanLocal {

    boolean login(String email, String password);
    
}
