package hu.unideb.inf.rft.restaurant.service.impl;

import hu.unideb.inf.rft.restaurant.client.api.service.UserService;
import hu.unideb.inf.rft.restaurant.client.api.vo.*;
import hu.unideb.inf.rft.restaurant.core.entitiy.*;
import hu.unideb.inf.rft.restaurant.core.repository.DrinkRepository;
import hu.unideb.inf.rft.restaurant.core.repository.FoodRepository;
import hu.unideb.inf.rft.restaurant.core.repository.RoleRepository;
import hu.unideb.inf.rft.restaurant.core.repository.UserRepository;
import hu.unideb.inf.rft.restaurant.service.mapper.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ejb.interceptor.SpringBeanAutowiringInterceptor;

import javax.ejb.*;
import javax.interceptor.Interceptors;
import java.util.*;
import java.util.stream.Collectors;

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
    @Autowired
    private FoodRepository foodRepository;
    @Autowired
    private DrinkRepository drinkRepository;

    @Override
    public List<UserVo> getUsers() {
        return UserMapper.toVo(userRepository.findAll()
                .stream().sorted((e1,e2) -> e1.getId().compareTo(e2.getId())).collect(Collectors.toList()));
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
    public void addTableToUserByName(String name, TableVo tableVo) {
        boolean contains = false;

        for (TableEntity table : userRepository.findByName(name).getTables()) {
            contains = table.getNumber() == tableVo.getNumber();
            if (contains) {
                break;
            }
        }

        if (!contains) {
            userRepository.findByName(name).getTables().add(TableMapper.toEntity(tableVo));
        }
    }

    @Override
    public void removeTableFromUserByName(String name, TableVo tableVo) {
        List<TableEntity> newTables = new ArrayList<TableEntity>();

        for (TableEntity table : userRepository.findByName(name).getTables()) {
            if (!(table.getNumber() == tableVo.getNumber())) {
                newTables.add(table);
            }
        }
        userRepository.findByName(name).setTables(newTables);
    }

    @Override
    public Map<Long,Long> getFoodsNumbers(String name) {
        return FoodMapper.toVo(userRepository.findByName(name).getFoods())
                .stream().sorted((e1,e2) -> e1.getName().compareTo(e2.getName())).map(t -> t.getId())
                .collect(Collectors.groupingBy(t -> t,Collectors.counting()));
    }

    @Override
    public List<FoodVo> getDistinctFoods(String name){
        return getFoodsNumbers(name).entrySet()
                .stream().sorted((e1,e2) -> foodRepository
                        .findOne(e1.getKey()).getName().compareTo(foodRepository
                                .findOne(e2.getKey()).getName()))
                .map(t -> t.getKey())
                .map(t -> FoodMapper.toVo(foodRepository.findOne(t))).collect(Collectors.toList());
    }

    @Override
    public List<Long> getDistinctFoodNumbers(String name){
        return getFoodsNumbers(name).entrySet()
                .stream().sorted((e1,e2) -> foodRepository
                        .findOne(e1.getKey()).getName().compareTo(foodRepository
                                .findOne(e2.getKey()).getName()))
                .map(t -> t.getValue())
                .collect(Collectors.toList());
    }

    @Override
    public void addFoodToUserByName(String name, FoodVo foodVo) {
         userRepository.findByName(name).getFoods().add(FoodMapper.toEntity(foodVo));
    }

    @Override
    public void addMoreFoodToUserByName(String name, FoodVo foodVo,int quantity) {
        UserEntity user = userRepository.findByName(name);

        for (int i = 0; i < quantity; i++)
            user.getFoods().add(FoodMapper.toEntity(foodVo));
    }

    @Override
    public void removeFoodFromUserByName(String name, FoodVo foodVo) {
        List<FoodEntity> newFoods = new ArrayList<FoodEntity>();

        for (FoodEntity food : userRepository.findByName(name).getFoods()) {
            if (!(food.getId().equals(foodVo.getId()))) {
                newFoods.add(food);
            }
            if ((food.getId().equals(foodVo.getId())))
                foodVo.setId(null);
        }
        userRepository.findByName(name).setFoods(newFoods);
    }

    @Override
    public void removeFoodsFromUserByName(String name, FoodVo foodVo){
        List<FoodEntity> newFoods = new ArrayList<FoodEntity>();

        for (FoodEntity food : userRepository.findByName(name).getFoods()) {
            if (!(food.getId().equals(foodVo.getId())))
                newFoods.add(food);
        }

        userRepository.findByName(name).setFoods(newFoods);
    }

    @Override
    public void removeAllFoodFromUserByName(String name) {
        userRepository.findByName(name).setFoods(null);
    }

    @Override
    public Map<Long,Long> getDrinksNumbers(String name){
        return DrinkMapper.toVo(userRepository.findByName(name).getDrinks())
                .stream().sorted((e1,e2) -> e1.getName().compareTo(e2.getName())).map(t -> t.getId())
                .collect(Collectors.groupingBy(t -> t,Collectors.counting()));
    }

    @Override
    public List<DrinkVo> getDistinctDrinks(String name){
        return getDrinksNumbers(name).entrySet()
                .stream().sorted((e1,e2) -> drinkRepository
                        .findOne(e1.getKey()).getName().compareTo(drinkRepository
                                .findOne(e2.getKey()).getName()))
                .map(t -> t.getKey())
                .map(t -> DrinkMapper.toVo(drinkRepository.findOne(t))).collect(Collectors.toList());
    }

    @Override
    public List<Long> getDistinctDrinkNumbers(String name){
        return getDrinksNumbers(name).entrySet()
                .stream().sorted((e1,e2) -> drinkRepository
                        .findOne(e1.getKey()).getName().compareTo(drinkRepository
                                .findOne(e2.getKey()).getName()))
                .map(t -> t.getValue())
                .collect(Collectors.toList());    }

    @Override
    public void addDrinkToUserByName(String name, DrinkVo drinkVo) {
        userRepository.findByName(name).getDrinks().add(DrinkMapper.toEntity(drinkVo));
    }

    @Override
    public void addMoreDrinkToUserByName(String name, DrinkVo drinkVo,int quantity) {
        UserEntity user = userRepository.findByName(name);

        for (int i = 0; i < quantity; i++)
            user.getDrinks().add(DrinkMapper.toEntity(drinkVo));
    }

    @Override
    public void removeDrinkFromUserByName(String name, DrinkVo drinkVo) {
        List<DrinkEntity> newDrinks = new ArrayList<DrinkEntity>();

        for (DrinkEntity drink : userRepository.findByName(name).getDrinks()) {
            if (!(drink.getId().equals(drinkVo.getId()))) {
                newDrinks.add(drink);
            }
            if ((drink.getId().equals(drinkVo.getId())))
                drinkVo.setId(null);
        }
        userRepository.findByName(name).setDrinks(newDrinks);
    }

    @Override
    public void removeDrinksFromUserByName(String name, DrinkVo drinkVo) {
        List<DrinkEntity> newDrinks = new ArrayList<DrinkEntity>();

        for (DrinkEntity drink : userRepository.findByName(name).getDrinks()) {
            if (!(drink.getId().equals(drinkVo.getId())))
                newDrinks.add(drink);
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
