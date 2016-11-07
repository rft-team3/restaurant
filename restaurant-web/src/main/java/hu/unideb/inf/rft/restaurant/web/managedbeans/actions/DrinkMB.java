package hu.unideb.inf.rft.restaurant.web.managedbeans.actions;

import hu.unideb.inf.rft.restaurant.client.api.service.DrinkService;
import hu.unideb.inf.rft.restaurant.client.api.service.UserService;
import hu.unideb.inf.rft.restaurant.client.api.vo.DrinkVo;
import hu.unideb.inf.rft.restaurant.client.api.vo.UserVo;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import java.util.*;

@ManagedBean(name="drinkBean")
public class DrinkMB {

    @EJB
    private UserService userService;

    private UserVo user;

    @EJB
    private DrinkService drinkService;

    private List<DrinkVo> drinkVoList = new ArrayList<>();

    @PostConstruct
    public void init() {
        drinkVoList.addAll(drinkService.getDrinks());
    }

    public List<DrinkVo> getDrinkVoList() {return drinkVoList;}

    public void addItem(DrinkVo drinkVo){
        String username = FacesContext.getCurrentInstance().getExternalContext().getUserPrincipal().getName();
        user = userService.getUserByName(username);
        userService.addDrinkToUserByName(user.getName(),drinkVo);

        ResourceBundle bundle;
        try {
            bundle = ResourceBundle.getBundle("Messages", FacesContext.getCurrentInstance().getViewRoot().getLocale());
        } catch (MissingResourceException e) {
            bundle = ResourceBundle.getBundle("Messages", Locale.ENGLISH);
        }

        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(
                bundle.getString("drink.message.label"),
                bundle.getString("drink.messageB.label") + drinkVo.getName()));
    }

    public UserVo getUser() {
        return user;
    }

    public void setUser(UserVo user) {
        this.user = user;
    }
}
