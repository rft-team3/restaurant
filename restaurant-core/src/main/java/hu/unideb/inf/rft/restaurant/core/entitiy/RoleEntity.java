package hu.unideb.inf.rft.restaurant.core.entitiy;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "Roles")
public class RoleEntity extends BaseEntity {

    @Basic
    @Column(nullable = false)
    private String name;

    public RoleEntity(){}

    public RoleEntity(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        RoleEntity that = (RoleEntity) o;

        return name.equals(that.name);

    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + name.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "RoleEntity{" +
                "name='" + name + '\'' +
                '}';
    }
}