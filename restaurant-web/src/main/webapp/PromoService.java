package org.primefaces.showcase.service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import org.primefaces.showcase.domain.Car;

@ManagedBean(name = "promoService")
@ApplicationScoped
public class PromoService {

 //   private final static String[] colors;

 //   private final static String[] brands;


    public List<Car> createCars(int size) {
        List<Car> list = new ArrayList<Car>();
  /*      for(int i = 0 ; i < size ; i++) {
            list.add(new Car(getRandomId(), getRandomBrand(), getRandomYear(), getRandomColor(), getRandomPrice(), getRandomSoldState()));
        }
*/
        return list;
    }

}