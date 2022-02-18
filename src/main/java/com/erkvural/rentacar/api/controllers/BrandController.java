package com.erkvural.rentacar.api.controllers;

import com.erkvural.rentacar.business.abstracts.BrandService;
import com.erkvural.rentacar.business.dtos.ListBrandDto;
import com.erkvural.rentacar.business.requests.brand.CreateBrandRequest;
import com.erkvural.rentacar.business.requests.brand.DeleteBrandRequest;
import com.erkvural.rentacar.business.requests.brand.UpdateBrandRequest;
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
    public List<ListBrandDto> getAll() {
        return brandService.getAll();
    }

    @GetMapping("/get")
    public ListBrandDto get(@RequestParam("id") int id) {
        return brandService.getById(id);
    }

    @PostMapping("/add")
    public void add(@RequestBody CreateBrandRequest createBrandRequest) {
        this.brandService.add(createBrandRequest);
    }

    @PutMapping("/update")
    public void update(@RequestBody UpdateBrandRequest updateBrandRequest) {
        this.brandService.update(updateBrandRequest);
    }

    @DeleteMapping("/Delete")
    public void delete(@RequestBody DeleteBrandRequest deleteBrandRequest) {
        this.brandService.delete(deleteBrandRequest);
    }
}
