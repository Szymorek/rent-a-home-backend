package com.rentahome.offer;

import com.rentahome.reservation.Reservation;
import com.rentahome.user.User;
import lombok.AllArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping(path = "api/v1/offer")
public class OfferController {
    private final OfferService offerService;

    @GetMapping
    public List<OfferDto> getOffers() { return offerService.getOffersDto(); }

    @GetMapping("/map/{latitude}/{longitude}/{range}")
    public List<OfferDto> getAllOffersByLocation(
            @PathVariable("latitude") Double latitude,
            @PathVariable("longitude") Double longitude,
            @PathVariable("range") Double range
    ) {
        List<OfferDto> offers = offerService.getAllOffersByLocation(latitude, longitude, range);
        return offers;
    }

    @PostMapping
    public OfferDto registerNewOffer(@RequestBody OfferDto offerDto, @AuthenticationPrincipal User user) {
        System.out.println("OfferDto: " + offerDto.getDescription() + offerDto.getTitle());
        return offerService.addNewOffer(offerDto, user);
    }

    @PutMapping(path = "{offerId}")
    public void updateOffer(
            @PathVariable("offerId") Long offerId,
            @RequestParam(required = false) String title,
            @RequestParam(required = false) String description,
            @RequestParam(required = false) Double price
    ) {
        offerService.updateOffer(offerId, title, description, price);
    }
}
