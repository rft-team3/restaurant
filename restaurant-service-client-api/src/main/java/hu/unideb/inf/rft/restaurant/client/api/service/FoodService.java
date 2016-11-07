package hu.unideb.inf.rft.restaurant.client.api.service;

import hu.unideb.inf.rft.restaurant.client.api.vo.FoodVo;

import java.util.List;

public interface FoodService {

    FoodVo saveFood(FoodVo foodVo);

    FoodVo getFoodById(Long id);

    FoodVo getFoodByName(String name);

    FoodVo getFoodByPrice(int price);

    List<FoodVo> getFoods();

}
