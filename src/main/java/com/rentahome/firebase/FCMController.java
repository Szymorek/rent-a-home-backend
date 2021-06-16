package com.rentahome.firebase;

import com.google.firebase.messaging.FirebaseMessagingException;
import com.rentahome.offer.OfferDto;
import com.rentahome.user.User;
import lombok.AllArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping(path = "api/v1/notifications")
public class FCMController {
    private final FCMService fcmService;

    @PostMapping
    public void sendNotificationToEmail(@RequestParam("email") String email,
                                 @RequestParam("title") String title,
                                 @RequestParam("body") String body) throws FirebaseMessagingException {
        fcmService.sendToUser(email, title, body);
    }

    @PostMapping(path="all")
    public void sendNotificationToAll(@RequestParam("title") String title,
                                      @RequestParam("body") String body) throws FirebaseMessagingException {
        fcmService.sendToAll(title, body);
    }

}
