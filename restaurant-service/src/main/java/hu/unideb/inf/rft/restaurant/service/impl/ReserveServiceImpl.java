package hu.unideb.inf.rft.restaurant.service.impl;

import hu.unideb.inf.rft.restaurant.client.api.service.ReserveService;
import hu.unideb.inf.rft.restaurant.client.api.vo.ReserveVo;
import hu.unideb.inf.rft.restaurant.core.entitiy.ReserveEntity;
import hu.unideb.inf.rft.restaurant.core.repository.ReserveRepository;
import hu.unideb.inf.rft.restaurant.service.mapper.ReserveMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ejb.interceptor.SpringBeanAutowiringInterceptor;

import javax.ejb.*;
import javax.interceptor.Interceptors;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Stateless(name = "ReserveService", mappedName = "ReserveService")
@TransactionAttribute(TransactionAttributeType.REQUIRED)
@TransactionManagement(TransactionManagementType.CONTAINER)
@Local(ReserveService.class)
@Interceptors({SpringBeanAutowiringInterceptor.class})
public class ReserveServiceImpl implements ReserveService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ReserveServiceImpl.class);

    @Autowired
    private ReserveRepository reserveRepository;

    @Override
    public List<ReserveVo> getReserves() {
        return ReserveMapper.toVo(reserveRepository.findAll());
    }

    @Override
    public ReserveVo saveReserve(ReserveVo reserveVo) {
        ReserveEntity reserveEntity = reserveRepository.findOne(reserveVo.getId());
        if (reserveEntity == null) {
            reserveEntity = new ReserveEntity();
        }
        ReserveMapper.toEntity(reserveVo, reserveEntity);
        return ReserveMapper.toVo(reserveRepository.save(reserveEntity));
    }

    @Override
    public void addReserve(ReserveVo reserveVo) {
        reserveRepository.save(ReserveMapper.toEntity(reserveVo));
    }

    @Override
    public void deleteReserve(Long id) {
        reserveRepository.delete(id);
    }

    @Override
    public ReserveVo getReserveById(Long id) {
        return ReserveMapper.toVo(reserveRepository.findOne(id));
    }

    @Override
    public ReserveVo getReserveByStartTime(Date startTime) {
        return ReserveMapper.toVo(reserveRepository.findByStartTime(startTime));
    }

    @Override
    public ReserveVo getReserveByEndTime(Date endTime) {
        return ReserveMapper.toVo(reserveRepository.findByEndTime(endTime));
    }
}
