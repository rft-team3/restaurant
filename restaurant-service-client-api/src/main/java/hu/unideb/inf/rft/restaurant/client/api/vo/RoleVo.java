package hu.unideb.inf.rft.restaurant.client.api.vo;

import java.io.Serializable;

public class RoleVo implements Serializable {

    private static final long serialVersionUID = 5932000328505763772L;

    private Long id;
    private String name;

    public RoleVo(Long id, String name) {
        this.id = id;
        this.name = name;
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

}
