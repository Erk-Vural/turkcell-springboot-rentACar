package com.erkvural.rentacar.business.abstracts;

import com.erkvural.rentacar.business.dtos.CarMaintenanceDto;
import com.erkvural.rentacar.business.requests.carmaintenance.CreateCarMaintenanceRequest;
import com.erkvural.rentacar.business.requests.carmaintenance.DeleteCarMaintenanceRequest;
import com.erkvural.rentacar.business.requests.carmaintenance.UpdateCarMaintenanceRequest;
import com.erkvural.rentacar.business.requests.rental.CreateRentalRequest;
import com.erkvural.rentacar.business.requests.rental.DeleteRentalRequest;
import com.erkvural.rentacar.business.requests.rental.UpdateRentalRequest;
import com.erkvural.rentacar.core.utilities.results.DataResult;
import com.erkvural.rentacar.core.utilities.results.Result;
import com.erkvural.rentacar.core.utilities.results.SuccessDataResult;
import org.springframework.data.domain.Sort;

import java.util.List;

public interface RentalService {
    DataResult<List<CarMaintenanceDto>> getAll();

    SuccessDataResult<CarMaintenanceDto> getById(int id);

    SuccessDataResult<List<CarMaintenanceDto>> getByCarId(int id);

    SuccessDataResult<List<CarMaintenanceDto>> getByCustomerId(int id);

    DataResult<List<CarMaintenanceDto>> getAllPaged(int pageNo, int pageSize);

    DataResult<List<CarMaintenanceDto>> getAllReturnDateSorted(Sort.Direction direction);

    DataResult<List<CarMaintenanceDto>> getAllRentalDateSorted(Sort.Direction direction);

    Result add(CreateRentalRequest createRentalRequest);

    Result update(UpdateRentalRequest updateRentalRequest);

    Result delete(DeleteRentalRequest deleteRentalRequest);
}
