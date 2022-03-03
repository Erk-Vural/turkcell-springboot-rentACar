package com.erkvural.rentacar.api.controllers;

import com.erkvural.rentacar.business.abstracts.CarMaintenanceService;
import com.erkvural.rentacar.business.dtos.CarMaintenanceDto;
import com.erkvural.rentacar.business.requests.carmaintenance.CreateCarMaintenanceRequest;
import com.erkvural.rentacar.business.requests.carmaintenance.DeleteCarMaintenanceRequest;
import com.erkvural.rentacar.business.requests.carmaintenance.UpdateCarMaintenanceRequest;
import com.erkvural.rentacar.core.utilities.results.DataResult;
import com.erkvural.rentacar.core.utilities.results.ErrorResult;
import com.erkvural.rentacar.core.utilities.results.Result;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/carMaintenances")
public class CarMaintenanceController {

    private final CarMaintenanceService carMaintenanceService;


    public CarMaintenanceController(CarMaintenanceService carMaintenanceService) {
        this.carMaintenanceService = carMaintenanceService;
    }

    @GetMapping("/getAll")
    public DataResult<List<CarMaintenanceDto>> getAll() {
        return carMaintenanceService.getAll();
    }

    @GetMapping("/get")
    public DataResult<CarMaintenanceDto> get(@RequestParam("id") int id) {
        return carMaintenanceService.getById(id);
    }

    @GetMapping("/getAllSorted")
    public DataResult<List<CarMaintenanceDto>> getAllSorted(@RequestParam("direction") Sort.Direction direction) {
        return this.carMaintenanceService.getAllSorted(direction);
    }

    @GetMapping("/getAllPaged")
    public DataResult<List<CarMaintenanceDto>> getAllPaged(@RequestParam("pageNo") int pageNo,
                                                           @RequestParam("pageSize") int pageSize) {
        return this.carMaintenanceService.getAllPaged(pageNo, pageSize);
    }

    @PostMapping("/add")
    public Result add(@RequestBody CreateCarMaintenanceRequest createCarMaintenanceRequest) {
        try {
            return this.carMaintenanceService.add(createCarMaintenanceRequest);
        } catch (Exception e) {
            return new ErrorResult(e.getMessage());
        }
    }

    @PutMapping("/update")
    public Result update(@RequestBody UpdateCarMaintenanceRequest updateCarMaintenanceRequest) {
        try {
            return this.carMaintenanceService.update(updateCarMaintenanceRequest);
        } catch (Exception e) {
            return new ErrorResult(e.getMessage());
        }
    }

    @DeleteMapping("/delete")
    public Result delete(@RequestBody DeleteCarMaintenanceRequest deleteCarMaintenanceRequest) {
        try {
            return this.carMaintenanceService.delete(deleteCarMaintenanceRequest);
        } catch (Exception e) {
            return new ErrorResult(e.getMessage());
        }
    }
}
