package com.rentahome.offer;

import com.rentahome.user.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Offer {
    @Id
    @SequenceGenerator(
            name = "offer_sequence",
            sequenceName = "offer_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "offer_sequence"
    )
    private Long id;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    private String title;
    private String description;
    private Double latitude;
    private Double longitude;
    private Double price;
    private LocalDate dateOfCreate;

    public Offer(User user, String title, String description, Double latitude, Double longitude, Double price, LocalDate dateOfCreate) {
        this.user = user;
        this.title = title;
        this.description = description;
        this.latitude = latitude;
        this.longitude = longitude;
        this.price = price;
        this.dateOfCreate = dateOfCreate;
    }
    public Offer(User user, String title, String description, Double latitude, Double longitude, Double price) {
        this.user = user;
        this.title = title;
        this.description = description;
        this.latitude = latitude;
        this.longitude = longitude;
        this.price = price;
        this.dateOfCreate = LocalDate.now();
    }
}
