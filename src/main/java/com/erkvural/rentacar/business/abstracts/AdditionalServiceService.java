package com.erkvural.rentacar.business.abstracts;

import com.erkvural.rentacar.business.dtos.AdditionalServiceDto;
import com.erkvural.rentacar.business.requests.additionalServiceRequests.CreateAdditionalServiceRequest;
import com.erkvural.rentacar.business.requests.additionalServiceRequests.DeleteAdditionalServiceRequest;
import com.erkvural.rentacar.business.requests.additionalServiceRequests.UpdateAdditionalServiceRequest;
import com.erkvural.rentacar.core.exceptions.BusinessException;
import com.erkvural.rentacar.core.utilities.results.Result;
import com.erkvural.rentacar.core.utilities.results.SuccessDataResult;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AdditionalServiceService {

    SuccessDataResult<List<AdditionalServiceDto>> getAll();

    SuccessDataResult<AdditionalServiceDto> getById(int id);

    SuccessDataResult<List<AdditionalServiceDto>> getByRentalId(int id);

    Result add(CreateAdditionalServiceRequest createAdditionalServiceRequest) throws BusinessException;

    Result update(UpdateAdditionalServiceRequest updateAdditionalServiceRequest) throws BusinessException;

    Result delete(DeleteAdditionalServiceRequest deleteAdditionalServiceRequest);
}
