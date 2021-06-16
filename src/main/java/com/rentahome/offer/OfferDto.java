package com.rentahome.offer;


import com.rentahome.offer.Offer;
import com.rentahome.user.UserDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OfferDto {
    private Long id;
    private UserDto userDto;
    private String title;
    private String description;
    private Double latitude;
    private Double longitude;
    private Double price;

    public OfferDto(Offer offer) {
        this.id = offer.getId();
        this.userDto = new UserDto(offer.getUser());
        this.title = offer.getTitle();
        this.description = offer.getDescription();
        this.latitude = offer.getLatitude();
        this.longitude = offer.getLongitude();
        this.price = offer.getPrice();
    }
}
