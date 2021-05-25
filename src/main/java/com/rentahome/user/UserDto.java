package com.rentahome.user;

import com.rentahome.user.User;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class UserDto {
    private String name;
    private String surname;
    private String email;
    private LocalDate dateOfBirth;
    private LocalDate dateOfJoin;

    public UserDto(User user) {
        this.name = user.getName();
        this.surname = user.getSurname();
        this.email = user.getEmail();
        this.dateOfBirth = user.getDateOfBirth();
        this.dateOfJoin = user.getDateOfCreate();
    }
}
