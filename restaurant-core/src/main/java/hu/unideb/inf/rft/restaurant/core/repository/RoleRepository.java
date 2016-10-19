package hu.unideb.inf.rft.restaurant.core.repository;


import hu.unideb.inf.rft.restaurant.core.entitiy.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<RoleEntity, Long> {

    RoleEntity findByName(String name);

    RoleEntity findById(Long Id);
}
