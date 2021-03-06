package hu.unideb.inf.rft.restaurant.client.api.service;

import hu.unideb.inf.rft.restaurant.client.api.vo.DrinkVo;

import java.util.List;

public interface DrinkService {

    DrinkVo saveDrink(DrinkVo drinkVo);

    void addDrink(DrinkVo drinkVo);

    void deleteDrink(Long id);

    DrinkVo getDrinkById(Long id);

    DrinkVo getDrinkByName(String name);

    DrinkVo getDrinkByPrice(int price);

    List<DrinkVo> getDrinks();

}
