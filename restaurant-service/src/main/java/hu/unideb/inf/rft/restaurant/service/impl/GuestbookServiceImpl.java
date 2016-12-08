package hu.unideb.inf.rft.restaurant.service.impl;

import hu.unideb.inf.rft.restaurant.client.api.service.GuestbookService;
import hu.unideb.inf.rft.restaurant.client.api.vo.GuestbookVo;
import hu.unideb.inf.rft.restaurant.core.entitiy.GuestbookEntity;
import hu.unideb.inf.rft.restaurant.core.repository.GuestbookRepository;
import hu.unideb.inf.rft.restaurant.service.mapper.GuestbookMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ejb.interceptor.SpringBeanAutowiringInterceptor;

import javax.ejb.*;
import javax.interceptor.Interceptors;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Stateless(name = "GuestbookService", mappedName = "GuestbookService")
@TransactionAttribute(TransactionAttributeType.REQUIRED)
@TransactionManagement(TransactionManagementType.CONTAINER)
@Local(GuestbookService.class)
@Interceptors({SpringBeanAutowiringInterceptor.class})
public class GuestbookServiceImpl implements GuestbookService {

    private static final Logger LOGGER = LoggerFactory.getLogger(GuestbookServiceImpl.class);

    @Autowired
    private GuestbookRepository guestbookRepository;

    @Override
    public void addMessage(GuestbookVo guestbookVo) {
        guestbookRepository.save(GuestbookMapper.toEntity(guestbookVo));
    }

    @Override
    public List<GuestbookVo> getMessages() {
        return GuestbookMapper.toVo(guestbookRepository.findAll()
                .stream().sorted((e1,e2) -> e1.getTime().compareTo(e2.getTime())).collect(Collectors.toList()));
    }

    @Override
    public GuestbookVo saveMessage(GuestbookVo guestbookVo) {
        GuestbookEntity guestbookEntity = guestbookRepository.findOne(guestbookVo.getId());
        if (guestbookEntity == null) {
            guestbookEntity = new GuestbookEntity();
        }
        GuestbookMapper.toEntity(guestbookVo, guestbookEntity);
        return GuestbookMapper.toVo(guestbookRepository.save(guestbookEntity));
    }

    @Override
    public GuestbookVo getMessageById(Long id) {
        return GuestbookMapper.toVo(guestbookRepository.findOne(id));
    }

    @Override
    public GuestbookVo getMessageByName(String name) {
        return GuestbookMapper.toVo(guestbookRepository.findByName(name));
    }

    @Override
    public GuestbookVo getMessageByTime(Date time) {
        return GuestbookMapper.toVo(guestbookRepository.findByTime(time));
    }
}
