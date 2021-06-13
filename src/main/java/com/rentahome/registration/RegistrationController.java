package com.rentahome.registration;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/v1/registration")
@AllArgsConstructor
public class RegistrationController {

    private RegistrationService registrationService;

    @PostMapping
    public RegistrationResponse register(@RequestBody RegistrationRequest request) {
        return registrationService.register(request);
    }
}
