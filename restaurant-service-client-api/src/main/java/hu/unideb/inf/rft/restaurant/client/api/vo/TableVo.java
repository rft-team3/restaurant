package hu.unideb.inf.rft.restaurant.client.api.vo;

import java.io.Serializable;

public class TableVo implements Serializable {

    private static final long serialVersionUID = 6754000328505763772L;

    private Long id;
    private int number;
    private int seats;

    public TableVo(){}

    public TableVo(Long id, int number, int seats) {
        this.id = id;
        this.number = number;
        this.seats = seats;
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

    public int getSeats() {
        return seats;
    }

    public void setSeats(int seats) {
        this.seats = seats;
    }
}
