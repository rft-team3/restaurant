package hu.unideb.inf.rft.restaurant.web.managedbeans;

import hu.unideb.inf.rft.restaurant.client.api.service.UserService;
import hu.unideb.inf.rft.restaurant.client.api.vo.UserVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

@ManagedBean(name = "editProfile")
@RequestScoped
public class ProfileMB {

    @EJB
    private UserService userService;

    private UserVo user;

    @PostConstruct
    private void init() {
        String username = FacesContext.getCurrentInstance().getExternalContext().getUserPrincipal().getName();
        user = userService.getUserByName(username);
    }

    public String saveUser() {
        PasswordEncoder encoder = new BCryptPasswordEncoder();
        String encPassword = encoder.encode(user.getPassword());
        user.setPassword(encPassword);

        userService.saveUser(user);

        return "200";
    }

    public UserVo getUser() {
        return user;
    }

    public void setUser(UserVo user) {
        this.user = user;
    }

}
