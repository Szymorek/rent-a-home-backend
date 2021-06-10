package com.rentahome.reservation;

import com.rentahome.user.User;
import com.rentahome.user.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@RestController
@RequestMapping(path = "api/v1/reservation")
public class ReservationController {
    private final ReservationService reservationService;
    private final UserRepository userRepository;

    @GetMapping
    public List<ReservationDto> getReservations() {
        return reservationService.getReservationsDto();
    }

    @PostMapping
    public void registerNewReservation(@RequestBody Reservation reservation) {
        reservationService.addNewReservation(reservation);
    }

    @PostMapping(path="{offerId}/make")
    public void makeReservation(@PathVariable Long offerId, @RequestBody LocalDate dateOfReservation, Principal principal) {
        reservationService.makeReservation(offerId, dateOfReservation, principal.getName());
    }

}
