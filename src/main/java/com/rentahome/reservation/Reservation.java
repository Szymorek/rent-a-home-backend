package com.rentahome.reservation;


import com.rentahome.offer.Offer;
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
public class Reservation {
    @Id
    @SequenceGenerator(
            name = "reservation_sequence",
            sequenceName = "reservation_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "reservation_sequence"
    )
    private Long id;

    @ManyToOne()
    @JoinColumn(name = "offer_id")
    private Offer offer;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    private String startDate;
    private String endDate;
    private Boolean accepted = false;

    public Reservation(Offer offer, User user, String startDate, String endDate) {
        this.offer = offer;
        this.user = user;
        this.startDate = startDate;
        this.endDate = endDate;
    }

}
