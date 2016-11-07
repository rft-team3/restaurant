package hu.unideb.inf.rft.restaurant.web.managedbeans.actions;

import hu.unideb.inf.rft.restaurant.client.api.service.FoodService;
import hu.unideb.inf.rft.restaurant.client.api.service.UserService;
import hu.unideb.inf.rft.restaurant.client.api.vo.FoodVo;
import hu.unideb.inf.rft.restaurant.client.api.vo.UserVo;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import java.util.ArrayList;
import java.util.List;

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
    }

    public UserVo getUser() {
        return user;
    }

    public void setUser(UserVo user) {
        this.user = user;
    }
}
