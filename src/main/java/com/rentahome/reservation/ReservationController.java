package com.rentahome.reservation;

import com.rentahome.offer.OfferDto;
import com.rentahome.user.User;
import com.rentahome.user.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
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
    public List<ReservationDto> getReservations(@AuthenticationPrincipal User user) {
        return reservationService.getReservationsDto(user);
    }

    @PostMapping
    public void registerNewReservation(@RequestBody ReservationDto reservationDto, @AuthenticationPrincipal User user) {
        reservationService.addNewReservation(reservationDto, user);
    }

    @PutMapping
    public void acceptReservation(@RequestBody Reservation reservation, @AuthenticationPrincipal User user) {
        reservationService.acceptReservation(reservation, user);
    }

}
