/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package stateless;

import javax.ejb.Remote;

/**
 *
 * @author Prakriti
 */
@Remote
public interface LoginBeanRemote {

    boolean login(String email, String password);
    
}
