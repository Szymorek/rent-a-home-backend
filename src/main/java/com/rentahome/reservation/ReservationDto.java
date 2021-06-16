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
    private String startDate;
    private String endDate;

    public ReservationDto(Reservation reservation) {
        this.id = reservation.getId();
        this.offerDto = new OfferDto(reservation.getOffer());
        this.userDto = new UserDto(reservation.getUser());
        this.startDate = reservation.getStartDate().toString();
        this.endDate = reservation.getEndDate().toString();
    }

    public ReservationDto(OfferDto offerDto, UserDto userDto, LocalDate startDate, LocalDate endDate) {
        this.offerDto = offerDto;
        this.userDto = userDto;
        this.startDate = startDate.toString();
        this.endDate = endDate.toString();
    }
}
