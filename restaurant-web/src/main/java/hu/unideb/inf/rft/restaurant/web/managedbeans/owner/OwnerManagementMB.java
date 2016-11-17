package hu.unideb.inf.rft.restaurant.web.managedbeans.owner;

import hu.unideb.inf.rft.restaurant.client.api.service.DrinkService;
import hu.unideb.inf.rft.restaurant.client.api.service.FoodService;
import hu.unideb.inf.rft.restaurant.client.api.vo.DrinkVo;
import hu.unideb.inf.rft.restaurant.client.api.vo.FoodVo;
import org.primefaces.event.RowEditEvent;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import java.util.ArrayList;
import java.util.List;

@ManagedBean(name = "ownerManagement")
public class OwnerManagementMB {

    @EJB
    private FoodService foodService;
    @EJB
    private DrinkService drinkService;

    private FoodVo food = new FoodVo();
    private DrinkVo drink = new DrinkVo();

    private List<FoodVo> foods = new ArrayList<>();

    private List<FoodVo> selectedFoods = new ArrayList<>();

    private List<DrinkVo> selectedDrinks = new ArrayList<>();

    private List<DrinkVo> drinks = new ArrayList<>();

    @PostConstruct
    public void init() {
        foods.addAll(foodService.getFoods());
        drinks.addAll(drinkService.getDrinks());
    }

    public FoodVo getFood() {return food;}

    public void setFood(FoodVo food) {this.food = food;}

    public DrinkVo getDrink() {return drink;}

    public void setDrink(DrinkVo drink) {this.drink = drink;}

    public void onRowEditFood(RowEditEvent event) {
        foodService.saveFood((FoodVo) event.getObject());
    }

    public void onRowEditDrink(RowEditEvent event) {
        drinkService.saveDrink((DrinkVo) event.getObject());
    }

    public void addFood(){
        foodService.addFood(food);
        foods = foodService.getFoods();
    }

    public void addDrink(){
        drinkService.addDrink(drink);
        drinks = drinkService.getDrinks();
    }

    public void deleteFood(List<FoodVo> foods){
        for (FoodVo food : foods) {
            foodService.deleteFood(food.getId());
        }
        this.foods = foodService.getFoods();
    }

    public void deleteDrink(List<DrinkVo> drinks){
        for (DrinkVo drink : drinks) {
            drinkService.deleteDrink(drink.getId());
        }
        this.drinks = drinkService.getDrinks();
    }

    public List<FoodVo> getFoods() {
        return foods;
    }

    public void setFoods(List<FoodVo> foods) {
        this.foods = foods;
    }

    public List<DrinkVo> getDrinks() {
        return drinks;
    }

    public void setDrinks(List<DrinkVo> drinks) {
        this.drinks = drinks;
    }

    public List<FoodVo> getSelectedFoods() {
        return selectedFoods;
    }

    public void setSelectedFoods(List<FoodVo> selectedFoods) {
        this.selectedFoods = selectedFoods;
    }

    public List<DrinkVo> getSelectedDrinks() {
        return selectedDrinks;
    }

    public void setSelectedDrinks(List<DrinkVo> selectedDrinks) {
        this.selectedDrinks = selectedDrinks;
    }
}
