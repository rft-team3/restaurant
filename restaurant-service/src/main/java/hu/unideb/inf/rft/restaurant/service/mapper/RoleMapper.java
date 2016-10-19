package hu.unideb.inf.rft.restaurant.service.mapper;

import hu.unideb.inf.rft.restaurant.client.api.vo.RoleVo;
import hu.unideb.inf.rft.restaurant.core.entitiy.RoleEntity;
import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.stream.Collectors;

public class RoleMapper {

    private static final Logger LOGGER = LoggerFactory.getLogger(RoleMapper.class);

    private static Mapper mapper = new DozerBeanMapper();

    public static RoleVo toVo(RoleEntity roleEntity) {
        if (roleEntity == null) {
            return null;
        }

        LOGGER.info(roleEntity.getName());
        LOGGER.info("Role entity mapped to RoleVo", roleEntity);
        return mapper.map(roleEntity, RoleVo.class);
    }

    public static RoleEntity toEntity(RoleVo roleVo) {
        if (roleVo == null) {
            return null;
        }

        LOGGER.info("RoleVo mapped to Role entity", roleVo);
        return mapper.map(roleVo, RoleEntity.class);
    }

    public static void toEntity(RoleVo roleVo, RoleEntity roleEntity) {
        if (roleVo == null || roleEntity == null) {
            return;
        }
        mapper.map(roleVo, roleEntity);
    }

    public static List<RoleVo> toVo(List<RoleEntity> roles) {
        return roles.stream()
                .map(RoleMapper::toVo)
                .collect(Collectors.toList());
    }

    public static List<RoleEntity> toEntity(List<RoleVo> roleVos) {
        return roleVos.stream()
                .map(RoleMapper::toEntity)
                .collect(Collectors.toList());
    }

}
