package com.samba.springsecurityjwt.service;

import com.samba.springsecurityjwt.domain.Car;
import com.samba.springsecurityjwt.repository.CarRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CarServiceImpl implements CarService {
    private final CarRepository carRepository;
    @Override
    public Car save(Car car) {
        return carRepository.save(car);
    }

    @Override
    public List<Car> findAll() {
        return (List<Car>) carRepository.findAll();
    }

    @Override
    public Car update(Car car) {
        return carRepository.findById(car.getId()).map(car1 -> {
            car1.setName(car.getName());
            car1.setModel(car.getModel());
            car1.setPrice(car.getPrice());
            return carRepository.save(car1);
        }).orElseThrow();
    }

    @Override
    public Car delete(Car car) {
        return carRepository.findById(car.getId()).map(car1 -> {
            carRepository.delete(car1);
            return car1;
        }).orElseThrow();
    }

    @Override
    public Car findById(Integer id) {

        return carRepository.findById(id).orElseThrow();
    }

    @Override
    public Car findByName(String name) {
        return carRepository.findByName(name).orElseThrow();
    }

    @Override
    public Car findByModel(String model) {
        return carRepository.findByModel(model).orElseThrow();

    }
}
