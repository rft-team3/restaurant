package hu.unideb.inf.rft.restaurant.web.managedbeans.register;

import hu.unideb.inf.rft.restaurant.client.api.service.RoleService;
import hu.unideb.inf.rft.restaurant.client.api.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

@ManagedBean(name = "registerBean")
@RequestScoped
public class RegisterMB {

    private static final Logger LOGGER = LoggerFactory.getLogger(RegisterMB.class);

    @ManagedProperty(value = "#{registerUser}")
    private RegisterUserMB user;

    @EJB
    private UserService userService;
    @EJB
    private RoleService roleService;

    public String doRegister() {

        PasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        String encPassword = bCryptPasswordEncoder.encode(user.getUser().getPassword());
        user.getUser().setPassword(encPassword);
        userService.registerUser(user.getUser());

        /*FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_INFO, "Register success", "You have successfully registered!"));*/

        LOGGER.info(user.getUser().getName() + " registered with " + user.getUser().getEmail() + " email address!");

        return "200";
    }

    public RegisterUserMB getUser() {
        return user;
    }

    public void setUser(RegisterUserMB user) {
        this.user = user;
    }
}
