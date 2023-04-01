package com.samba.springsecurityjwt.service;

import com.samba.springsecurityjwt.domain.Car;

import java.util.List;

public interface CarService {
    Car save(Car car);
    List<Car> findAll();
    Car update(Car car);
    Car delete(Car car);
    Car findById(Integer id);
    Car findByName(String name);
    Car findByModel(String model);
}
