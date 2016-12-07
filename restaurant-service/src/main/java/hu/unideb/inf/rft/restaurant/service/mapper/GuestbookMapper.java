package hu.unideb.inf.rft.restaurant.service.mapper;

import hu.unideb.inf.rft.restaurant.client.api.vo.GuestbookVo;
import hu.unideb.inf.rft.restaurant.core.entitiy.GuestbookEntity;
import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.stream.Collectors;

public class GuestbookMapper {

    private static final Logger LOGGER = LoggerFactory.getLogger(GuestbookMapper.class);

    private static Mapper mapper = new DozerBeanMapper();

    public static GuestbookVo toVo(GuestbookEntity guestbookEntity) {
        if (guestbookEntity == null) {
            return null;
        }

        LOGGER.info(guestbookEntity.getName());
        LOGGER.info("Guestbook entity mapped to GuestbookVo", guestbookEntity);
        return mapper.map(guestbookEntity, GuestbookVo.class);
    }

    public static GuestbookEntity toEntity(GuestbookVo guestbookVo) {
        if (guestbookVo == null) {
            return null;
        }

        LOGGER.info("GuestbookVo mapped to Guestbook entity", guestbookVo);
        return mapper.map(guestbookVo, GuestbookEntity.class);
    }

    public static void toEntity(GuestbookVo gustbookVo, GuestbookEntity guestbookEntity) {
        if (gustbookVo == null || guestbookEntity == null) {
            return;
        }
        mapper.map(gustbookVo, guestbookEntity);
    }

    public static List<GuestbookVo> toVo(List<GuestbookEntity> messages) {
        return messages.stream()
                .map(GuestbookMapper::toVo)
                .collect(Collectors.toList());
    }

    public static List<GuestbookEntity> toEntity(List<GuestbookVo> guestbookVos) {
        return guestbookVos.stream()
                .map(GuestbookMapper::toEntity)
                .collect(Collectors.toList());
    }

}
