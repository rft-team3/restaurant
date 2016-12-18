package hu.unideb.inf.rft.restaurant.client.api.vo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class UserVo implements Serializable {

    private static final long serialVersionUID = 5932000328505763772L;

    private Long id;
    private String name;
    private String email;
    private String password;
    private String address;
    private String phone;
    private boolean active;
    private List<FoodVo> foods;
    private List<DrinkVo> drinks;
    private List<ReserveVo> reserves;

    public UserVo() {
        this.active = true;
    }

    public UserVo(Long id, String name, String email, String password, String address, String phone) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.address = address;
        this.phone = phone;
        this.active = true;
        this.foods = new ArrayList<>();
        this.drinks = new ArrayList<>();
        this.reserves = new ArrayList<>();
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public List<FoodVo> getFoods() {return foods; }

    public void setFoods(List<FoodVo> foods) {this.foods = foods; }

    public List<DrinkVo> getDrinks() {
        return drinks;
    }

    public void setDrinks(List<DrinkVo> drinks) {
        this.drinks = drinks;
    }

    public List<ReserveVo> getReserves() {
        return reserves;
    }

    public void setReserves(List<ReserveVo> reserves) {
        this.reserves = reserves;
    }
}
