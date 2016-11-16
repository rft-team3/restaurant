package hu.unideb.inf.rft.restaurant.service.impl;

import hu.unideb.inf.rft.restaurant.client.api.service.TableService;
import hu.unideb.inf.rft.restaurant.client.api.vo.TableVo;
import hu.unideb.inf.rft.restaurant.core.entitiy.TableEntity;
import hu.unideb.inf.rft.restaurant.core.repository.TableRepository;
import hu.unideb.inf.rft.restaurant.service.mapper.TableMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ejb.interceptor.SpringBeanAutowiringInterceptor;

import javax.ejb.*;
import javax.interceptor.Interceptors;
import java.util.List;
import java.util.stream.Collectors;

@Stateless(name = "TableService", mappedName = "TableService")
@TransactionAttribute(TransactionAttributeType.REQUIRED)
@TransactionManagement(TransactionManagementType.CONTAINER)
@Local(TableService.class)
@Interceptors({SpringBeanAutowiringInterceptor.class})
public class TableServiceImpl implements TableService {

    private static final Logger LOGGER = LoggerFactory.getLogger(TableServiceImpl.class);

    @Autowired
    private TableRepository tableRepository;

    /*@Override
    public List<RoleVo> getRoles() {
        return RoleMapper.toVo(roleRepository.findAll());
    }*/

    @Override
    public TableVo saveTable(TableVo tableVo) {
        TableEntity tableEntity = tableRepository.findOne(tableVo.getId());
        if (tableEntity == null) {
            tableEntity = new TableEntity();
        }
        TableMapper.toEntity(tableVo, tableEntity);
        return TableMapper.toVo(tableRepository.save(tableEntity));
    }

    @Override
    public TableVo getTableById(Long id) {
        return TableMapper.toVo(tableRepository.findOne(id));
    }

    @Override
    public TableVo getTableByNumber(int number) {
        return TableMapper.toVo(tableRepository.findByNumber(number));
    }

    @Override
    public void setTableReservedByNumber(int number, boolean reserved) {
        tableRepository.findByNumber(number).setReserved(reserved);
    }

    @Override
    public List<TableVo> getTablesByUserId(Long userId){
        return TableMapper.toVo(tableRepository.findTablesByUserId(userId));
    }

    public List<TableVo> getTables() {
        return TableMapper.toVo(tableRepository.findAll().stream()
                .sorted( (e1,e2) -> Integer.compare(e1.getNumber(),e2.getNumber()) ).collect(Collectors.toList()));
    }

}
