package hu.unideb.inf.rft.restaurant.web.managedbeans.actions;

import hu.unideb.inf.rft.restaurant.client.api.exception.EmailSendingException;
import hu.unideb.inf.rft.restaurant.client.api.service.FoodService;
import hu.unideb.inf.rft.restaurant.client.api.service.MailService;
import hu.unideb.inf.rft.restaurant.client.api.service.UserService;
import hu.unideb.inf.rft.restaurant.client.api.vo.DrinkVo;
import hu.unideb.inf.rft.restaurant.client.api.vo.FoodVo;
import hu.unideb.inf.rft.restaurant.client.api.vo.UserVo;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import java.util.*;

@ManagedBean(name="orderBean")
public class OrderMB {

    private ResourceBundle bundle;

    @EJB
    private UserService userService;

    @EJB
    private MailService mailService;

    private UserVo user;

    @EJB
    private FoodService foodService;

    private List<FoodVo> foods = new ArrayList<>();

    private List<Long> foodQuantityList = new ArrayList<>();

    private List<Integer> foodCounter = new ArrayList<>();

    private List<DrinkVo> drinks = new ArrayList<>();

    private List<Long> drinkQuantityList = new ArrayList<>();

    private List<Integer> drinkCounter = new ArrayList<>();

    @PostConstruct
    public void init() {
        String username = FacesContext.getCurrentInstance().getExternalContext().getUserPrincipal().getName();
        user = userService.getUserByName(username);

        foods = userService.getDistinctFoods(username);
        foodQuantityList = userService.getDistinctFoodNumbers(username);

        for (int i = 0; i < foods.size(); i++)
            foodCounter.add(i);

        drinks = userService.getDistinctDrinks(username);
        drinkQuantityList = userService.getDistinctDrinkNumbers(username);

        for (int i = 0; i < drinks.size(); i++)
            drinkCounter.add(i);

        try {
            bundle = ResourceBundle.getBundle("Messages", FacesContext.getCurrentInstance().getViewRoot().getLocale());
        } catch (MissingResourceException e) {
            bundle = ResourceBundle.getBundle("Messages", Locale.ENGLISH);
        }
    }

    private void reloadUser(){
        user = userService.getUserByName(user.getName());
        foods = userService.getDistinctFoods(user.getName());
        foodQuantityList = userService.getDistinctFoodNumbers(user.getName());

        foodCounter.clear();
        for (int i = 0; i < foods.size(); i++)
            foodCounter.add(i);

        drinks = userService.getDistinctDrinks(user.getName());
        drinkQuantityList = userService.getDistinctDrinkNumbers(user.getName());

        drinkCounter.clear();
        for (int i = 0; i < drinks.size(); i++)
            drinkCounter.add(i);
    }

    public void removeFood(FoodVo foodVo){
        userService.removeFoodFromUserByName(user.getName(),foodVo);
        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_INFO,bundle.getString("order.messageFood.label"),
                        bundle.getString("order.messageBFood.label") + foodVo.getName()));
        reloadUser();
    }

    public void removeFoods(FoodVo foodVo){
        userService.removeFoodsFromUserByName(user.getName(),foodVo);
        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_INFO,bundle.getString("order.messageFood.label"),
                        bundle.getString("order.messageBFood.label") + foodVo.getName()));
        reloadUser();
    }

    public void removeAllFood(){
        userService.removeAllFoodFromUserByName(user.getName());
        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_INFO,bundle.getString("order.messageFood.label"),
                        bundle.getString("order.messageAllFood.label")));
        reloadUser();
    }

    public void removeDrink(DrinkVo drinkVo){
        userService.removeDrinkFromUserByName(user.getName(),drinkVo);
        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_INFO,bundle.getString("order.messageDrink.label"),
                        bundle.getString("order.messageBDrink.label") + drinkVo.getName()));
        reloadUser();
    }

    public void removeDrinks(DrinkVo drinkVo){
        userService.removeDrinksFromUserByName(user.getName(),drinkVo);
        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_INFO,bundle.getString("order.messageDrink.label"),
                        bundle.getString("order.messageBDrink.label") + drinkVo.getName()));
        reloadUser();
    }

    public void removeAllDrink(){
        userService.removeAllDrinkFromUserByName(user.getName());
        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_INFO,bundle.getString("order.messageDrink.label"),
                        bundle.getString("order.messageAllDrink.label")));
        reloadUser();
    }

    public void removeAll(){
        removeAllFood();
        removeAllDrink();
        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_INFO,bundle.getString("order.messageAll.label"),
                        bundle.getString("order.messageBAll.label")));
        reloadUser();
    }

    public String orderItems(){
        sendOrderedItems();
        return "105";
    }

    public Long getPrice(){return userService.sumPrice(user.getName());}

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

    public List<Long> getFoodQuantityList() {
        return foodQuantityList;
    }

    public void setFoodQuantityList(List<Long> foodQuantityList) {
        this.foodQuantityList = foodQuantityList;
    }

    public List<Integer> getFoodCounter() {
        return foodCounter;
    }

    public void setFoodCounter(List<Integer> foodCounter) {
        this.foodCounter = foodCounter;
    }

    public List<DrinkVo> getDrinks() {
        return drinks;
    }

    public void setDrinks(List<DrinkVo> drinks) {
        this.drinks = drinks;
    }

    public List<Long> getDrinkQuantityList() {
        return drinkQuantityList;
    }

    public void setDrinkQuantityList(List<Long> drinkQuantityList) {
        this.drinkQuantityList = drinkQuantityList;
    }

    public List<Integer> getDrinkCounter() {
        return drinkCounter;
    }

    public void setDrinkCounter(List<Integer> drinkCounter) {
        this.drinkCounter = drinkCounter;
    }

    public void sendOrderedItems(){
        ResourceBundle bundle;
        try {
            bundle = ResourceBundle.getBundle("Messages", FacesContext.getCurrentInstance().getViewRoot().getLocale());
        } catch (MissingResourceException e) {
            bundle = ResourceBundle.getBundle("Messages", Locale.ENGLISH);
        }
        String rendeles = bundle.getString("email.defpw.dear")+" "+user.getName()+"!<br>";
        rendeles += bundle.getString("email.order.message") + "<br>";

        if (user.getFoods().size() > 0 ){
            rendeles+="<br>"+bundle.getString("email.order.foods")+":<br>";
            for (int i=0;i<foods.size();i++)
                rendeles+= foods.get(i).getName() + " " + foodQuantityList.get(i) + " db " + foods.get(i).getPrice() + " Ft /db<br>";
        }

        if (user.getDrinks().size() > 0 ){
            rendeles+="<br>"+bundle.getString("email.order.drinks")+":<br>";
            for (int i=0;i<drinks.size();i++)
                rendeles+= drinks.get(i).getName() + " " + drinkQuantityList.get(i) + " db " + drinks.get(i).getPrice() + " Ft /db<br>";
        }

        rendeles += "<br>"+bundle.getString("email.order.total")+": "+getPrice()+" Ft<br>";
        rendeles += bundle.getString("email.defpw.endmessage");
        try {
            mailService.sendMail("noreply@restaurant.hu", user.getEmail(), bundle.getString("email.order.subject"), rendeles);

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
                    bundle.getString("reserve.sendMail.success.summary"),
                    bundle.getString("reserve.sendMail.success.detail")));
        } catch (EmailSendingException e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    bundle.getString("reserve.sendMail.error.summary"),
                    bundle.getString("reserve.sendMail.error.detail")));
        }
    }
}
