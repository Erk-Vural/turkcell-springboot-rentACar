package com.erkvural.rentacar.api.controllers;

import com.erkvural.rentacar.business.abstracts.ColorService;
import com.erkvural.rentacar.business.dtos.ColorDto;
import com.erkvural.rentacar.business.requests.color.CreateColorRequest;
import com.erkvural.rentacar.business.requests.color.DeleteColorRequest;
import com.erkvural.rentacar.business.requests.color.UpdateColorRequest;
import com.erkvural.rentacar.core.utilities.results.DataResult;
import com.erkvural.rentacar.core.utilities.results.ErrorResult;
import com.erkvural.rentacar.core.utilities.results.Result;
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
    public DataResult<List<ColorDto>> getAll() {
        return colorService.getAll();
    }

    @GetMapping("/get")
    public DataResult<ColorDto> get(@RequestParam("id") int id) {
        return colorService.getById(id);
    }

    @PostMapping("/add")
    public Result add(@RequestBody CreateColorRequest createColorRequest) {
        return this.colorService.add(createColorRequest);
    }

    @PutMapping("/update")
    public Result update(@RequestBody UpdateColorRequest updateColorRequest) {
        return this.colorService.update(updateColorRequest);
    }

    @DeleteMapping("/delete")
    public Result delete(@RequestBody DeleteColorRequest deleteColorRequest) {
        return this.colorService.delete(deleteColorRequest);
    }
}
