package com.rentahome.reservation;

import com.google.firebase.messaging.FirebaseMessagingException;
import com.rentahome.firebase.FCMService;
import com.rentahome.offer.Offer;
import com.rentahome.offer.OfferRepository;
import com.rentahome.user.User;
import com.rentahome.user.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ReservationService {
    private final ReservationRepository reservationRepository;
    private final UserRepository userRepository;
    private final OfferRepository offerRepository;
    private final FCMService fcmService;


    public List<Reservation> getReservations(User user) {
        return reservationRepository.findAllByUser(user);
    }

    public List<ReservationDto> getReservationsDto(User user) {
        return getReservations(user)
                .stream()
                .map(this::convertToReservationDto)
                .collect(Collectors.toList());
    }

    @Transactional
    public ReservationDto addNewReservation(ReservationDto reservationDto, User user) {
        System.out.println(user.getFCMToken());
        Optional<Offer> optionalOffer = offerRepository.findById(reservationDto.getOfferDto().getId());
        Reservation reservation = new Reservation(optionalOffer.get(), user, reservationDto.getStartDate(), reservationDto.getEndDate());
        reservationRepository.save(reservation);
        User offerOwner = optionalOffer.get().getUser();
        try {
            fcmService.sendToToken(offerOwner.getFCMToken(), "Reservation", "User: " + user.getUsername() + "sent reservation.");
        } catch (FirebaseMessagingException e) {
            e.printStackTrace();
        }
        return convertToReservationDto(reservation);
    }

    public ReservationDto convertToReservationDto(Reservation reservation) {
        return new ReservationDto(reservation);
    }

}
