package hu.unideb.inf.rft.restaurant.web.managedbeans;

import hu.unideb.inf.rft.restaurant.client.api.exception.EmailSendingException;
import hu.unideb.inf.rft.restaurant.client.api.service.MailService;
import hu.unideb.inf.rft.restaurant.client.api.service.UserService;
import hu.unideb.inf.rft.restaurant.client.api.vo.UserVo;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;
import java.util.UUID;

@ManagedBean(name = "forgotPassword")
@RequestScoped
public class ForgotPasswordMB {

    @EJB
    private UserService userService;

    private String email;

    @EJB
    private MailService mailService;

    public void sendNewPassword() {
        UserVo user = userService.getUserByEmail(email);

        ResourceBundle bundle;
        try {
            bundle = ResourceBundle.getBundle("Messages", FacesContext.getCurrentInstance().getViewRoot().getLocale());
        } catch (MissingResourceException e) {
            bundle = ResourceBundle.getBundle("Messages", Locale.ENGLISH);
        }

        if (user == null) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    bundle.getString("forgotPassword.email.notFound.summary"),
                    bundle.getString("forgotPassword.email.notFound.detail")));
        }

        String newPassword = UUID.randomUUID().toString();
        newPassword = newPassword.substring(0, 8);
        PasswordEncoder encoder = new BCryptPasswordEncoder();
        String encPassword = encoder.encode(newPassword);
        user.setPassword(encPassword);
        userService.saveUser(user);

        String message = bundle.getString("email.defpw.dear")+" "+user.getName()+"!<br>";
        message+=bundle.getString("email.forgotpw.message")+" "+newPassword+bundle.getString("email.defpw.endmessage");
        try {
            mailService.sendMail("noreply@restaurant.hu", user.getEmail(), bundle.getString("email.forgotpw.subject"), message);

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
                    bundle.getString("forgotPassword.sendMail.success.summary"),
                    bundle.getString("forgotPassword.sendMail.success.detail")));
        } catch (EmailSendingException e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    bundle.getString("forgotPassword.sendMail.error.summary"),
                    bundle.getString("forgotPassword.sendMail.error.detail")));
        }
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
