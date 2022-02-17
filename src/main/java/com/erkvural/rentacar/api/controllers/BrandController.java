package com.erkvural.rentacar.api.controllers;

import com.erkvural.rentacar.business.abstracts.BrandService;
import com.erkvural.rentacar.business.dtos.ListBrandDto;
import com.erkvural.rentacar.business.requests.CreateBrandRequest;
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
    public ListBrandDto get(@RequestParam int id) {
        return brandService.getById(id);
    }

    @PostMapping("/add")
    public void add(@RequestBody CreateBrandRequest createBrandRequest) {
        this.brandService.add(createBrandRequest);
    }

}
