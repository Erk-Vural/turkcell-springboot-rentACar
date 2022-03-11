package com.erkvural.rentacar.api.controllers;

import com.erkvural.rentacar.business.abstracts.AdditionalServiceService;
import com.erkvural.rentacar.business.dtos.AdditionalServiceDto;
import com.erkvural.rentacar.business.requests.additionalServiceRequests.CreateAdditionalServiceRequest;
import com.erkvural.rentacar.business.requests.additionalServiceRequests.DeleteAdditionalServiceRequest;
import com.erkvural.rentacar.business.requests.additionalServiceRequests.UpdateAdditionalServiceRequest;
import com.erkvural.rentacar.core.exceptions.BusinessException;
import com.erkvural.rentacar.core.utilities.results.ErrorResult;
import com.erkvural.rentacar.core.utilities.results.Result;
import com.erkvural.rentacar.core.utilities.results.SuccessDataResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/additional-services")
public class AdditionalServiceController {

    private final AdditionalServiceService additionalServiceService;


    public AdditionalServiceController(AdditionalServiceService additionalServiceService) {
        this.additionalServiceService = additionalServiceService;
    }

    @GetMapping("/getAll")
    public SuccessDataResult<List<AdditionalServiceDto>> getAll() {
        return additionalServiceService.getAll();
    }

    @GetMapping("/get")
    public SuccessDataResult<AdditionalServiceDto> get(@RequestParam("id") int id) {
        return additionalServiceService.getById(id);
    }

    @GetMapping("/getByRentalId")
    public SuccessDataResult<List<AdditionalServiceDto>> getByRentalId(@RequestParam("id") int id) {
        return additionalServiceService.getByRentalId(id);
    }

    @PostMapping("/add")
    public Result add(@RequestBody CreateAdditionalServiceRequest createAdditionalServiceRequest) throws BusinessException {
        return this.additionalServiceService.add(createAdditionalServiceRequest);
    }

    @PutMapping("/update")
    public Result update(@RequestBody UpdateAdditionalServiceRequest updateAdditionalServiceRequest) throws BusinessException {
        return this.additionalServiceService.update(updateAdditionalServiceRequest);
    }

    @DeleteMapping("/delete")
    public Result delete(@RequestBody DeleteAdditionalServiceRequest deleteAdditionalServiceRequest) {
        return this.additionalServiceService.delete(deleteAdditionalServiceRequest);
    }
}
