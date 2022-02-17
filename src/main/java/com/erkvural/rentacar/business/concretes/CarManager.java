package com.erkvural.rentacar.business.concretes;

import com.erkvural.rentacar.business.abstracts.CarService;
import com.erkvural.rentacar.business.dtos.ListCarDto;
import com.erkvural.rentacar.business.requests.car.CreateCarRequest;
import com.erkvural.rentacar.business.requests.car.DeleteCarRequest;
import com.erkvural.rentacar.business.requests.car.UpdateCarRequest;
import com.erkvural.rentacar.core.utilities.mapping.ModelMapperService;
import com.erkvural.rentacar.dataAccess.abstracts.CarDao;
import com.erkvural.rentacar.entities.concretes.Car;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CarManager implements CarService {
    private final CarDao carDao;
    private final ModelMapperService modelMapperService;

    @Autowired
    public CarManager(CarDao carDao, ModelMapperService modelMapperService) {
        this.carDao = carDao;
        this.modelMapperService = modelMapperService;
    }

    @Override
    public List<ListCarDto> getAll() {
        List<Car> result = carDao.findAll();
        List<ListCarDto> response = result.stream()
                .map(car -> modelMapperService.forDto()
                        .map(car, ListCarDto.class))
                .collect(Collectors.toList());

        return response;
    }

    @Override
    public ListCarDto getById(int id) {
        Car car = carDao.getById(id);
        ListCarDto response = modelMapperService.forDto().map(car, ListCarDto.class);

        return response;
    }

    @Override
    public void add(CreateCarRequest createCarRequest) {
        Car car = modelMapperService.forRequest().map(createCarRequest, Car.class);

        carDao.save(car);
    }

    @Override
    public void update(UpdateCarRequest updateCarRequest) {
        Car car = this.modelMapperService.forRequest().map(updateCarRequest, Car.class);

        if(this.carDao.getCarByCarId(car.getCarId()) == null) {
            System.out.println("Can't find car by Id to update");
        }

        this.carDao.save(car);
    }

    @Override
    public void delete(DeleteCarRequest deleteCarRequest) {
        Car car = this.modelMapperService.forRequest().map(deleteCarRequest, Car.class);

        if(this.carDao.getCarByCarId(car.getCarId()) == null) {
            System.out.println("Can't find car by Id to delete");
        }

        this.carDao.deleteById(car.getCarId());
    }
}
