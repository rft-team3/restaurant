package hu.unideb.inf.rft.restaurant.service.mapper;

import hu.unideb.inf.rft.restaurant.client.api.vo.FoodVo;
import hu.unideb.inf.rft.restaurant.core.entitiy.FoodEntity;
import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.stream.Collectors;

public class FoodMapper {

    private static final Logger LOGGER = LoggerFactory.getLogger(FoodMapper.class);

    private static Mapper mapper = new DozerBeanMapper();

    public static FoodVo toVo(FoodEntity foodEntity) {
        if (foodEntity == null) {
            return null;
        }

        LOGGER.info(foodEntity.getName());
        LOGGER.info("Food entity mapped to FoodVo", foodEntity);
        return mapper.map(foodEntity, FoodVo.class);
    }

    public static FoodEntity toEntity(FoodVo foodVo) {
        if (foodVo == null) {
            return null;
        }

        LOGGER.info("FoodVo mapped to Food entity", foodVo);
        return mapper.map(foodVo, FoodEntity.class);
    }

    public static void toEntity(FoodVo foodVo, FoodEntity foodEntity) {
        if (foodVo == null || foodEntity == null) {
            return;
        }
        mapper.map(foodVo, foodEntity);
    }

    public static List<FoodVo> toVo(List<FoodEntity> foods) {
        return foods.stream()
                .map(FoodMapper::toVo)
                .collect(Collectors.toList());
    }

    public static List<FoodEntity> toEntity(List<FoodVo> foodVos) {
        return foodVos.stream()
                .map(FoodMapper::toEntity)
                .collect(Collectors.toList());
    }

}
