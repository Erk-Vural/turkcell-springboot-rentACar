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
import org.springframework.data.domain.Sort;
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

    @GetMapping("/getAllSorted")
    public DataResult<List<CarDto>> getAllSorted(@RequestParam("direction") Sort.Direction direction) {
        return this.carService.getAllSorted(direction);
    }

    @GetMapping("/getAllPaged")
    public DataResult<List<CarDto>> getAllPaged(@RequestParam("pageNo") int pageNo,
                                                @RequestParam("pageSize") int pageSize) {
        return this.carService.getAllPaged(pageNo, pageSize);
    }

    @GetMapping("/getAllByDailyPriceLessThanEqual")
    public DataResult<List<CarDto>> getAllByDailyPriceLessThanEqual(@RequestParam("dailyPrice") double dailyPrice) {
        return this.carService.getAllByDailyPriceLessThanEqual(dailyPrice);
    }

    @PostMapping("/add")
    public Result add(@RequestBody CreateCarRequest createCarRequest) {
        return this.carService.add(createCarRequest);
    }

    @PutMapping("/update")
    public Result update(@RequestBody UpdateCarRequest updateCarRequest) {
        return this.carService.update(updateCarRequest);
    }

    @DeleteMapping("/delete")
    public Result delete(@RequestBody DeleteCarRequest deleteCarRequest) {
        return this.carService.delete(deleteCarRequest);
    }
}
