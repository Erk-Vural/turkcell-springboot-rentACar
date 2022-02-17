package com.erkvural.rentacar.dataAccess.abstracts;

import com.erkvural.rentacar.entities.concretes.Car;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarDao extends JpaRepository<Car, Integer> {
    Car getCarByCarId(int carId);
}
