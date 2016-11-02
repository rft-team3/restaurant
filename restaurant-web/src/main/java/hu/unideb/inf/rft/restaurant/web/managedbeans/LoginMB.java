package hu.unideb.inf.rft.restaurant.web.managedbeans;

import hu.unideb.inf.rft.restaurant.client.api.service.UserService;
import hu.unideb.inf.rft.restaurant.client.api.vo.UserVo;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.IOException;
import java.util.ResourceBundle;

@ManagedBean(name="loginBean")
@RequestScoped
public class LoginMB {

    @EJB
    private UserService userService;

    @ManagedProperty("#{msg}")
    private ResourceBundle bundle;

    private String username;

    private String password;

    public String login() throws ServletException, IOException {
        UserVo user = userService.getUserByName(username);
        if (user == null) {
            FacesMessage msg = new FacesMessage();
            msg.setSeverity(FacesMessage.SEVERITY_ERROR);
            msg.setSummary(bundle.getString("login.userNotFound.summary"));
            FacesContext.getCurrentInstance().addMessage(null, msg);
            return null;
        }
        if (user.isActive() == false){
            FacesMessage msg = new FacesMessage();
            msg.setSeverity(FacesMessage.SEVERITY_ERROR);
            msg.setSummary(bundle.getString("login.userInactive.summary"));
            FacesContext.getCurrentInstance().addMessage(null, msg);
            return null;
        }
        FacesContext facesContext = FacesContext.getCurrentInstance();
        ExternalContext externalContext = facesContext.getExternalContext();
        RequestDispatcher dispatcher = ((ServletRequest)externalContext.getRequest()).getRequestDispatcher("/login");
        dispatcher.forward((ServletRequest) externalContext.getRequest(), (ServletResponse) externalContext.getResponse());
        facesContext.responseComplete();
        return null;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public ResourceBundle getBundle() {
        return bundle;
    }

    public void setBundle(ResourceBundle bundle) {
        this.bundle = bundle;
    }
}
