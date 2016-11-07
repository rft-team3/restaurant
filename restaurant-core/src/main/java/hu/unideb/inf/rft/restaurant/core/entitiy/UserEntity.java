package hu.unideb.inf.rft.restaurant.core.entitiy;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Users")
public class UserEntity extends BaseEntity {

    @Basic
    @Column(nullable = false)
    private String name;

    @Basic
    @Column(nullable = false)
    private String email;

    @Basic
    @Column(nullable = false)
    private String password;

    @Basic
    @Column(nullable = true)
    private String address;

    @Basic
    @Column(nullable = true)
    private String phone;

    @Basic
    @Column(nullable = false)
    private boolean active;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    private List<RoleEntity> roles;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    private List<TableEntity> tables;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    private List<FoodEntity> foods;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    private List<DrinkEntity> drinks;

    public UserEntity(){}

    public UserEntity(String name, String email, String password, String address, String phone) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.address = address;
        this.phone = phone;
        this.active = true;
        this.roles = new ArrayList<>();
        this.tables = new ArrayList<>();
        this.foods = new ArrayList<>();
        this.drinks = new ArrayList<>();
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

    public List<RoleEntity> getRoles() {
        return roles;
    }

    public void setRoles(List<RoleEntity> roles) {
        this.roles = roles;
    }

    public List<TableEntity> getTables() {
        return tables;
    }

    public void setTables(List<TableEntity> tables) {
        this.tables = tables;
    }

    public List<FoodEntity> getFoods() {
        return foods;
    }

    public void setFoods(List<FoodEntity> foods) {
        this.foods = foods;
    }

    public List<DrinkEntity> getDrinks() {
        return drinks;
    }

    public void setDrinks(List<DrinkEntity> drinks) {
        this.drinks = drinks;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        UserEntity that = (UserEntity) o;

        if (active != that.active) return false;
        if (!name.equals(that.name)) return false;
        if (!email.equals(that.email)) return false;
        if (!password.equals(that.password)) return false;
        if (address != null ? !address.equals(that.address) : that.address != null) return false;
        return phone != null ? phone.equals(that.phone) : that.phone == null;

    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + name.hashCode();
        result = 31 * result + email.hashCode();
        result = 31 * result + password.hashCode();
        result = 31 * result + (address != null ? address.hashCode() : 0);
        result = 31 * result + (phone != null ? phone.hashCode() : 0);
        result = 31 * result + (active ? 1 : 0);
        return result;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", active=" + active +
                "} ";
    }
}
