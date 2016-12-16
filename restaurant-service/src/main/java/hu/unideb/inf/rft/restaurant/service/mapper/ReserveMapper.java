package hu.unideb.inf.rft.restaurant.service.mapper;

import hu.unideb.inf.rft.restaurant.client.api.vo.ReserveVo;
import hu.unideb.inf.rft.restaurant.core.entitiy.ReserveEntity;
import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.stream.Collectors;

public class ReserveMapper {

    private static final Logger LOGGER = LoggerFactory.getLogger(ReserveMapper.class);

    private static Mapper mapper = new DozerBeanMapper();

    public static ReserveVo toVo(ReserveEntity reserveEntity) {
        if (reserveEntity == null) {
            return null;
        }

        LOGGER.info(String.valueOf(reserveEntity.getStartTime()));
        LOGGER.info("Reserve entity mapped to ReserveVo", reserveEntity);
        return mapper.map(reserveEntity, ReserveVo.class);
    }

    public static ReserveEntity toEntity(ReserveVo reserveVo) {
        if (reserveVo == null) {
            return null;
        }

        LOGGER.info("ReserveVo mapped to Reserve entity", reserveVo);
        return mapper.map(reserveVo, ReserveEntity.class);
    }

    public static void toEntity(ReserveVo reserveVo, ReserveEntity reserveEntity) {
        if (reserveVo == null || reserveEntity == null) {
            return;
        }
        mapper.map(reserveVo, reserveEntity);
    }

    public static List<ReserveVo> toVo(List<ReserveEntity> reserves) {
        return reserves.stream()
                .map(ReserveMapper::toVo)
                .collect(Collectors.toList());
    }

    public static List<ReserveEntity> toEntity(List<ReserveVo> reserveVos) {
        return reserveVos.stream()
                .map(ReserveMapper::toEntity)
                .collect(Collectors.toList());
    }

}
