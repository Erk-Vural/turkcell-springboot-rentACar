package com.erkvural.rentacar.business.abstracts;

import com.erkvural.rentacar.business.dtos.CarMaintenanceDto;
import com.erkvural.rentacar.business.requests.carmaintenance.CreateCarMaintenanceRequest;
import com.erkvural.rentacar.business.requests.carmaintenance.DeleteCarMaintenanceRequest;
import com.erkvural.rentacar.business.requests.carmaintenance.UpdateCarMaintenanceRequest;
import com.erkvural.rentacar.core.utilities.results.DataResult;
import com.erkvural.rentacar.core.utilities.results.Result;
import com.erkvural.rentacar.core.utilities.results.SuccessDataResult;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CarMaintenanceService {
    DataResult<List<CarMaintenanceDto>> getAll();

    SuccessDataResult<CarMaintenanceDto> getById(int id);

    SuccessDataResult<List<CarMaintenanceDto>> getByCarId(int id);

    DataResult<List<CarMaintenanceDto>> getAllPaged(int pageNo, int pageSize);

    DataResult<List<CarMaintenanceDto>> getAllSorted(Sort.Direction direction);

    Result add(CreateCarMaintenanceRequest createCarMaintenanceRequest);

    Result update(UpdateCarMaintenanceRequest updateCarMaintenanceRequest);

    Result delete(DeleteCarMaintenanceRequest deleteCarMaintenanceRequest);
}
