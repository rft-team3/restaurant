package hu.unideb.inf.rft.restaurant.core.repository;


import hu.unideb.inf.rft.restaurant.core.entitiy.ReserveEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
public interface ReserveRepository extends JpaRepository<ReserveEntity, Long> {

    ReserveEntity findByStartTime(Date startTime);

    ReserveEntity findByEndTime(Date endTime);
}
