package com.rentahome.offer;

import com.rentahome.user.User;
import com.rentahome.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OfferService {
    private final OfferRepository offerRepository;

    @Autowired
    public OfferService(OfferRepository offerRepository) { this.offerRepository = offerRepository; }

    public List<Offer> getOffers() { return offerRepository.findAll(); }

    public List<OfferDto> getOffersDto() {
        return getOffers()
                .stream()
                .map(this::convertToOfferDto)
                .collect(Collectors.toList());
    }

    public List<OfferDto> getAllOffersByLocation(Double latitude, Double longitude, Double range) {
        return offerRepository.findOfferByLocation(latitude, longitude, range)
                .stream()
                .map(this::convertToOfferDto)
                .collect(Collectors.toList());
    }


    public OfferDto addNewOffer(OfferDto offerDto, User user) {
        Integer offerOptional = offerRepository
                .countOfferByUser(user);
        if (offerOptional > 50) {
            throw new IllegalStateException("offer for this user already exists");
        }
        Offer offer = new Offer(offerDto, user);

        offerRepository.save(offer);

        return offerDto;
    }

    @Transactional
    public void updateOffer(Long offerId, String title, String description, Double price) {
        Offer offer = offerRepository.findById(offerId)
                .orElseThrow(() -> new IllegalStateException(
                        "offer with id " + offerId + " does not exist"
                ));
        if (title != null &&
                title.length() > 0 &&
                !offer.getTitle().equals(title)
        ) {
            offer.setTitle(title);
        }
        if ( description != null &&
                description.length() > 0 &&
                !offer.getDescription().equals(description)
        ) {
            offer.setDescription(description);
        }

        if ( price != null &&
                price >= 0 &&
                !offer.getPrice().equals(price)
        ) {
            offer.setPrice(price);
        }
    }


    public OfferDto convertToOfferDto(Offer offer) {
        return new OfferDto(offer);
    }
}
