package hu.unideb.inf.rft.restaurant.client.api.service;

import hu.unideb.inf.rft.restaurant.client.api.vo.DrinkVo;
import hu.unideb.inf.rft.restaurant.client.api.vo.FoodVo;
import hu.unideb.inf.rft.restaurant.client.api.vo.RoleVo;
import hu.unideb.inf.rft.restaurant.client.api.vo.UserVo;
import java.util.List;

public interface UserService {

    UserVo saveUser(UserVo userVo);

    UserVo getUserById(Long id);

    UserVo getUserByName(String name);

    UserVo getUserByEmail(String email);

    List<UserVo> getUsers();

    Long countUsers();

    void addRoleToUserByName(String name, RoleVo roleVo);

    void removeRoleFromUserByName(String name, RoleVo roleVo);

    void addFoodToUserByName(String name, FoodVo foodVo);

    void removeFoodFromUserByName(String name, FoodVo foodVo);

    void removeAllFoodFromUserByName(String name);

    void addDrinkToUserByName(String name, DrinkVo drinkVo);

    void removeDrinkFromUserByName(String name, DrinkVo drinkVo);

    void removeAllDrinkFromUserByName(String name);

    void setUserActivityByName(String name, boolean activity);

    void registerUser(UserVo user);

    Long sumPrice(String name);
}
