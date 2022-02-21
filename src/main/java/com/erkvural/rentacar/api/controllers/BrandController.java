package com.erkvural.rentacar.api.controllers;

import com.erkvural.rentacar.business.abstracts.BrandService;
import com.erkvural.rentacar.business.dtos.BrandDto;
import com.erkvural.rentacar.business.requests.brand.CreateBrandRequest;
import com.erkvural.rentacar.business.requests.brand.DeleteBrandRequest;
import com.erkvural.rentacar.business.requests.brand.UpdateBrandRequest;
import com.erkvural.rentacar.core.utilities.results.DataResult;
import com.erkvural.rentacar.core.utilities.results.ErrorResult;
import com.erkvural.rentacar.core.utilities.results.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/brands")
public class BrandController {

    private final BrandService brandService;

    @Autowired
    public BrandController(BrandService brandService) {
        this.brandService = brandService;
    }

    @GetMapping("/getAll")
    public DataResult<List<BrandDto>> getAll() {
        return brandService.getAll();
    }

    @GetMapping("/get")
    public DataResult<BrandDto> get(@RequestParam("id") int id) {
        return brandService.getById(id);
    }

    @PostMapping("/add")
    public Result add(@RequestBody CreateBrandRequest createBrandRequest) {
        try {
            return this.brandService.add(createBrandRequest);
        } catch (Exception e) {
            return new ErrorResult(e.getMessage());
        }

    }

    @PutMapping("/update")
    public Result update(@RequestBody UpdateBrandRequest updateBrandRequest) {
        try {
            return this.brandService.update(updateBrandRequest);
        } catch (Exception e) {
            return new ErrorResult(e.getMessage());
        }
    }

    @DeleteMapping("/Delete")
    public Result delete(@RequestBody DeleteBrandRequest deleteBrandRequest) {
        try {
            return this.brandService.delete(deleteBrandRequest);
        } catch (Exception e) {
            return new ErrorResult(e.getMessage());
        }
    }
}
