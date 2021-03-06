package com.erkvural.rentacar.business.concretes;

import com.erkvural.rentacar.business.abstracts.CarService;
import com.erkvural.rentacar.business.dtos.CarDto;
import com.erkvural.rentacar.business.requests.car.CreateCarRequest;
import com.erkvural.rentacar.business.requests.car.DeleteCarRequest;
import com.erkvural.rentacar.business.requests.car.UpdateCarRequest;
import com.erkvural.rentacar.core.utilities.mapping.ModelMapperService;
import com.erkvural.rentacar.core.utilities.results.*;
import com.erkvural.rentacar.dataaccess.abstracts.BrandDao;
import com.erkvural.rentacar.dataaccess.abstracts.CarDao;
import com.erkvural.rentacar.dataaccess.abstracts.ColorDao;
import com.erkvural.rentacar.entities.concretes.Car;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class CarManager implements CarService {
    private final CarDao carDao;
    private final BrandDao brandDao;
    private final ColorDao colorDao;
    private final ModelMapperService modelMapperService;

    @Autowired
    public CarManager(CarDao carDao, BrandDao brandDao, ColorDao colorDao, ModelMapperService modelMapperService) {
        this.carDao = carDao;
        this.brandDao = brandDao;
        this.colorDao = colorDao;
        this.modelMapperService = modelMapperService;
    }

    @Override
    public DataResult<List<CarDto>> getAll() {
        List<Car> result = carDao.findAll();
        List<CarDto> response = result.stream()
                .map(car -> modelMapperService.forDto()
                        .map(car, CarDto.class))
                .collect(Collectors.toList());

        return new SuccessDataResult<List<CarDto>>("Success", response);
    }

    @Override
    public DataResult<CarDto> getById(int id) {
        Car car = carDao.getById(id);

        if (car != null) {
            CarDto response = modelMapperService.forDto().map(car, CarDto.class);

            return new SuccessDataResult<CarDto>("Car with given ID found.", response);
        }
        return new ErrorDataResult<CarDto>("Car with given ID can't be found.");
    }

    @Override
    public DataResult<List<CarDto>> getAllPaged(int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo - 1, pageSize);

        List<Car> result = this.carDao.findAll(pageable).getContent();
        List<CarDto> response = result.stream()
                .map(car -> this.modelMapperService.forDto()
                        .map(car, CarDto.class))
                .collect(Collectors.toList());

        return new SuccessDataResult<List<CarDto>>(response);
    }

    @Override
    public DataResult<List<CarDto>> getAllSorted(Sort.Direction direction) {
        Sort s = Sort.by(direction, "dailyPrice");

        List<Car> result = this.carDao.findAll(s);
        List<CarDto> response = result.stream()
                .map(car -> this.modelMapperService.forDto()
                        .map(car, CarDto.class))
                .collect(Collectors.toList());

        return new SuccessDataResult<List<CarDto>>(response);
    }

    @Override
    public DataResult<List<CarDto>> getAllByDailyPriceLessThanEqual(double dailyPrice) {
        List<Car> result = this.carDao.getCarByDailyPriceLessThanEqual(dailyPrice);

        if (result.isEmpty()) {
            return new ErrorDataResult<List<CarDto>>("No results");
        }

        List<CarDto> response = result.stream()
                .map(car -> this.modelMapperService.forDto()
                        .map(car, CarDto.class))
                .collect(Collectors.toList());

        return new SuccessDataResult<List<CarDto>>("Results Listed.", response);
    }

    @Override
    public Result add(CreateCarRequest createCarRequest) {
        Car car = this.modelMapperService.forRequest().map(createCarRequest, Car.class);
        String brandName = brandDao.findById(car.getBrand().getId()).get().getName();
        String colorName = colorDao.findById(car.getColor().getId()).get().getName();

        this.carDao.save(car);

        return new SuccessResult("Car added: " + brandName + ", " + colorName);
    }

    @Override
    public Result update(UpdateCarRequest updateCarRequest) {
        Car car = this.modelMapperService.forRequest().map(updateCarRequest, Car.class);
        String brandName = brandDao.findById(car.getBrand().getId()).get().getName();
        String colorName = colorDao.findById(car.getColor().getId()).get().getName();

        if (checkCarIdExist(car)) {
            this.carDao.save(car);

            return new SuccessResult("Car updated: " + brandName + ", " + colorName);
        }
        return new ErrorResult("Car can't be updated (Car with given Id not exists) " + brandName + ", " + colorName);
    }

    @Override
    public Result delete(DeleteCarRequest deleteCarRequest) {
        Car car = this.modelMapperService.forRequest().map(deleteCarRequest, Car.class);
        String brandName = brandDao.findById(car.getBrand().getId()).get().getName();
        String colorName = colorDao.findById(car.getColor().getId()).get().getName();

        if (checkCarIdExist(car)) {
            this.carDao.deleteById(car.getId());

            return new SuccessResult("Car deleted with id: " + car.getId());
        }
        return new ErrorResult("Car can't be deleted (Car with given Id not exists) " + brandName + ", " + colorName);
    }

    private boolean checkCarIdExist(Car car) {

        return Objects.nonNull(carDao.getCarById(car.getId()));
    }
}
