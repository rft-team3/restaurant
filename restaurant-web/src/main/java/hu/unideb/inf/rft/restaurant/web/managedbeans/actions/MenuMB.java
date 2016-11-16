package hu.unideb.inf.rft.restaurant.web.managedbeans.actions;

import hu.unideb.inf.rft.restaurant.client.api.service.FoodService;
import hu.unideb.inf.rft.restaurant.client.api.service.UserService;
import hu.unideb.inf.rft.restaurant.client.api.vo.FoodVo;
import hu.unideb.inf.rft.restaurant.client.api.vo.UserVo;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import java.util.*;

@ManagedBean(name="menuBean")
public class MenuMB {

    @EJB
    private UserService userService;

    private UserVo user;

    @EJB
    private FoodService foodService;

    private List<FoodVo> foodVoList = new ArrayList<>();

    @PostConstruct
    public void init() {
        foodVoList.addAll(foodService.getFoods());
    }

    public List<FoodVo> getFoodVoList() {return foodVoList;}

    public void addItem(FoodVo foodVo){
        String username = FacesContext.getCurrentInstance().getExternalContext().getUserPrincipal().getName();
        user = userService.getUserByName(username);
        userService.addFoodToUserByName(user.getName(),foodVo);

        ResourceBundle bundle;
        try {
            bundle = ResourceBundle.getBundle("Messages", FacesContext.getCurrentInstance().getViewRoot().getLocale());
        } catch (MissingResourceException e) {
            bundle = ResourceBundle.getBundle("Messages", Locale.ENGLISH);
        }

        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(
                bundle.getString("food.message.label"),
                bundle.getString("food.messageB.label") + foodVo.getName()));
    }

    public UserVo getUser() {
        return user;
    }

    public void setUser(UserVo user) {
        this.user = user;
    }


}
