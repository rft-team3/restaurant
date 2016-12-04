package hu.unideb.inf.rft.restaurant.web.managedbeans;

import hu.unideb.inf.rft.restaurant.client.api.service.FoodService;
import hu.unideb.inf.rft.restaurant.client.api.service.UserService;
import hu.unideb.inf.rft.restaurant.client.api.vo.FoodVo;
import hu.unideb.inf.rft.restaurant.client.api.vo.UserVo;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import java.util.*;

import static java.lang.Math.toIntExact;

@ManagedBean(name="promoBean")
public class PromoMB {

    @EJB
    private FoodService foodService;

    private List<FoodVo> promoFoods = new ArrayList<>();

    @PostConstruct
    public void init() {
        promoFoods.add(foodService.getFoodById((long) 4));
        promoFoods.add(foodService.getFoodById((long) 5));
        promoFoods.add(foodService.getFoodById((long) 6));
    }

    public List<FoodVo> getPromoFoods() {
        return promoFoods;
    }

    public void setPromoFoods(List<FoodVo> promoFoods) {
        this.promoFoods = promoFoods;
    }
}
