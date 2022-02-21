package com.erkvural.rentacar.api.controllers;

import com.erkvural.rentacar.business.abstracts.CarService;
import com.erkvural.rentacar.business.dtos.CarDto;
import com.erkvural.rentacar.business.requests.car.CreateCarRequest;
import com.erkvural.rentacar.business.requests.car.DeleteCarRequest;
import com.erkvural.rentacar.business.requests.car.UpdateCarRequest;
import com.erkvural.rentacar.core.utilities.results.DataResult;
import com.erkvural.rentacar.core.utilities.results.ErrorResult;
import com.erkvural.rentacar.core.utilities.results.Result;
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
    public DataResult<List<CarDto>> getAll() {
        return carService.getAll();
    }

    @GetMapping("/get")
    public DataResult<CarDto> get(@RequestParam("id") int id) {
        return carService.getById(id);
    }

    @PostMapping("/add")
    public Result add(@RequestBody CreateCarRequest createCarRequest) {
        try {
            return this.carService.add(createCarRequest);
        } catch (Exception e) {
            return new ErrorResult(e.getMessage());
        }
    }

    @PutMapping("/update")
    public Result update(@RequestBody UpdateCarRequest updateCarRequest) {
        try {
            return this.carService.update(updateCarRequest);
        } catch (Exception e) {
            return new ErrorResult(e.getMessage());
        }
    }

    @DeleteMapping("/delete")
    public Result delete(@RequestBody DeleteCarRequest deleteCarRequest) {
        try {
            return this.carService.delete(deleteCarRequest);
        } catch (Exception e) {
            return new ErrorResult(e.getMessage());
        }
    }
}
