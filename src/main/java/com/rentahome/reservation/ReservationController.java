package com.rentahome.reservation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/reservation")
public class ReservationController {
    private final ReservationService reservationService;

    @Autowired
    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @GetMapping
    public List<ReservationDto> getReservations() {
        return reservationService.getReservationsDto();
    }

    @PostMapping
    public void registerNewReservation(@RequestBody Reservation reservation) {
        reservationService.addNewReservation(reservation);
    }

}
