package com.rentahome.offer;

import com.rentahome.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
public interface OfferRepository extends JpaRepository<Offer, Long> {

    @Query("SELECT o FROM Offer o WHERE o.user = ?1")
    Optional<Offer> findOfferByUser(User user);

    @Query(
            "SELECT o FROM Offer o WHERE " +
            "(o.latitude < ?1 + ?3 AND o.latitude > ?1 - ?3) AND" +
            "(o.longitude < ?2 + ?3 AND o.longitude > ?2 - ?3)"
    )
    List<Offer> findOfferByLocation(Double latitude, Double longitude, Double range);
}
