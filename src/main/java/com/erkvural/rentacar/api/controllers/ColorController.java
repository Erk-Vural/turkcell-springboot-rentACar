package com.erkvural.rentacar.api.controllers;

import com.erkvural.rentacar.business.abstracts.ColorService;
import com.erkvural.rentacar.business.dtos.ListColorDto;
import com.erkvural.rentacar.business.requests.CreateColorRequest;
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
        return colorService.getColorByColorId(id);
    }

    @PostMapping("/add")
    public void add(@RequestBody CreateColorRequest createColorRequest) {
        this.colorService.add(createColorRequest);
    }

}
