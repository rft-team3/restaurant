package hu.unideb.inf.rft.restaurant.web.managedbeans.actions;

import hu.unideb.inf.rft.restaurant.client.api.service.FoodService;
import hu.unideb.inf.rft.restaurant.client.api.service.UserService;
import hu.unideb.inf.rft.restaurant.client.api.vo.DrinkVo;
import hu.unideb.inf.rft.restaurant.client.api.vo.FoodVo;
import hu.unideb.inf.rft.restaurant.client.api.vo.UserVo;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;


@ManagedBean(name="orderBean")
public class OrderMB {

    ResourceBundle bundle;

    @EJB
    private UserService userService;

    private UserVo user;

    @EJB
    private FoodService foodService;

    @PostConstruct
    public void init() {
        String username = FacesContext.getCurrentInstance().getExternalContext().getUserPrincipal().getName();
        user = userService.getUserByName(username);

        try {
            bundle = ResourceBundle.getBundle("Messages", FacesContext.getCurrentInstance().getViewRoot().getLocale());
        } catch (MissingResourceException e) {
            bundle = ResourceBundle.getBundle("Messages", Locale.ENGLISH);
        }
    }

    public void removeFood(FoodVo foodVo){
        userService.removeFoodFromUserByName(user.getName(),foodVo);
        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_INFO,bundle.getString("order.messageFood.label"),
                        bundle.getString("order.messageBFood.label") + foodVo.getName()));
    }

    public void removeAllFood(){
        userService.removeAllFoodFromUserByName(user.getName());
        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_INFO,bundle.getString("order.messageFood.label"),
                        bundle.getString("order.messageAllFood.label")));
    }

    public void removeDrink(DrinkVo drinkVo){
        userService.removeDrinkFromUserByName(user.getName(),drinkVo);
        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_INFO,bundle.getString("order.messageDrink.label"),
                        bundle.getString("order.messageBDrink.label") + drinkVo.getName()));
    }

    public void removeAllDrink(){
        userService.removeAllDrinkFromUserByName(user.getName());
        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_INFO,bundle.getString("order.messageDrink.label"),
                        bundle.getString("order.messageAllDrink.label")));
    }

    public void removeAll(){
        removeAllFood();
        removeAllDrink();
        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_INFO,bundle.getString("order.messageAll.label"),
                        bundle.getString("order.messageBAll.label")));
    }

    public String orderItems(){return "105";}

    public Long getPrice(){return userService.sumPrice(user.getName());}

    public UserVo getUser() {
        return user;
    }

    public void setUser(UserVo user) {
        this.user = user;
    }
}
