package com.samba.springsecurityjwt.service;

import com.samba.springsecurityjwt.domain.Rental;

public interface RentalService {
    Rental save(Rental rental);
    Rental update(Rental rental);
    void delete(Rental rental);
    Rental findById(Integer id);
    Rental findByCarId(Integer carId);
    Rental findByUserId(Integer userId);
}
