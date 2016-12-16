package hu.unideb.inf.rft.restaurant.client.api.vo;

import java.io.Serializable;
import java.util.Date;

public class ReserveVo implements Serializable {

    private static final long serialVersionUID = 4743200328505763772L;

    private Long id;
    private Date startTime;
    private Date endTime;

    public ReserveVo(){}

    public ReserveVo(Long id, Date startTime, Date endTime) {
        this.id = id;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }
}
