package hu.unideb.inf.rft.restaurant.core.entitiy;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "Foods")
public class FoodEntity extends BaseEntity {

    @Basic
    @Column(nullable = false)
    private String name;

    @Basic
    @Column(nullable = false)
    private int price;

    public FoodEntity(){}

    public FoodEntity(String name, int price) {
        this.name = name;
        this.price = price;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        FoodEntity that = (FoodEntity) o;

        if (price != that.price) return false;
        return name.equals(that.name);

    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + name.hashCode();
        result = 31 * result + price;
        return result;
    }

    @Override
    public String toString() {
        return "FoodEntity{" +
                "name='" + name + '\'' +
                ", price=" + price +
                '}';
    }
}