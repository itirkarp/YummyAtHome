package beans;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import stateless.LoginBeanRemote;

/**
 *
 * @author Prakriti
 */
@Named(value = "loginManagedBean")
@SessionScoped
public class LoginManagedBean implements Serializable {
    
    @EJB
    private LoginBeanRemote loginBean;
    private String email;
    private String password;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    public LoginManagedBean() {
    }
    
    public String login() {
        if (loginBean.login(email, password)) {
            return "success";
        } else {
            FacesContext.getCurrentInstance().addMessage("login", 
                    new FacesMessage("The credentials were incorrect. Please try again"));
            return "failure";
        }
    }
}
