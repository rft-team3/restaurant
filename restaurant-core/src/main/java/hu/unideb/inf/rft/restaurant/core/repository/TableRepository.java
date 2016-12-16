package hu.unideb.inf.rft.restaurant.core.repository;


import hu.unideb.inf.rft.restaurant.core.entitiy.TableEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TableRepository extends JpaRepository<TableEntity, Long> {

    TableEntity findByNumber(int number);

    TableEntity findBySeats(int seats);

    @Query("SELECT r FROM UserEntity u JOIN u.tables r WHERE u.id = ?1")
    List<TableEntity> findTablesByUserId(Long userId);

}
