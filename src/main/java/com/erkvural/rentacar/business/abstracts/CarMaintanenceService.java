package com.erkvural.rentacar.business.abstracts;

import com.erkvural.rentacar.business.dtos.CarDto;
import com.erkvural.rentacar.business.dtos.CarMaintanenceDto;
import com.erkvural.rentacar.business.requests.carmaintenance.CreateCarMaintenanceRequest;
import com.erkvural.rentacar.business.requests.carmaintenance.DeleteCarMaintenanceRequest;
import com.erkvural.rentacar.business.requests.carmaintenance.UpdateCarMaintenanceRequest;
import com.erkvural.rentacar.core.utilities.results.DataResult;
import com.erkvural.rentacar.core.utilities.results.Result;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CarMaintanenceService {
    DataResult<List<CarMaintanenceDto>> getAll();

    DataResult<List<CarMaintanenceDto>> getById(int id);

    DataResult<List<CarMaintanenceDto>> getAllPaged(int pageNo, int pageSize);

    DataResult<List<CarMaintanenceDto>> getAllSorted(Sort.Direction direction);

    Result add(CreateCarMaintenanceRequest createCarMaintenanceRequest);

    Result update(UpdateCarMaintenanceRequest updateCarMaintenanceRequest);

    Result delete(DeleteCarMaintenanceRequest deleteCarMaintenanceRequest);
}
