package com.rentahome.login;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/v1/login")
@AllArgsConstructor
public class LoginController {

    private LoginService loginService;

    @PostMapping
    public LoginResponse login(@RequestBody LoginRequest request) {
        return loginService.login(request);
    }
}