package com.erkvural.rentacar.business.abstracts;

import com.erkvural.rentacar.business.dtos.ListCarDto;
import com.erkvural.rentacar.business.requests.car.CreateCarRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CarService {

    List<ListCarDto> getAll();

    ListCarDto getById(int id);

    void add(CreateCarRequest createCarRequest);

}
