package com.erkvural.rentacar.api.controllers;

import com.erkvural.rentacar.business.abstracts.BrandService;
import com.erkvural.rentacar.business.dtos.ListBrandDto;
import com.erkvural.rentacar.business.requests.CreateBrandRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
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
        return brandService.getBrandByBrandId(id);
    }

    @GetMapping("/add")
    public void add(@RequestBody CreateBrandRequest createBrandRequest) {
        this.brandService.add(createBrandRequest);
    }

}
