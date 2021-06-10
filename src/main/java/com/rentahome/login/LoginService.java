package com.rentahome.login;

import com.rentahome.user.UserDto;
import com.rentahome.user.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class LoginService {
    private final UserService userService;

    public LoginResponse login(LoginRequest request) {
        Optional<UserDto> optionalUserDto = userService.loginUser(request.getEmail());
        if (optionalUserDto.isEmpty()) {
            return new LoginResponse("Wrong credentials", false);
        } else {
            return new LoginResponse("Authentication successful", true);
        }
    }
}
