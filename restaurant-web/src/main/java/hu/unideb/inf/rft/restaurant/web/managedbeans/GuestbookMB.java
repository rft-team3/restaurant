package hu.unideb.inf.rft.restaurant.web.managedbeans;

import hu.unideb.inf.rft.restaurant.client.api.service.GuestbookService;
import hu.unideb.inf.rft.restaurant.client.api.service.RoleService;
import hu.unideb.inf.rft.restaurant.client.api.service.UserService;
import hu.unideb.inf.rft.restaurant.client.api.vo.GuestbookVo;
import hu.unideb.inf.rft.restaurant.client.api.vo.UserVo;
import hu.unideb.inf.rft.restaurant.web.managedbeans.register.RegisterUserMB;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@ManagedBean(name = "guestbookBean")
@RequestScoped
public class GuestbookMB {

    private static final Logger LOGGER = LoggerFactory.getLogger(GuestbookMB.class);

    @EJB
    private UserService userService;

    @EJB
    private GuestbookService guestbookService;

    private List<GuestbookVo> messages = new ArrayList<>();

    private UserVo user;

    private String message;

    private GuestbookVo guestbook = new GuestbookVo();

    @PostConstruct
    public void init() {



        messages.addAll(guestbookService.getMessages());

    }
    public void addmsg(){
        String username = FacesContext.getCurrentInstance().getExternalContext().getUserPrincipal().getName();
        user = userService.getUserByName(username);
        guestbook.setName(user.getName());
        guestbook.setMessage(message);
        guestbookService.addMessage(guestbook);
        messages.clear();
        messages.addAll(guestbookService.getMessages());
    }



    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<GuestbookVo> getMessages() {
        return messages;
    }

    public void setMessages(List<GuestbookVo> messages) {
        this.messages = messages;
    }

}
