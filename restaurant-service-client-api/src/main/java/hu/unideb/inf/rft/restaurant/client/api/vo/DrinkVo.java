package hu.unideb.inf.rft.restaurant.client.api.vo;

import java.io.Serializable;

public class DrinkVo implements Serializable {

    private static final long serialVersionUID = 1952000328505763772L;

    private Long id;
    private String name;
    private int price;

    public DrinkVo(){}

    public DrinkVo(Long id, String name, int price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
