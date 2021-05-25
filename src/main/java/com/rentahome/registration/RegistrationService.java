package com.rentahome.registration;

import com.rentahome.user.User;
import com.rentahome.user.UserDto;
import com.rentahome.user.UserRole;
import com.rentahome.user.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Month;
import java.util.Optional;

@Service
@AllArgsConstructor
public class RegistrationService {

    private final UserService userService;
    private final EmailValidator emailValidator;

    public String register(RegistrationRequest request) {
        boolean isValidEmail = emailValidator.test(request.getEmail());
        if (!isValidEmail) {
            throw new IllegalStateException("email not valid");
        }
        return userService.signUpUser(
                new User(
                        request.getUsername(),
                       request.getName(),
                       request.getSurname(),
                       request.getEmail(),
                       request.getPassword(),
                        LocalDate.of(2000, Month.APRIL, 1),
                        LocalDate.now(),
                        UserRole.ROLE_USER,
                        false,
                        true
                )
        );
    }

    public String login(LoginRequest request) {
        Optional<UserDto> optionalUserDto = userService.loginUser(request.getEmail(), request.getPassword());
        if (optionalUserDto.isEmpty()) {
            return "wrong credentials";
        } else {
            return "success";
        }
    }
}