package com.rentahome.offer;

import lombok.AllArgsConstructor;
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
    public void registerNewOffer(@RequestBody Offer offer) {
        offerService.addNewOffer(offer);
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
