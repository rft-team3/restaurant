package hu.unideb.inf.rft.restaurant.service.impl;

import hu.unideb.inf.rft.restaurant.client.api.service.DrinkService;
import hu.unideb.inf.rft.restaurant.client.api.vo.DrinkVo;
import hu.unideb.inf.rft.restaurant.core.entitiy.DrinkEntity;
import hu.unideb.inf.rft.restaurant.core.repository.DrinkRepository;
import hu.unideb.inf.rft.restaurant.service.mapper.DrinkMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ejb.interceptor.SpringBeanAutowiringInterceptor;

import javax.ejb.*;
import javax.interceptor.Interceptors;
import java.util.List;
import java.util.stream.Collectors;

@Stateless(name = "DrinkService", mappedName = "DrinkService")
@TransactionAttribute(TransactionAttributeType.REQUIRED)
@TransactionManagement(TransactionManagementType.CONTAINER)
@Local(DrinkService.class)
@Interceptors({SpringBeanAutowiringInterceptor.class})
public class DrinkServiceImpl implements DrinkService {

    private static final Logger LOGGER = LoggerFactory.getLogger(DrinkServiceImpl.class);

    @Autowired
    private DrinkRepository drinkRepository;

    @Override
    public List<DrinkVo> getDrinks() {
        return DrinkMapper.toVo(drinkRepository.findAll()
                .stream().sorted((e1,e2) -> e1.getName().compareTo(e2.getName())).collect(Collectors.toList()));
    }

    @Override
    public DrinkVo saveDrink(DrinkVo drinkVo) {
        DrinkEntity drinkEntity = drinkRepository.findOne(drinkVo.getId());
        if (drinkEntity == null) {
            drinkEntity = new DrinkEntity();
        }
        DrinkMapper.toEntity(drinkVo, drinkEntity);
        return DrinkMapper.toVo(drinkRepository.save(drinkEntity));
    }

    @Override
    public void addDrink(DrinkVo drinkVo) {
        drinkRepository.save(DrinkMapper.toEntity(drinkVo));
    }

    @Override
    public void deleteDrink(Long id) {
        drinkRepository.delete(id);
    }

    @Override
    public DrinkVo getDrinkById(Long id) {
        return DrinkMapper.toVo(drinkRepository.findOne(id));
    }

    @Override
    public DrinkVo getDrinkByName(String name) {
        return DrinkMapper.toVo(drinkRepository.findByName(name));
    }

    @Override
    public DrinkVo getDrinkByPrice(int price) {
        return DrinkMapper.toVo(drinkRepository.findByPrice(price));
    }
}
