package com.erkvural.rentacar.api.controllers;

import com.erkvural.rentacar.business.abstracts.RentalService;
import com.erkvural.rentacar.business.dtos.RentalDto;
import com.erkvural.rentacar.business.requests.rental.CreateRentalRequest;
import com.erkvural.rentacar.business.requests.rental.DeleteRentalRequest;
import com.erkvural.rentacar.business.requests.rental.UpdateRentalRequest;
import com.erkvural.rentacar.core.utilities.results.ErrorResult;
import com.erkvural.rentacar.core.utilities.results.Result;
import com.erkvural.rentacar.core.utilities.results.SuccessDataResult;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/rentals")
public class RentalController {

    private final RentalService rentalService;

    public RentalController(RentalService rentalService) {
        this.rentalService = rentalService;
    }

    @GetMapping("/getAll")
    public SuccessDataResult<List<RentalDto>> getAll() {
        return rentalService.getAll();
    }

    @GetMapping("/get")
    public SuccessDataResult<RentalDto> get(@RequestParam("id") int id) {
        return rentalService.getById(id);
    }

    @GetMapping("/getByCarId")
    public SuccessDataResult<List<RentalDto>> getByCarId(@RequestParam("id") int id) {
        return rentalService.getByCarId(id);
    }

    @GetMapping("/getByCustomerId")
    public SuccessDataResult<List<RentalDto>> getByCustomerId(@RequestParam("id") int id) {
        return rentalService.getByCustomerId(id);
    }

    @GetMapping("/getAllReturnDateSorted")
    public SuccessDataResult<List<RentalDto>> getAllReturnDateSorted(@RequestParam("direction") Sort.Direction direction) {
        return this.rentalService.getAllReturnDateSorted(direction);
    }

    @GetMapping("/getAllRentalDateSorted")
    public SuccessDataResult<List<RentalDto>> getAllRentalDateSorted(@RequestParam("direction") Sort.Direction direction) {
        return this.rentalService.getAllRentalDateSorted(direction);
    }

    @GetMapping("/getAllPaged")
    public SuccessDataResult<List<RentalDto>> getAllPaged(@RequestParam("pageNo") int pageNo,
                                                          @RequestParam("pageSize") int pageSize) {
        return this.rentalService.getAllPaged(pageNo, pageSize);
    }

    @PostMapping("/add")
    public Result add(@RequestBody CreateRentalRequest createRentalRequest) {
        try {
            return this.rentalService.add(createRentalRequest);
        } catch (Exception e) {
            return new ErrorResult(e.getMessage());
        }
    }

    @PutMapping("/update")
    public Result update(@RequestBody UpdateRentalRequest updateRentalRequest) {
        try {
            return this.rentalService.update(updateRentalRequest);
        } catch (Exception e) {
            return new ErrorResult(e.getMessage());
        }
    }

    @DeleteMapping("/delete")
    public Result delete(@RequestBody DeleteRentalRequest deleteRentalRequest) {
        try {
            return this.rentalService.delete(deleteRentalRequest);
        } catch (Exception e) {
            return new ErrorResult(e.getMessage());
        }
    }
}
