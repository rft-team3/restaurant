package hu.unideb.inf.rft.restaurant.core.repository;


import hu.unideb.inf.rft.restaurant.core.entitiy.ReserveEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface ReserveRepository extends JpaRepository<ReserveEntity, Long> {

    ReserveEntity findByStartTime(Date startTime);

    ReserveEntity findByEndTime(Date endTime);

    @Query("SELECT r FROM TableEntity t JOIN t.reserves r WHERE t.id = ?1")
    List<ReserveEntity> findReservesByTableId(Long tableId);
}
