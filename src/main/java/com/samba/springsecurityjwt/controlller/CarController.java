package com.samba.springsecurityjwt.controlller;


import com.samba.springsecurityjwt.domain.Car;
import com.samba.springsecurityjwt.service.CarService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/v1/cars")
public class CarController {

    private final CarService carService;

    @GetMapping
    public ResponseEntity<?> getAllCars() {
        return ResponseEntity.ok(carService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getCarById(@PathVariable Integer id) {
        return ResponseEntity.ok(carService.findById(id));
    }

    @PostMapping
    public ResponseEntity<?> saveCar(@RequestBody Car car) {
        return ResponseEntity.ok(carService.save(car));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCar(@PathVariable Integer id) {
        return ResponseEntity.ok(carService.delete(carService.findById(id)));
    }
}
