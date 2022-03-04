package com.erkvural.rentacar.dataaccess.abstracts;

import com.erkvural.rentacar.entities.concretes.Car;
import com.erkvural.rentacar.entities.concretes.Rental;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RentalDao extends JpaRepository<Rental, Integer> {

    Rental getRentalsById(int id);

    List<Rental> getRentalsByCarId(Car car);

    List<Rental> getRentalsByCustomerId(int customerId);
}
