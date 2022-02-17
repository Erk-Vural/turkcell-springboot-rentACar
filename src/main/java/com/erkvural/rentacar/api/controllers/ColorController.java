package com.erkvural.rentacar.api.controllers;

import com.erkvural.rentacar.business.abstracts.ColorService;
import com.erkvural.rentacar.business.dtos.ListColorDto;
import com.erkvural.rentacar.business.requests.color.CreateColorRequest;
import com.erkvural.rentacar.business.requests.color.DeleteColorRequest;
import com.erkvural.rentacar.business.requests.color.UpdateColorRequest;
import org.hibernate.sql.Update;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/colors")
public class ColorController {

    private final ColorService colorService;

    @Autowired
    public ColorController(ColorService colorService) {
        this.colorService = colorService;
    }

    @GetMapping("/getAll")
    public List<ListColorDto> getAll() {
        return colorService.getAll();
    }

    @GetMapping("/get")
    public ListColorDto get(@RequestParam int id) {
        return colorService.getById(id);
    }

    @PostMapping("/add")
    public void add(@RequestBody CreateColorRequest createColorRequest) {
        this.colorService.add(createColorRequest);
    }

    @PostMapping("/update")
    public void add(@RequestBody UpdateColorRequest updateColorRequest) {
        this.colorService.update(updateColorRequest);
    }

    @DeleteMapping("/delete")
    public void delete(@RequestBody DeleteColorRequest deleteColorRequest) {
        this.colorService.delete(deleteColorRequest);
    }
}
