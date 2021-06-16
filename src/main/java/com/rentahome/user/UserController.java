package com.rentahome.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping(path = "api/v1/user")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<UserDto> getUsers() {
        return userService.getUsersDto();
    }

    @GetMapping(path = "login")
    public UserDto getLoginUser(@AuthenticationPrincipal User user) {
        return userService.getUserByEmailDto(user.getEmail());
    }

    @PostMapping
    public void registerNewUser(@RequestBody User user) {
        userService.addNewUser(user);
    }

    @DeleteMapping(path = "{userId}")
    public void deleteUser(
            @PathVariable("userId") Long userId){
        userService.deleteUser(userId);
    }

    @PutMapping()
    public UserDto updateUser(
            @AuthenticationPrincipal User user,
            @RequestParam(required = false) String username,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String surname,
            @RequestParam(required = false) String description
    ) {
        return userService.updateUser(user, username, name, surname, description);
    }
}
