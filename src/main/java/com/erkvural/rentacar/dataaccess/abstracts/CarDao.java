package com.erkvural.rentacar.dataaccess.abstracts;

import com.erkvural.rentacar.entities.concretes.Car;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CarDao extends JpaRepository<Car, Integer> {

    Car getCarById(int id);

    List<Car> getCarByBrandId(int brandId);

    List<Car> getCarByColorId(int colorId);

    List<Car> getCarByDailyPriceLessThanEqual(double dailyPrice);

}
