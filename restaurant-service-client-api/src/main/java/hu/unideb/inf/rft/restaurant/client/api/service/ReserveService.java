package hu.unideb.inf.rft.restaurant.client.api.service;

import hu.unideb.inf.rft.restaurant.client.api.vo.ReserveVo;

import java.util.Date;
import java.util.List;

public interface ReserveService {

    ReserveVo saveReserve(ReserveVo reserveVo);

    void addReserve(ReserveVo reserveVo);

    void deleteReserve(Long id);

    ReserveVo getReserveById(Long id);

    ReserveVo getReserveByStartTime(Date startTime);

    ReserveVo getReserveByEndTime(Date endTime);

    List<ReserveVo> getReserves();

    List<ReserveVo> getReservesByTableId(Long tableId);

    void addReserveToTable(ReserveVo reserveVo, int tableNumber);

    void addReserveToUser(ReserveVo reserveVo, Long userId);

}
