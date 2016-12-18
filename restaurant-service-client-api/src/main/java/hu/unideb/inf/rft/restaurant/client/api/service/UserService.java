package hu.unideb.inf.rft.restaurant.client.api.service;

import hu.unideb.inf.rft.restaurant.client.api.vo.*;

import java.util.List;
import java.util.Map;

public interface UserService {

    UserVo saveUser(UserVo userVo);

    UserVo getUserById(Long id);

    UserVo getUserByName(String name);

    UserVo getUserByEmail(String email);

    List<UserVo> getUsers();

    Long countUsers();

    void addRoleToUserByName(String name, RoleVo roleVo);

    void removeRoleFromUserByName(String name, RoleVo roleVo);

    Map<Long,Long> getFoodsNumbers(String name);

    List<FoodVo> getDistinctFoods(String name);

    List<Long> getDistinctFoodNumbers(String name);

    void addFoodToUserByName(String name, FoodVo foodVo);

    void addMoreFoodToUserByName(String name, FoodVo foodVo, int quantity);

    void removeFoodFromUserByName(String name, FoodVo foodVo);

    void removeFoodsFromUserByName(String name, FoodVo foodVo);

    void removeAllFoodFromUserByName(String name);

    Map<Long,Long> getDrinksNumbers(String name);

    List<DrinkVo> getDistinctDrinks(String name);

    List<Long> getDistinctDrinkNumbers(String name);

    void addDrinkToUserByName(String name, DrinkVo drinkVo);

    void addMoreDrinkToUserByName(String name, DrinkVo drinkVo, int quantity);

    void removeDrinkFromUserByName(String name, DrinkVo drinkVo);

    void removeDrinksFromUserByName(String name, DrinkVo drinkVo);

    void removeAllDrinkFromUserByName(String name);

    void setUserActivityByName(String name, boolean activity);

    void registerUser(UserVo user);

    Long sumPrice(String name);
}
