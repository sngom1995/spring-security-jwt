package com.samba.springsecurityjwt.controlller;


import com.samba.springsecurityjwt.config.JwtService;
import com.samba.springsecurityjwt.domain.Car;
import com.samba.springsecurityjwt.domain.Rental;
import com.samba.springsecurityjwt.domain.User;
import com.samba.springsecurityjwt.repository.UserRepository;
import com.samba.springsecurityjwt.service.CarService;
import com.samba.springsecurityjwt.service.RentalService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/rentals")
public class RentalController {
    private final RentalService rentalService;
    private final CarService carService;
    private final JwtService jwtService;
    private final UserRepository userRepository;

    @GetMapping
    public ResponseEntity<?> getAllRentals() {
        return ResponseEntity.ok(rentalService.findAll());
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> getRentalById(@PathVariable Integer id) {
        return ResponseEntity.ok(rentalService.findById(id));
    }
    @PostMapping
    public ResponseEntity<?> saveRental(@RequestBody Integer[] carIds, @RequestHeader("Authorization") String authorization) {
        String token = authorization.replace("Bearer ", "");
        String email = jwtService.extractUsername(token);
        User user = userRepository.findByEmail(email).orElseThrow();
        for (Integer carId : carIds
             ) {
            Car car = carService.findById(carId);
            Rental rental = Rental.builder().car(car).user(user).build();
            rentalService.save(rental);
        }
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteRental(@PathVariable Integer id) {
        rentalService.delete(rentalService.findById(id));
        return ResponseEntity.ok().build();
    }
}
