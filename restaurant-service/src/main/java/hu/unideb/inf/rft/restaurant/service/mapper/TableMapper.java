package hu.unideb.inf.rft.restaurant.service.mapper;

import hu.unideb.inf.rft.restaurant.client.api.vo.TableVo;
import hu.unideb.inf.rft.restaurant.core.entitiy.TableEntity;
import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.stream.Collectors;

public class TableMapper {

    private static final Logger LOGGER = LoggerFactory.getLogger(TableMapper.class);

    private static Mapper mapper = new DozerBeanMapper();

    public static TableVo toVo(TableEntity tableEntity) {
        if (tableEntity == null) {
            return null;
        }

        LOGGER.info(tableEntity.getNumber() + "");
        LOGGER.info("Table entity mapped to TableVo", tableEntity);
        return mapper.map(tableEntity, TableVo.class);
    }

    public static TableEntity toEntity(TableVo tableVo) {
        if (tableVo == null) {
            return null;
        }

        LOGGER.info("TableVo mapped to Table entity", tableVo);
        return mapper.map(tableVo, TableEntity.class);
    }

    public static void toEntity(TableVo tableVo, TableEntity tableEntity) {
        if (tableVo == null || tableEntity == null) {
            return;
        }
        mapper.map(tableVo, tableEntity);
    }

    public static List<TableVo> toVo(List<TableEntity> tables) {
        return tables.stream()
                .map(TableMapper::toVo)
                .collect(Collectors.toList());
    }

    public static List<TableEntity> toEntity(List<TableVo> tableVos) {
        return tableVos.stream()
                .map(TableMapper::toEntity)
                .collect(Collectors.toList());
    }

}
