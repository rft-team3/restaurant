package hu.unideb.inf.rft.restaurant.client.api.service;

import hu.unideb.inf.rft.restaurant.client.api.vo.TableVo;

import java.util.List;

public interface TableService {

    TableVo saveTable(TableVo tableVo);

    TableVo getTableById(Long id);

    TableVo getTableByNumber(int number);

    TableVo getTableBySeats(int seats);

    List<TableVo> getTables();
}
