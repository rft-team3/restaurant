package hu.unideb.inf.rft.restaurant.client.api.vo;

import java.io.Serializable;
import java.time.LocalDateTime;

public class GuestbookVo implements Serializable {

    private static final long serialVersionUID = 6472000328505763772L;

    private Long id;
    private String name;
    private String message;
    private LocalDateTime time;

    public GuestbookVo(){
        this.time = LocalDateTime.now();
    }

    public GuestbookVo(Long id, String name, String message ) {
        this.id = id;
        this.name = name;
        this.message = message;
        this.time = LocalDateTime.now();
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

}
