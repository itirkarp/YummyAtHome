package beans;

import entity.RestaurantUser;
import javax.inject.Named;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;
import javax.inject.Inject;
import stateless.RegistrationBeanRemote;

/**
 *
 * @author Prakriti
 */
@Named(value = "registrationManagedBean")
@RequestScoped
public class RegistrationManagedBean implements Serializable {
    @EJB
    private RegistrationBeanRemote registrationBean;
    @Inject
    private LoginManagedBean loginBean;

    private String email;
    private String password;
    private String confirmPassword;
    private String phone;
    private String address;
    private String name;

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

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public RegistrationManagedBean() {
    }
    
    public String register() {
        RestaurantUser user = registrationBean.register(email, password, phone, name, address);
        if (user != null) {
            loginBean.setCurrentUser(user);
            return "success";
        } else {
            FacesContext.getCurrentInstance().addMessage("registration", 
                    new FacesMessage("Registration failed. This email ID is already registered."));
            return "failure";
        }
    }
    
    public void validatePasswordPair(FacesContext context, 
            UIComponent componentToValidate, Object newValue) 
            throws ValidatorException {
        String password1 = (String) newValue;
        UIInput confirmPasswordComp = (UIInput) componentToValidate.getAttributes()
                .get("confirmPassword");
        String password2 = (String) confirmPasswordComp.getSubmittedValue();

        if (!password1.equals(password2)) {
            FacesMessage message = new FacesMessage(
                    "Password and Confirm Password must be the same.");
            throw new ValidatorException(message);
        }
    }

}
