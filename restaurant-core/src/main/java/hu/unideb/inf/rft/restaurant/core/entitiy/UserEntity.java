package hu.unideb.inf.rft.restaurant.core.entitiy;

import javax.persistence.*;

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
    @Column(nullable = false)
    private boolean active;

    /*@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    private List<Role> roles;*/

    public UserEntity(){}

    public UserEntity(String name, String email, String password/*, List<Role> roles*/) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.active = true;
        /*this.roles = new ArrayList<>();*/
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

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    /*public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }*/

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        UserEntity userEntity = (UserEntity) o;

        if (active != userEntity.active) return false;
        if (!name.equals(userEntity.name)) return false;
        if (!email.equals(userEntity.email)) return false;
        return password.equals(userEntity.password);

    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + name.hashCode();
        result = 31 * result + email.hashCode();
        result = 31 * result + password.hashCode();
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
