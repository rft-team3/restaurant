package hu.unideb.inf.rft.restaurant.service.mapper;

import hu.unideb.inf.rft.restaurant.client.api.vo.UserVo;
import hu.unideb.inf.rft.restaurant.core.entitiy.UserEntity;
import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.stream.Collectors;

public class UserMapper {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserMapper.class);

    private static Mapper mapper = new DozerBeanMapper();

    public static UserVo toVo(UserEntity userEntity) {
        if (userEntity == null) {
            return null;
        }

        LOGGER.info(userEntity.getName());
        LOGGER.info(userEntity.getEmail());
        LOGGER.info(String.valueOf(userEntity.isActive()));
        LOGGER.info("User entity mapped to UserVo", userEntity);
        return mapper.map(userEntity, UserVo.class);
    }

    public static UserEntity toEntity(UserVo userVo) {
        if (userVo == null) {
            return null;
        }

        LOGGER.info("UserVo mapped to User entity", userVo);
        return mapper.map(userVo, UserEntity.class);
    }

    public static void toEntity(UserVo userVo, UserEntity userEntity) {
        if (userVo == null || userEntity == null) {
            return;
        }

        mapper.map(userVo, userEntity);
    }

    public static List<UserVo> toVo(List<UserEntity> users) {
        return users.stream()
                .map(UserMapper::toVo)
                .collect(Collectors.toList());
    }

    public static List<UserEntity> toEntity(List<UserVo> userVos) {
        return userVos.stream()
                .map(UserMapper::toEntity)
                .collect(Collectors.toList());
    }

}
