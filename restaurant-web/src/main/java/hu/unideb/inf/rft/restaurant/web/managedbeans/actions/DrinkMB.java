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

import static java.lang.Math.toIntExact;

@ManagedBean(name="drinkBean")
public class DrinkMB {

    @EJB
    private UserService userService;

    private UserVo user;

    @EJB
    private DrinkService drinkService;

    private List<DrinkVo> drinks = new ArrayList<>();

    private List<Integer> quantityList = new ArrayList<>();

    private List<Integer> counter = new ArrayList<>();

    @PostConstruct
    public void init() {
        drinks.addAll(drinkService.getDrinks());

        for (int i = 0; i < drinks.size(); i++) {
            quantityList.add(1);
            counter.add(i);
        }
    }

    private void getCurrentUser(){
        String username = FacesContext.getCurrentInstance().getExternalContext().getUserPrincipal().getName();
        user = userService.getUserByName(username);
    }

    private int getCurrentQuantity(DrinkVo drinkVo){
        return quantityList.get(drinks.indexOf(drinkVo));
    }

    public void addItem(DrinkVo drinkVo){
        getCurrentUser();
        boolean checker = getMax(true,drinkVo) > getCurrentQuantity(drinkVo);
        if (checker)
            userService.addMoreDrinkToUserByName(user.getName(),drinkVo,getCurrentQuantity(drinkVo));
        else
            userService.addMoreDrinkToUserByName(user.getName(),drinkVo,getMax(true,drinkVo));

        ResourceBundle bundle;
        try {
            bundle = ResourceBundle.getBundle("Messages", FacesContext.getCurrentInstance().getViewRoot().getLocale());
        } catch (MissingResourceException e) {
            bundle = ResourceBundle.getBundle("Messages", Locale.ENGLISH);
        }

        if (checker)
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(
                bundle.getString("drink.message.label"),
                bundle.getString("drink.messageB.label") + " " + getCurrentQuantity(drinkVo) + " " + drinkVo.getName()));
        else
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(
                    bundle.getString("drink.message.label"),
                    bundle.getString("drink.messageB.label") + " Max " + drinkVo.getName()));
    }
    
    public boolean isMax(boolean check,DrinkVo drinkVo){
        if (!check)
            return false;

        getCurrentUser();

        long tester = 0;
        boolean isIn = false;

        for (int i = 0; i < user.getDrinks().size(); i++){
            if (Objects.equals(user.getDrinks().get(i).getId(), drinkVo.getId()))
                isIn = true;
        }

        if (isIn)
            tester = userService.getDrinksNumbers(user.getName()).entrySet()
                    .stream().filter(t -> t.getKey().equals(drinkVo.getId()))
                    .map(t -> t.getValue()).findFirst().get();
        return tester < 20;
    }

    public int getMax(boolean check,DrinkVo drinkVo){
        if (!check)
            return 20;

        getCurrentUser();

        boolean isIn = false;

        for (int i = 0; i < user.getDrinks().size(); i++){
            if (Objects.equals(user.getDrinks().get(i).getId(), drinkVo.getId()))
                isIn = true;
        }

        if (isIn)
            return toIntExact(20 - (userService.getDrinksNumbers(user.getName()).entrySet()
                    .stream().filter(t -> t.getKey().equals(drinkVo.getId()))
                    .map(t -> t.getValue()).findFirst().get()));

        return 20;
    }

    public UserVo getUser() {
        return user;
    }

    public void setUser(UserVo user) {
        this.user = user;
    }

    public List<DrinkVo> getDrinks() {
        return drinks;
    }

    public void setDrinks(List<DrinkVo> drinks) {
        this.drinks = drinks;
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
