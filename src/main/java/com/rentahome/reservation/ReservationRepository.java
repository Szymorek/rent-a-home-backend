package com.rentahome.reservation;

import com.rentahome.offer.Offer;
import com.rentahome.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {

    @Query("SELECT r FROM Reservation r WHERE r.offer = ?1")
    Optional<Reservation> findReservationByOffer(Offer offer);

    Optional<Reservation> findReservationByOfferAndStartDateBetween(Offer offer, LocalDate startDate, LocalDate endDate);

    Optional<Reservation> findReservationByOfferAndEndDateBetween(Offer offer, LocalDate startDate, LocalDate endDate);

    Optional<Reservation> findReservationByUser(User user);
}
