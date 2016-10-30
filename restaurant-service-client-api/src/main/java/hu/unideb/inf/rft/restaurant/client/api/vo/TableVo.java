package hu.unideb.inf.rft.restaurant.client.api.vo;

import java.io.Serializable;

public class TableVo implements Serializable {

    private static final long serialVersionUID = 6754000328505763772L;

    private Long id;
    private int number;
    private boolean reserved;

    public TableVo(){}

    public TableVo(Long id, int number) {
        this.id = id;
        this.number = number;
        this.reserved = false;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
}
