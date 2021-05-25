package com.rentahome.reservation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ReservationService {
    private final ReservationRepository reservationRepository;

    @Autowired
    public ReservationService(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }

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
}
