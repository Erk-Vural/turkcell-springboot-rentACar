package com.erkvural.rentacar.business.concretes;

import com.erkvural.rentacar.business.abstracts.AdditionalServiceService;
import com.erkvural.rentacar.business.dtos.AdditionalServiceDto;
import com.erkvural.rentacar.business.requests.additionalServiceRequests.CreateAdditionalServiceRequest;
import com.erkvural.rentacar.business.requests.additionalServiceRequests.DeleteAdditionalServiceRequest;
import com.erkvural.rentacar.business.requests.additionalServiceRequests.UpdateAdditionalServiceRequest;
import com.erkvural.rentacar.core.exceptions.BusinessException;
import com.erkvural.rentacar.core.utilities.mapping.ModelMapperService;
import com.erkvural.rentacar.core.utilities.results.ErrorResult;
import com.erkvural.rentacar.core.utilities.results.Result;
import com.erkvural.rentacar.core.utilities.results.SuccessDataResult;
import com.erkvural.rentacar.core.utilities.results.SuccessResult;
import com.erkvural.rentacar.dataaccess.abstracts.AdditionalServiceDao;
import com.erkvural.rentacar.dataaccess.abstracts.RentalDao;
import com.erkvural.rentacar.entities.concretes.AdditionalService;
import com.erkvural.rentacar.entities.concretes.Rental;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class AdditionalServiceManager implements AdditionalServiceService {
    private final AdditionalServiceDao additionalServiceDao;
    private final RentalDao rentalDao;
    private final ModelMapperService modelMapperService;

    public AdditionalServiceManager(AdditionalServiceDao additionalServiceDao, RentalDao rentalDao, ModelMapperService modelMapperService) {
        this.additionalServiceDao = additionalServiceDao;
        this.rentalDao = rentalDao;
        this.modelMapperService = modelMapperService;
    }


    @Override
    public SuccessDataResult<List<AdditionalServiceDto>> getAll() {
        List<AdditionalService> result = this.additionalServiceDao.findAll();

        List<AdditionalServiceDto> response = result.stream().map(additionalService -> this.modelMapperService.forDto()
                        .map(additionalService, AdditionalServiceDto.class))
                .collect(Collectors.toList());

        return new SuccessDataResult<>("Success, All AdditionalServices listed.", response);
    }

    @Override
    public SuccessDataResult<AdditionalServiceDto> getById(int id) {
        AdditionalService additionalService = additionalServiceDao.getById(id);

        AdditionalServiceDto response = modelMapperService.forDto().map(additionalService, AdditionalServiceDto.class);

        return new SuccessDataResult<>("Success, Additional Service with given ID found", response);
    }

    @Override
    public SuccessDataResult<List<AdditionalServiceDto>> getByRentalId(int rentalId) {
        Rental rental = this.rentalDao.getById(rentalId);

        List<AdditionalService> result = this.additionalServiceDao.getAdditionalServiceByRentalId(rental);

        List<AdditionalServiceDto> response = result.stream()
                .map(additionalService -> this.modelMapperService.forDto()
                        .map(additionalService, AdditionalServiceDto.class))
                .collect(Collectors.toList());

        return new SuccessDataResult<>("Success, Additional Service with given rentalID found", response);
    }

    @Override
    public Result add(CreateAdditionalServiceRequest createAdditionalServiceRequest) throws BusinessException {
        AdditionalService additionalService = this.modelMapperService.forRequest().map(createAdditionalServiceRequest, AdditionalService.class);

        if (checkRentalIdExist(additionalService.getRentalId())) {
            this.additionalServiceDao.save(additionalService);

            return new SuccessResult("Additional Service added: " + additionalService);
        }
        return new ErrorResult("Additional Service with given rentalID doesn't exists." + additionalService);
    }

    @Override
    public Result update(UpdateAdditionalServiceRequest updateAdditionalServiceRequest) throws BusinessException {
        AdditionalService additionalService = this.modelMapperService.forRequest().map(updateAdditionalServiceRequest, AdditionalService.class);

        if (checkAdditionalServiceIdExist(additionalService)) {
            this.additionalServiceDao.save(additionalService);

            return new SuccessResult("Additional Service updated: " + additionalService);
        }
        return new ErrorResult("Additional Service can't be updated (Rental with given Id not exists) " + additionalService);
    }

    @Override
    public Result delete(DeleteAdditionalServiceRequest deleteAdditionalServiceRequest) {
        AdditionalService additionalService = this.modelMapperService.forRequest().map(deleteAdditionalServiceRequest, AdditionalService.class);

        if (checkAdditionalServiceIdExist(additionalService)) {
            this.additionalServiceDao.deleteById(additionalService.getId());

            return new SuccessResult("Additional Service deleted with id: " + additionalService.getId());
        }
        return new ErrorResult("Additional Service can't be deleted (Additional Service with given Id not exists) " + additionalService);
    }

    private boolean checkRentalIdExist(Rental rental) {

        return Objects.nonNull(rentalDao.getRentalsById(rental.getId()));
    }

    private boolean checkAdditionalServiceIdExist(AdditionalService additionalService) {

        return Objects.nonNull(additionalServiceDao.getAdditionalServiceById(additionalService.getId()));
    }
}
