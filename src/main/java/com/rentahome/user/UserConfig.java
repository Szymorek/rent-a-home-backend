//package com.rentahome.user;
//
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//import java.time.LocalDate;
//import java.time.Month;
//import java.util.List;
//
//@Configuration
//public class UserConfig {
//
//    @Bean
//    CommandLineRunner commandLineRunner(UserRepository repository) {
//        return args -> {
//            User jack = new User(
//                    "Jack",
//                    "Jack",
//                    "jack.pedigree@gmail.com",
//                    "$2a$10$PQfODVtdH5W06sK5fuGNQOD1Z3ZUBl9CM2VyuDHEOISimRcKnHC5q",
//                    LocalDate.of(2000, Month.APRIL, 1),
//                    LocalDate.now(),
//                    UserRole.ROLE_USER,
//                    false,
//                    true
//            );
//            User alice = new User(
//                    "Alice",
//                    "alice.piper@gmail.com",
//                    LocalDate.of(1994, Month.FEBRUARY, 25),
//                    LocalDate.now()
//            );
//
//            repository.saveAll(
//                    List.of(jack, alice)
//            );
//        };
//    }
//}
//
