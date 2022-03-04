package com.erkvural.rentacar.dataaccess.abstracts;

import com.erkvural.rentacar.entities.concretes.Car;
import com.erkvural.rentacar.entities.concretes.CarMaintenance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarMaintenanceDao extends JpaRepository<CarMaintenance, Integer> {
    List<CarMaintenance> getCarMaintenanceByCarId(Car carId);

    List<CarMaintenance> deleteCarMaintenanceByCarId(Car carId);

    CarMaintenance getCarMaintenanceById(int id);
}
