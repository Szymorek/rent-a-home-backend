package com.rentahome.user;

import com.rentahome.registration.RegistrationResponse;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserService implements UserDetailsService {
    private final String USER_NOT_FOUND_MSG =
            "user with email %s not found";
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public List<User> getUsers() {
        return userRepository.findAll();
    }

    public List<UserDto> getUsersDto() {
        return getUsers()
                .stream()
                .map(this::convertToUserDto)
                .collect(Collectors.toList());
    }

    public User getUserByEmail(String email) {
        return userRepository.findUserByEmail(email)
                .orElseThrow(() -> new IllegalStateException(
                        "user with email " + email + "does not exist"
                ));
    }

    public UserDto getUserByEmailDto(String email) {
        return convertToUserDto(getUserByEmail(email));
    }

    public void addNewUser(User user) {
        Optional<User> userOptional = userRepository
                .findUserByEmail(user.getEmail());
        if (userOptional.isPresent()) {
            throw new IllegalStateException("email taken");
        }
        userRepository.save(user);
    }

    public void deleteUser(Long userId) {
        boolean exists = userRepository.existsById(userId);
        if (!exists) {
            throw new IllegalStateException(
                    "user with id " + userId + " does not exist"
            );
        }
        userRepository.deleteById(userId);
    }

    @Transactional
    public void updateUser(Long userId, String name, String email) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalStateException(
                        "user with id " + userId + " does not exist"
                ));
        if (name != null &&
            name.length() > 0 &&
                !user.getName().equals(name)
        ) {
            user.setName(name);
        }
        if (email != null &&
                email.length() > 0 &&
                !user.getEmail().equals(email)
        ) {
            user.setEmail(email);
        }
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userRepository.findUserByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException(String.format(USER_NOT_FOUND_MSG, email)));
    }

    public RegistrationResponse signUpUser(User user) {
        boolean userExists = userRepository.findUserByEmail(user.getEmail())
                .isPresent();

        if (userExists) {
            throw new IllegalStateException("email already taken");
        }

        String encodedPassword = bCryptPasswordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);

        userRepository.save(user);

        return new RegistrationResponse("User " + user.getUsername() + " registered successfully", true);
    }

    public Optional<UserDto> loginUser(String email) {

        Optional<User> optionalUser = userRepository.findUserByEmail(email);

        if (optionalUser.isEmpty()) {
            throw new IllegalStateException("wrong credentials!!!");
        }

        return optionalUser
                .map(this::convertToUserDto);
    }

    public UserDto convertToUserDto(User user) {
        return new UserDto(user);
    }
}
