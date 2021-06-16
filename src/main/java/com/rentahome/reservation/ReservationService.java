package com.rentahome.reservation;

import com.rentahome.offer.Offer;
import com.rentahome.offer.OfferRepository;
import com.rentahome.user.User;
import com.rentahome.user.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ReservationService {
    private final ReservationRepository reservationRepository;
    private final UserRepository userRepository;
    private final OfferRepository offerRepository;


    public Optional<Reservation> getReservations(User user) {
        return reservationRepository.findReservationByUser(user);
    }

    public List<ReservationDto> getReservationsDto(User user) {
        return getReservations(user)
                .stream()
                .map(this::convertToReservationDto)
                .collect(Collectors.toList());
    }

    @Transactional
    public void addNewReservation(ReservationDto reservationDto, User user) {
        Optional<Offer> optionalOffer = offerRepository.findById(reservationDto.getOfferDto().getId());
        Reservation reservation = new Reservation(optionalOffer.get(), user, reservationDto.getStartDate(), reservationDto.getEndDate());
        Optional<Reservation> reservationOptional = reservationRepository
                .findReservationByOfferAndStartDateBetween(reservation.getOffer(), reservation.getStartDate(), reservation.getEndDate());
        if (reservationOptional.isPresent()) {
            throw new IllegalStateException("offer is already reserved for this period of time");
        }
        reservationOptional = reservationRepository
                .findReservationByOfferAndEndDateBetween(reservation.getOffer(), reservation.getStartDate(), reservation.getEndDate());
        if (reservationOptional.isPresent()) {
            throw new IllegalStateException("offer is already reserved for this period of time");
        }

        reservationRepository.save(reservation);
    }

    public ReservationDto convertToReservationDto(Reservation reservation) {
        return new ReservationDto(reservation);
    }

//    public Reservation convertToReservation(ReservationDto reservationDto) {
//        return new Reservation(reservationDto);
//    }

    public void makeReservation(Long offerId, LocalDate startDate, LocalDate endDate, String userEmail) {
        User user = userRepository.findUserByEmail(userEmail)
                .orElseThrow(() -> new IllegalStateException(
                        "user with email " + userEmail + " does not exist"
                ));
        Offer offer = offerRepository.findById(offerId)
                .orElseThrow(() -> new IllegalStateException(
                        "offer with id " + offerId + " does not exist"
                ));
        Reservation reservation = new Reservation(offer, user, startDate, endDate);
        reservationRepository.save(reservation);
    }

    public void acceptReservation(Reservation reservation, User user) {
        offerRepository.findOfferByUserAndId(user, reservation.getOffer().getId())
                .orElseThrow(() -> new IllegalStateException(
                        "user is not owner of the offer"
                ));
        reservation.setAccepted(true);
        reservationRepository.save(reservation);

    }
}
