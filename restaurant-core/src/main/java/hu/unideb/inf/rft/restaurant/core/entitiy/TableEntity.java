package hu.unideb.inf.rft.restaurant.core.entitiy;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "Tables")
public class TableEntity extends BaseEntity {

    @Basic
    @Column(nullable = false)
    private int number;

    @Basic
    @Column(nullable = false)
    private boolean reserved;

    public TableEntity(){}

    public TableEntity(int number) {
        this.number = number;
        this.reserved = false;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public boolean isReserved() {
        return reserved;
    }

    public void setReserved(boolean reserved) {
        this.reserved = reserved;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        TableEntity that = (TableEntity) o;

        if (number != that.number) return false;
        return reserved == that.reserved;

    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + number;
        result = 31 * result + (reserved ? 1 : 0);
        return result;
    }

    @Override
    public String toString() {
        return "TableEntity{" +
                "number=" + number +
                ", reserved=" + reserved +
                '}';
    }
}