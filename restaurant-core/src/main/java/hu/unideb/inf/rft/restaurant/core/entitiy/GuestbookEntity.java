package hu.unideb.inf.rft.restaurant.core.entitiy;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(name = "Guestbook")
public class GuestbookEntity extends BaseEntity {

    @Basic
    @Column(nullable = false)
    private String name;

    @Basic
    @Column(nullable = false)
    private String message;

    @Basic
    @Column(nullable = false)
    private LocalDateTime time;

    public GuestbookEntity() {

    }

    public GuestbookEntity(String name, String message, LocalDateTime time) {
        this.name = name;
        this.message = message;
        this.time = time;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        GuestbookEntity that = (GuestbookEntity) o;

        if (!name.equals(that.name)) return false;
        if (!message.equals(that.message)) return false;
        return time.equals(that.time);

    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + name.hashCode();
        result = 31 * result + message.hashCode();
        result = 31 * result + time.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "GuestbookEntity{" +
                "name='" + name + '\'' +
                ", message='" + message + '\'' +
                ", time=" + time +
                '}';
    }
}