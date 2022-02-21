package com.erkvural.rentacar.business.abstracts;

import com.erkvural.rentacar.business.dtos.CarDto;
import com.erkvural.rentacar.business.requests.car.CreateCarRequest;
import com.erkvural.rentacar.business.requests.car.DeleteCarRequest;
import com.erkvural.rentacar.business.requests.car.UpdateCarRequest;
import com.erkvural.rentacar.core.utilities.results.DataResult;
import com.erkvural.rentacar.core.utilities.results.Result;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CarService {

    DataResult<List<CarDto>> getAll();

    DataResult<CarDto> getById(int id);

    Result add(CreateCarRequest createCarRequest);

    Result update(UpdateCarRequest updateCarRequest);

    Result delete(DeleteCarRequest deleteCarRequest);

}
