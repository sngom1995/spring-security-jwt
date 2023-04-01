package com.samba.springsecurityjwt.service;

import com.samba.springsecurityjwt.domain.Rental;
import com.samba.springsecurityjwt.repository.RentalRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RentalServiceImpl implements RentalService {
    private final RentalRepository rentalRepository;
    @Override
    public Rental save(Rental rental) {
        return rentalRepository.save(rental);
    }

    @Override
    public Rental update(Rental rental) {
        Rental rentalToUpdate = rentalRepository.findById(rental.getId()).orElseThrow();
        rentalToUpdate.setCar(rental.getCar());
        rentalToUpdate.setUser(rental.getUser());
        rentalToUpdate.setStartDate(rental.getStartDate());
        rentalToUpdate.setEndDate(rental.getEndDate());
        return rentalRepository.save(rentalToUpdate);
    }

    @Override
    public void delete(Rental rental) {
         rentalRepository.delete(rental);
    }

    @Override
    public Rental findById(Integer id) {
        return rentalRepository.findById(id).orElseThrow();
    }

    @Override
    public Rental findByCarId(Integer carId) {
        return rentalRepository.findByCarId(carId).orElseThrow();
    }

    @Override
    public Rental findByUserId(Integer userId) {
        return rentalRepository.findByUserId(userId).orElseThrow();
    }

    @Override
    public List<Rental> findAll() {
        return (List<Rental>) rentalRepository.findAll();
    }
}
