package hu.unideb.inf.rft.restaurant.web.managedbeans.owner;

import hu.unideb.inf.rft.restaurant.client.api.service.DrinkService;
import hu.unideb.inf.rft.restaurant.client.api.service.FoodService;
import hu.unideb.inf.rft.restaurant.client.api.vo.DrinkVo;
import hu.unideb.inf.rft.restaurant.client.api.vo.FoodVo;
import org.primefaces.event.RowEditEvent;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import java.util.List;

@ManagedBean(name = "ownerManagement")
public class OwnerManagementMB {

    @EJB
    private FoodService foodService;
    @EJB
    private DrinkService drinkService;

    private FoodVo food = new FoodVo();
    private DrinkVo drink = new DrinkVo();

    public FoodVo getFood() {
        return food;
    }

    public void setFood(FoodVo food) {
        this.food = food;
    }

    public DrinkVo getDrink() {
        return drink;
    }

    public void setDrink(DrinkVo drink) {
        this.drink = drink;
    }

    public void onRowEditFood(RowEditEvent event) {
        foodService.saveFood((FoodVo) event.getObject());
    }

    public void onRowEditDrink(RowEditEvent event) {
        drinkService.saveDrink((DrinkVo) event.getObject());
    }

    public void addFood(){
        foodService.addFood(food);
    }

    public void addDrink(){
        drinkService.addDrink(drink);
    }

    public void deleteFood(List<FoodVo> foods){
        for (FoodVo food : foods) {
            foodService.deleteFood(food.getId());
        }
    }

    public void deleteDrink(List<DrinkVo> drinks){
        for (DrinkVo drink : drinks) {
            drinkService.deleteDrink(drink.getId());
        }
    }

}
