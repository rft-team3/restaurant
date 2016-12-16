package hu.unideb.inf.rft.restaurant.core.entitiy;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "Tables")
public class TableEntity extends BaseEntity {

    @Basic
    @Column(nullable = false)
    private int number;

    @Basic
    @Column(nullable = false)
    private int seats;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    private List<ReserveEntity> reserves;

    public TableEntity(){}

    public TableEntity(int number, int seats) {
        this.number = number;
        this.seats = seats;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getSeats() {
        return seats;
    }

    public void setSeats(int seats) {
        this.seats = seats;
    }

    public List<ReserveEntity> getReserves() {
        return reserves;
    }

    public void setReserves(List<ReserveEntity> reserves) {
        this.reserves = reserves;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        TableEntity that = (TableEntity) o;

        if (number != that.number) return false;
        return seats == that.seats;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + number;
        result = 31 * result + seats;
        return result;
    }

    @Override
    public String toString() {
        return "TableEntity{" +
                "number=" + number +
                ", seats=" + seats +
                '}';
    }
}