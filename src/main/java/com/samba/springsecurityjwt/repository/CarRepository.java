package com.samba.springsecurityjwt.repository;


import com.samba.springsecurityjwt.domain.Car;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CarRepository extends CrudRepository<Car, Integer> {
    Optional<Car> findByName(String name);
    Optional<Car> findByModel(String model);
}
