package hu.unideb.inf.rft.restaurant.service.mapper;

import hu.unideb.inf.rft.restaurant.client.api.vo.DrinkVo;
import hu.unideb.inf.rft.restaurant.core.entitiy.DrinkEntity;
import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.stream.Collectors;

public class DrinkMapper {

    private static final Logger LOGGER = LoggerFactory.getLogger(DrinkMapper.class);

    private static Mapper mapper = new DozerBeanMapper();

    public static DrinkVo toVo(DrinkEntity drinkEntity) {
        if (drinkEntity == null) {
            return null;
        }

        LOGGER.info(drinkEntity.getName());
        LOGGER.info("Drink entity mapped to DrinkVo", drinkEntity);
        return mapper.map(drinkEntity, DrinkVo.class);
    }

    public static DrinkEntity toEntity(DrinkVo drinkVo) {
        if (drinkVo == null) {
            return null;
        }

        LOGGER.info("DrinkVo mapped to Drink entity", drinkVo);
        return mapper.map(drinkVo, DrinkEntity.class);
    }

    public static void toEntity(DrinkVo drinkVo, DrinkEntity drinkEntity) {
        if (drinkVo == null || drinkEntity == null) {
            return;
        }
        mapper.map(drinkVo, drinkEntity);
    }

    public static List<DrinkVo> toVo(List<DrinkEntity> drinks) {
        return drinks.stream()
                .map(DrinkMapper::toVo)
                .collect(Collectors.toList());
    }

    public static List<DrinkEntity> toEntity(List<DrinkVo> drinkVos) {
        return drinkVos.stream()
                .map(DrinkMapper::toEntity)
                .collect(Collectors.toList());
    }

}
