package com.rentahome.reservation;

import com.rentahome.offer.OfferDto;
import com.rentahome.user.UserDto;

import java.time.LocalDate;

public class ReservationDto {
    private OfferDto offerDto;
    private UserDto userDto;
    private LocalDate dateOfReservation;
    private LocalDate dateOfCreate;

    public ReservationDto(Reservation reservation) {
        this.offerDto = new OfferDto(reservation.getOffer());
        this.userDto = new UserDto(reservation.getUser());
        this.dateOfReservation = reservation.getDateOfReservation();
        this.dateOfCreate = reservation.getDateOfCreate();
    }
}
