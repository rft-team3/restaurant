package hu.unideb.inf.rft.restaurant.core.repository;


import hu.unideb.inf.rft.restaurant.core.entitiy.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleRepository extends JpaRepository<RoleEntity, Long> {

    RoleEntity findByName(String name);

    @Query("SELECT r FROM UserEntity u JOIN u.roles r WHERE u.id = ?1")
    List<RoleEntity> findRolesByUserId(Long userId);
}
