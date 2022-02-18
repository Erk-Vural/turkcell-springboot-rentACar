package com.erkvural.rentacar.api.controllers;

import com.erkvural.rentacar.business.abstracts.CarService;
import com.erkvural.rentacar.business.dtos.ListCarDto;
import com.erkvural.rentacar.business.requests.car.CreateCarRequest;
import com.erkvural.rentacar.business.requests.car.DeleteCarRequest;
import com.erkvural.rentacar.business.requests.car.UpdateCarRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cars")
public class CarController {

    private final CarService carService;

    @Autowired
    public CarController(CarService carService) {
        this.carService = carService;
    }

    @GetMapping("/getAll")
    public List<ListCarDto> getAll() {
        return carService.getAll();
    }

    @GetMapping("/get")
    public ListCarDto get(@RequestParam("id") int id) {
        return carService.getById(id);
    }

    @PostMapping("/add")
    public void add(@RequestBody CreateCarRequest createCarRequest) {
        this.carService.add(createCarRequest);
    }

    @PutMapping("/update")
    public void update(@RequestBody UpdateCarRequest updateCarRequest) {
        this.carService.update(updateCarRequest);
    }

    @DeleteMapping("/delete")
    public void delete(@RequestBody DeleteCarRequest deleteCarRequest) {
        this.carService.delete(deleteCarRequest);
    }
}
