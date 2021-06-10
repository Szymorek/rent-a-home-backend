package com.rentahome.reservation;

import com.rentahome.offer.Offer;
import com.rentahome.offer.OfferRepository;
import com.rentahome.user.User;
import com.rentahome.user.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ReservationService {
    private final ReservationRepository reservationRepository;
    private final UserRepository userRepository;
    private final OfferRepository offerRepository;


    public List<Reservation> getReservations() {
        return reservationRepository.findAll();
    }

    public List<ReservationDto> getReservationsDto() {
        return getReservations()
                .stream()
                .map(this::convertToReservationDto)
                .collect(Collectors.toList());
    }

    @Transactional
    public void addNewReservation(Reservation reservation) {
        Optional<Reservation> reservationOptional = reservationRepository
                .findReservationByOfferIdAndDateOfReservation(reservation.getOffer(), reservation.getDateOfReservation());
        if (reservationOptional.isPresent()) {
            throw new IllegalStateException("offer is already reserved for this date");
        }
        reservationRepository.save(reservation);
    }

    public ReservationDto convertToReservationDto(Reservation reservation) {
        return new ReservationDto(reservation);
    }

    public void makeReservation(Long offerId, LocalDate date, String userEmail) {
        User user = userRepository.findUserByEmail(userEmail)
                .orElseThrow(() -> new IllegalStateException(
                        "offer with id " + offerId + " does not exist"
                ));
        Offer offer = offerRepository.findById(offerId)
                .orElseThrow(() -> new IllegalStateException(
                        "offer with id " + offerId + " does not exist"
                ));
        Reservation reservation = new Reservation(offer, user, date);
        reservationRepository.save(reservation);
    }
}
