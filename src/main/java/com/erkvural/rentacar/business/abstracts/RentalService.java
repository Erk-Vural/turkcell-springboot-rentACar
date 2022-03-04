package com.erkvural.rentacar.business.abstracts;

import com.erkvural.rentacar.business.dtos.RentalDto;
import com.erkvural.rentacar.business.requests.rental.CreateRentalRequest;
import com.erkvural.rentacar.business.requests.rental.DeleteRentalRequest;
import com.erkvural.rentacar.business.requests.rental.UpdateRentalRequest;
import com.erkvural.rentacar.core.utilities.results.Result;
import com.erkvural.rentacar.core.utilities.results.SuccessDataResult;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface RentalService {
    SuccessDataResult<List<RentalDto>> getAll();

    SuccessDataResult<RentalDto> getById(int id);

    SuccessDataResult<List<RentalDto>> getByCarId(int id);

    SuccessDataResult<List<RentalDto>> getByCustomerId(int id);

    SuccessDataResult<List<RentalDto>> getAllPaged(int pageNo, int pageSize);

    SuccessDataResult<List<RentalDto>> getAllReturnDateSorted(Sort.Direction direction);

    SuccessDataResult<List<RentalDto>> getAllRentalDateSorted(Sort.Direction direction);

    Result add(CreateRentalRequest createRentalRequest);

    Result update(UpdateRentalRequest updateRentalRequest);

    Result delete(DeleteRentalRequest deleteRentalRequest);
}
