package com.rentahome.reservation;

import com.rentahome.offer.OfferDto;
import com.rentahome.user.UserDto;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ReservationDto {
    private Long id;
    @NotNull
    private OfferDto offerDto;
    @NotNull
    private UserDto userDto;
    private LocalDate startDate;
    private LocalDate endDate;
    private LocalDate dateOfCreate;

    public ReservationDto(Reservation reservation) {
        this.id = reservation.getId();
        this.offerDto = new OfferDto(reservation.getOffer());
        this.userDto = new UserDto(reservation.getUser());
        this.startDate = reservation.getStartDate();
        this.endDate = reservation.getEndDate();
        this.dateOfCreate = reservation.getDateOfCreate();
    }

    public ReservationDto(OfferDto offerDto, UserDto userDto, LocalDate startDate, LocalDate endDate) {
        this.offerDto = offerDto;
        this.userDto = userDto;
        this.startDate = startDate;
        this.endDate = endDate;
        this.dateOfCreate = LocalDate.now();
    }


    public ReservationDto(OfferDto offerDto, UserDto userDto, String startDate, String endDate) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM d, yyyy");
        //LocalDate localDate = LocalDate.parse(date, formatter);
        this.offerDto = offerDto;
        this.userDto = userDto;
        this.startDate = LocalDate.parse(startDate, formatter);
        this.endDate = LocalDate.parse(endDate, formatter);
        this.dateOfCreate = LocalDate.now();
    }
}
