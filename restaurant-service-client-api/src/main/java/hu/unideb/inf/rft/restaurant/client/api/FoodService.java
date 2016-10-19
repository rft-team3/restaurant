package hu.unideb.inf.rft.restaurant.client.api;

import hu.unideb.inf.rft.restaurant.client.api.vo.FoodVo;

public interface FoodService {

    FoodVo saveFood(FoodVo foodVo);

    FoodVo getFoodById(Long id);

    FoodVo getFoodByName(String name);

    FoodVo getFoodByPrice(int price);

}
