package com.samba.springsecurityjwt.repository;

import com.samba.springsecurityjwt.domain.Rental;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RentalRepository extends CrudRepository<Rental, Integer> {
    Optional<Rental> findByCarId(Integer carId);
    Optional<Rental> findByUserId(Integer userId);
}
