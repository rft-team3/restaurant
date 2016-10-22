package hu.unideb.inf.rft.restaurant.client.api.service;

import hu.unideb.inf.rft.restaurant.client.api.vo.DrinkVo;

public interface DrinkService {

    DrinkVo saveDrink(DrinkVo drinkVo);

    DrinkVo getDrinkById(Long id);

    DrinkVo getDrinkByName(String name);

    DrinkVo getDrinkByPrice(int price);

}
