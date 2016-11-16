package hu.unideb.inf.rft.restaurant.client.api.service;

import hu.unideb.inf.rft.restaurant.client.api.vo.TableVo;

import java.util.List;

public interface TableService {

    TableVo saveTable(TableVo tableVo);

    TableVo getTableById(Long id);

    TableVo getTableByNumber(int number);

    void setTableReservedByNumber(int number, boolean reserved);

    List<TableVo> getTablesByUserId(Long userId);

    List<TableVo> getTables();
}
