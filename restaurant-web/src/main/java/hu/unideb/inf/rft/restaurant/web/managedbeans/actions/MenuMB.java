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

import static java.lang.Math.toIntExact;

@ManagedBean(name="menuBean")
public class MenuMB {

    @EJB
    private UserService userService;

    private UserVo user;

    @EJB
    private FoodService foodService;

    private List<FoodVo> foods = new ArrayList<>();

    private List<Integer> quantityList = new ArrayList<>();

    private List<Integer> counter = new ArrayList<>();

    @PostConstruct
    public void init() {
        foods.addAll(foodService.getFoods());

        for (int i = 0; i < foods.size(); i++) {
            quantityList.add(1);
            counter.add(i);
        }
    }

    private void getCurrentUser(){
        String username = FacesContext.getCurrentInstance().getExternalContext().getUserPrincipal().getName();
        user = userService.getUserByName(username);
    }

    private int getCurrentQuantity(FoodVo foodVo){
        return quantityList.get(foods.indexOf(foodVo));
    }

    public void addItem(FoodVo foodVo){
        getCurrentUser();
        boolean checker = getMax(true,foodVo) > getCurrentQuantity(foodVo);
        if (checker)
            userService.addMoreFoodToUserByName(user.getName(),foodVo,getCurrentQuantity(foodVo));
        else
            userService.addMoreFoodToUserByName(user.getName(),foodVo,getMax(true,foodVo));

        ResourceBundle bundle;
        try {
            bundle = ResourceBundle.getBundle("Messages", FacesContext.getCurrentInstance().getViewRoot().getLocale());
        } catch (MissingResourceException e) {
            bundle = ResourceBundle.getBundle("Messages", Locale.ENGLISH);
        }

        if (checker)
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(
                    bundle.getString("food.message.label"),
                    bundle.getString("food.messageB.label") + " " + getCurrentQuantity(foodVo) + " " + foodVo.getName()));
        else
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(
                    bundle.getString("food.message.label"),
                    bundle.getString("food.messageB.label") + " Max " + foodVo.getName()));
    }

    public boolean isMax(boolean check,FoodVo foodVo){
        if (!check)
            return false;

        getCurrentUser();

        long tester = 0;
        boolean isIn = false;

        for (int i = 0; i < user.getFoods().size(); i++){
            if (Objects.equals(user.getFoods().get(i).getId(), foodVo.getId()))
                isIn = true;
        }

        if (isIn)
            tester = userService.getFoodsNumbers(user.getName()).entrySet()
                    .stream().filter(t -> t.getKey().equals(foodVo.getId()))
                    .map(t -> t.getValue()).findFirst().get();
        return tester < 20;
    }

    public int getMax(boolean check,FoodVo foodVo){
        if (!check)
            return 20;

        getCurrentUser();

        boolean isIn = false;

        for (int i = 0; i < user.getFoods().size(); i++){
            if (Objects.equals(user.getFoods().get(i).getId(), foodVo.getId()))
                isIn = true;
        }

        if (isIn)
            return toIntExact(20 - (userService.getFoodsNumbers(user.getName()).entrySet()
                    .stream().filter(t -> t.getKey().equals(foodVo.getId()))
                    .map(t -> t.getValue()).findFirst().get()));

        return 20;
    }

    public UserVo getUser() {
        return user;
    }

    public void setUser(UserVo user) {
        this.user = user;
    }

    public List<FoodVo> getFoods() {
        return foods;
    }

    public void setFoods(List<FoodVo> foods) {
        this.foods = foods;
    }

    public List<Integer> getQuantityList() {return quantityList;}

    public void setQuantityList(List<Integer> quantityList) {this.quantityList = quantityList;}

    public List<Integer> getCounter() {
        return counter;
    }

    public void setCounter(List<Integer> counter) {
        this.counter = counter;
    }
}
