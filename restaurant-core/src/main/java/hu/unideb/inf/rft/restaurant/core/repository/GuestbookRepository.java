package hu.unideb.inf.rft.restaurant.core.repository;


import hu.unideb.inf.rft.restaurant.core.entitiy.GuestbookEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
public interface GuestbookRepository extends JpaRepository<GuestbookEntity, Long> {

    GuestbookEntity findByName(String name);

    GuestbookEntity findByTime(Date time);

}
