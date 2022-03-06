package com.erkvural.rentacar.business.concretes;

import com.erkvural.rentacar.business.abstracts.CarMaintenanceService;
import com.erkvural.rentacar.business.dtos.CarMaintenanceDto;
import com.erkvural.rentacar.business.requests.carmaintenance.CreateCarMaintenanceRequest;
import com.erkvural.rentacar.business.requests.carmaintenance.DeleteCarMaintenanceRequest;
import com.erkvural.rentacar.business.requests.carmaintenance.UpdateCarMaintenanceRequest;
import com.erkvural.rentacar.core.exceptions.BusinessException;
import com.erkvural.rentacar.core.utilities.mapping.ModelMapperService;
import com.erkvural.rentacar.core.utilities.results.*;
import com.erkvural.rentacar.dataaccess.abstracts.CarDao;
import com.erkvural.rentacar.dataaccess.abstracts.CarMaintenanceDao;
import com.erkvural.rentacar.dataaccess.abstracts.RentalDao;
import com.erkvural.rentacar.entities.concretes.Car;
import com.erkvural.rentacar.entities.concretes.CarMaintenance;
import com.erkvural.rentacar.entities.concretes.Rental;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class CarMaintenanceManager implements CarMaintenanceService {

    private final CarMaintenanceDao carMaintenanceDao;
    private final CarDao carDao;
    private final ModelMapperService modelMapperService;
    private final RentalDao rentalDao;

    @Autowired
    public CarMaintenanceManager(CarMaintenanceDao carMaintenanceDao, CarDao carDao, ModelMapperService modelMapperService, RentalDao rentalDao) {
        this.carMaintenanceDao = carMaintenanceDao;
        this.carDao = carDao;
        this.modelMapperService = modelMapperService;
        this.rentalDao = rentalDao;
    }


    @Override
    public DataResult<List<CarMaintenanceDto>> getAll() {
        List<CarMaintenance> result = this.carMaintenanceDao.findAll();

        List<CarMaintenanceDto> response = result.stream().map(carMaintenance -> this.modelMapperService.forDto().map(carMaintenance, CarMaintenanceDto.class)).collect(Collectors.toList());

        return new SuccessDataResult<List<CarMaintenanceDto>>("Success", response);
    }

    @Override
    public SuccessDataResult<CarMaintenanceDto> getById(int id) {
        CarMaintenance carMaintenance = carMaintenanceDao.getById(id);

        CarMaintenanceDto response = modelMapperService.forDto().map(carMaintenance, CarMaintenanceDto.class);

        return new SuccessDataResult<CarMaintenanceDto>("Car maintenance with given ID found.", response);
    }

    @Override
    public SuccessDataResult<List<CarMaintenanceDto>> getByCarId(int id) {
        Car car = this.carDao.getById(id);

        List<CarMaintenance> result = this.carMaintenanceDao.getCarMaintenanceByCarId(car);

        List<CarMaintenanceDto> response = result.stream().map(carMaintenance -> this.modelMapperService.forDto().map(carMaintenance, CarMaintenanceDto.class)).collect(Collectors.toList());

        return new SuccessDataResult<List<CarMaintenanceDto>>("Success", response);
    }

    @Override
    public DataResult<List<CarMaintenanceDto>> getAllPaged(int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo - 1, pageSize);

        List<CarMaintenance> result = this.carMaintenanceDao.findAll(pageable).getContent();
        List<CarMaintenanceDto> response = result.stream().map(carMaintenance -> this.modelMapperService.forDto().map(carMaintenance, CarMaintenanceDto.class)).collect(Collectors.toList());

        return new SuccessDataResult<List<CarMaintenanceDto>>(response);
    }

    @Override
    public DataResult<List<CarMaintenanceDto>> getAllSorted(Sort.Direction direction) {
        Sort s = Sort.by(direction, "returnDate");

        List<CarMaintenance> result = this.carMaintenanceDao.findAll(s);
        List<CarMaintenanceDto> response = result.stream().map(carMaintenance -> this.modelMapperService.forDto().map(carMaintenance, CarMaintenanceDto.class)).collect(Collectors.toList());

        return new SuccessDataResult<List<CarMaintenanceDto>>(response);
    }

    @Override
    public Result add(CreateCarMaintenanceRequest createCarMaintenanceRequest) {
        CarMaintenance carMaintenance = this.modelMapperService.forRequest().map(createCarMaintenanceRequest, CarMaintenance.class);

        if (checkCarIdExist(carMaintenance.getCarId()) && checkIsRented(carMaintenance)) {
            this.carMaintenanceDao.save(carMaintenance);

            return new SuccessResult("Car maintenance added: " + carMaintenance);
        }
        return new ErrorResult("Car maintenance with given carId doesn't exists." + carMaintenance);
    }

    @Override
    public Result update(UpdateCarMaintenanceRequest updateCarMaintenanceRequest) throws BusinessException {
        CarMaintenance carMaintenance = this.modelMapperService.forRequest().map(updateCarMaintenanceRequest, CarMaintenance.class);

        if (checkCarMaintenanceIdExist(carMaintenance) && checkIsRented(carMaintenance)) {
            this.carMaintenanceDao.save(carMaintenance);

            return new SuccessResult("Car maintenance updated: " + carMaintenance);
        }

        return new ErrorResult("Car maintenance can't be updated (Car maintenance with given Id not exists) " + carMaintenance);
    }

    @Override
    public Result delete(DeleteCarMaintenanceRequest deleteCarMaintenanceRequest) {
        CarMaintenance carMaintenance = this.modelMapperService.forRequest().map(deleteCarMaintenanceRequest, CarMaintenance.class);

        if (checkCarMaintenanceIdExist(carMaintenance)) {
            this.carMaintenanceDao.deleteById(carMaintenance.getId());

            return new SuccessResult("Car maintenance deleted with id: " + carMaintenance.getId());
        }
        return new ErrorResult("Car maintenance can't be deleted (Car maintenance with given Id not exists) " + carMaintenance);
    }

    private boolean checkCarIdExist(Car car) {

        return Objects.nonNull(carDao.getCarById(car.getId()));
    }

    private boolean checkCarMaintenanceIdExist(CarMaintenance carMaintenance) {

        return Objects.nonNull(carMaintenanceDao.getCarMaintenanceById(carMaintenance.getId()));
    }

    private boolean checkIsRented(CarMaintenance carMaintenance) {
        List<Rental> result = this.rentalDao.getRentalsByCarId(carMaintenance.getCarId());

        if (result == null) {
            return true;
        }

        for (Rental rental : result) {

            if (rental.getReturnDate() != null
                    && carMaintenance.getReturnDate().isAfter(rental.getRentDate())
                    && carMaintenance.getReturnDate().isBefore((rental.getReturnDate()))) {
                return false;
            }
            if (rental.getReturnDate() == null
                    && carMaintenance.getReturnDate().isAfter(rental.getRentDate())
                    || carMaintenance.getReturnDate().equals(rental.getRentDate())) {
                return false;
            }
        }
        return true;
    }
}
