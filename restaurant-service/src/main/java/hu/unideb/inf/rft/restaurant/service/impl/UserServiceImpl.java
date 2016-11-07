package hu.unideb.inf.rft.restaurant.service.impl;

import hu.unideb.inf.rft.restaurant.client.api.service.UserService;
import hu.unideb.inf.rft.restaurant.client.api.vo.DrinkVo;
import hu.unideb.inf.rft.restaurant.client.api.vo.FoodVo;
import hu.unideb.inf.rft.restaurant.client.api.vo.RoleVo;
import hu.unideb.inf.rft.restaurant.client.api.vo.UserVo;
import hu.unideb.inf.rft.restaurant.core.entitiy.DrinkEntity;
import hu.unideb.inf.rft.restaurant.core.entitiy.FoodEntity;
import hu.unideb.inf.rft.restaurant.core.entitiy.RoleEntity;
import hu.unideb.inf.rft.restaurant.core.entitiy.UserEntity;
import hu.unideb.inf.rft.restaurant.core.repository.RoleRepository;
import hu.unideb.inf.rft.restaurant.core.repository.UserRepository;
import hu.unideb.inf.rft.restaurant.service.mapper.DrinkMapper;
import hu.unideb.inf.rft.restaurant.service.mapper.FoodMapper;
import hu.unideb.inf.rft.restaurant.service.mapper.RoleMapper;
import hu.unideb.inf.rft.restaurant.service.mapper.UserMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ejb.interceptor.SpringBeanAutowiringInterceptor;

import javax.ejb.*;
import javax.interceptor.Interceptors;
import java.util.ArrayList;
import java.util.List;

@Stateless(name = "UserService", mappedName = "UserService")
@TransactionAttribute(TransactionAttributeType.REQUIRED)
@TransactionManagement(TransactionManagementType.CONTAINER)
@Local(UserService.class)
@Interceptors({SpringBeanAutowiringInterceptor.class})
public class UserServiceImpl implements UserService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);

    public static final String DEFAULT_USER_ROLE = "ROLE_USER";

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;

    @Override
    public List<UserVo> getUsers() {
        return UserMapper.toVo(userRepository.findAll());
    }

    @Override
    public UserVo saveUser(UserVo userVo) {
        UserEntity userEntity = userRepository.findOne(userVo.getId());
        if (userEntity == null) {
            userEntity = new UserEntity();
        }
        UserMapper.toEntity(userVo, userEntity);
        return UserMapper.toVo(userRepository.save(userEntity));
    }

    @Override
    public UserVo getUserById(Long id) {
        return UserMapper.toVo(userRepository.findOne(id));
    }

    @Override
    public UserVo getUserByName(String name) {
        return UserMapper.toVo(userRepository.findByName(name));
    }

    @Override
    public UserVo getUserByEmail(String email) {
        return UserMapper.toVo(userRepository.findByEmail(email));
    }

    @Override
    public Long countUsers() {
        return userRepository.count();
    }

    @Override
    public void addRoleToUserByName(String name, RoleVo roleVo) {
        boolean contains = false;

        for (RoleEntity role : userRepository.findByName(name).getRoles()) {
            contains = role.getName().equals(roleVo.getName());
            if (contains) {
                break;
            }
        }

        if (!contains) {
            userRepository.findByName(name).getRoles().add(RoleMapper.toEntity(roleVo));
        }
    }

    @Override
    public void removeRoleFromUserByName(String name, RoleVo roleVo) {
        List<RoleEntity> newRoles = new ArrayList<RoleEntity>();

        for (RoleEntity role : userRepository.findByName(name).getRoles()) {
            if (!(role.getName().equals(roleVo.getName()))) {
                newRoles.add(role);
            }
        }
        userRepository.findByName(name).setRoles(newRoles);
    }

    @Override
    public void addFoodToUserByName(String name, FoodVo foodVo) {
         userRepository.findByName(name).getFoods().add(FoodMapper.toEntity(foodVo));
    }

    @Override
    public void removeFoodFromUserByName(String name, FoodVo foodVo) {
        List<FoodEntity> newFoods = new ArrayList<FoodEntity>();

        for (FoodEntity food : userRepository.findByName(name).getFoods()) {
            if (!(food.getName().equals(foodVo.getName()))) {
                newFoods.add(food);
            }
        }
        userRepository.findByName(name).setFoods(newFoods);
    }

    @Override
    public void removeAllFoodFromUserByName(String name) {
        userRepository.findByName(name).setFoods(null);
    }


    @Override
    public void addDrinkToUserByName(String name, DrinkVo drinkVo) {
        userRepository.findByName(name).getDrinks().add(DrinkMapper.toEntity(drinkVo));
    }

    @Override
    public void removeDrinkFromUserByName(String name, DrinkVo drinkVo) {
        List<DrinkEntity> newDrinks = new ArrayList<DrinkEntity>();

        for (DrinkEntity drink : userRepository.findByName(name).getDrinks()) {
            if (!(drink.getName().equals(drinkVo.getName()))) {
                newDrinks.add(drink);
            }
        }
        userRepository.findByName(name).setDrinks(newDrinks);
    }

    @Override
    public void removeAllDrinkFromUserByName(String name) {userRepository.findByName(name).setDrinks(null);}

    @Override
    public void setUserActivityByName(String name, boolean activity) {
        userRepository.findByName(name).setActive(activity);
    }

    @Override
    public void registerUser(UserVo user) {

        UserEntity userEntity = UserMapper.toEntity(user);
        if (userEntity.getRoles() == null) {
            userEntity.setRoles(new ArrayList<>(1));
        }

        addDefaultRole(userEntity);
        userRepository.save(userEntity);
    }

    private void addDefaultRole(UserEntity userEntity) {
        RoleEntity role = roleRepository.findByName(DEFAULT_USER_ROLE);
        userEntity.getRoles().add(role);
    }

    @Override
    public Long sumPrice(String name) {
        return userRepository.findByName(name).getFoods().stream().mapToLong(t -> t.getPrice()).sum() +
                userRepository.findByName(name).getDrinks().stream().mapToLong(t -> t.getPrice()).sum();
    }
}
