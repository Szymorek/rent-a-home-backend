package com.rentahome.reservation;

import com.rentahome.offer.Offer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {

    @Query("SELECT r FROM Reservation r WHERE r.offer = ?1")
    Optional<Reservation> findReservationByOffer(Offer offer);

    @Query("SELECT r FROM Reservation r WHERE r.offer = ?1 AND r.dateOfReservation = ?2")
    Optional<Reservation> findReservationByOfferIdAndDateOfReservation(Offer offer, LocalDate dateOfReservation);
}
