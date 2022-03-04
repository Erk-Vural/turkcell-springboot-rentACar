package com.erkvural.rentacar.business.concretes;

import com.erkvural.rentacar.business.abstracts.RentalService;
import com.erkvural.rentacar.business.dtos.RentalDto;
import com.erkvural.rentacar.business.requests.rental.CreateRentalRequest;
import com.erkvural.rentacar.business.requests.rental.DeleteRentalRequest;
import com.erkvural.rentacar.business.requests.rental.UpdateRentalRequest;
import com.erkvural.rentacar.core.utilities.mapping.ModelMapperService;
import com.erkvural.rentacar.core.utilities.results.ErrorResult;
import com.erkvural.rentacar.core.utilities.results.Result;
import com.erkvural.rentacar.core.utilities.results.SuccessDataResult;
import com.erkvural.rentacar.core.utilities.results.SuccessResult;
import com.erkvural.rentacar.dataaccess.abstracts.CarDao;
import com.erkvural.rentacar.dataaccess.abstracts.RentalDao;
import com.erkvural.rentacar.entities.concretes.Car;
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
public class RentalManager implements RentalService {
    private final RentalDao rentalDao;
    private final CarDao carDao;
    private final ModelMapperService modelMapperService;

    @Autowired
    public RentalManager(RentalDao rentalDao, CarDao carDao, ModelMapperService modelMapperService) {
        this.rentalDao = rentalDao;
        this.carDao = carDao;
        this.modelMapperService = modelMapperService;
    }

    @Override
    public SuccessDataResult<List<RentalDto>> getAll() {
        List<Rental> result = this.rentalDao.findAll();

        List<RentalDto> response = result.stream().map(rental -> this.modelMapperService.forDto().map(rental, RentalDto.class)).collect(Collectors.toList());

        return new SuccessDataResult<List<RentalDto>>("Success", response);
    }

    @Override
    public SuccessDataResult<RentalDto> getById(int id) {
        Rental rental = rentalDao.getById(id);

        RentalDto rentalDto = modelMapperService.forDto().map(rental, RentalDto.class);

        return new SuccessDataResult<RentalDto>("Rental with given ID found");
    }

    @Override
    public SuccessDataResult<List<RentalDto>> getByCarId(int id) {
        Car car = this.carDao.getById(id);

        List<Rental> result = this.rentalDao.getRentalsByCarId(car);

        List<RentalDto> response = result.stream().map(rental -> this.modelMapperService.forDto().map(rental, RentalDto.class)).collect(Collectors.toList());

        return new SuccessDataResult<List<RentalDto>>("Success", response);
    }

    @Override
    public SuccessDataResult<List<RentalDto>> getByCustomerId(int customerId) {

        List<Rental> result = this.rentalDao.getRentalsByCustomerId(customerId);

        List<RentalDto> response = result.stream().map(rental -> this.modelMapperService.forDto().map(rental, RentalDto.class)).collect(Collectors.toList());

        return new SuccessDataResult<List<RentalDto>>("Success", response);
    }

    @Override
    public SuccessDataResult<List<RentalDto>> getAllPaged(int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo - 1, pageSize);

        List<Rental> result = this.rentalDao.findAll(pageable).getContent();
        List<RentalDto> response = result.stream()
                .map(rental -> this.modelMapperService.forDto()
                        .map(rental, RentalDto.class))
                .collect(Collectors.toList());

        return new SuccessDataResult<List<RentalDto>>(response);
    }

    @Override
    public SuccessDataResult<List<RentalDto>> getAllReturnDateSorted(Sort.Direction direction) {
        Sort s = Sort.by(direction, "rentDate");

        List<Rental> result = this.rentalDao.findAll(s);
        List<RentalDto> response = result.stream()
                .map(rental -> this.modelMapperService.forDto()
                        .map(rental, RentalDto.class))
                .collect(Collectors.toList());

        return new SuccessDataResult<List<RentalDto>>(response);
    }

    @Override
    public SuccessDataResult<List<RentalDto>> getAllRentalDateSorted(Sort.Direction direction) {
        Sort s = Sort.by(direction, "returnDate");

        List<Rental> result = this.rentalDao.findAll(s);
        List<RentalDto> response = result.stream()
                .map(rental -> this.modelMapperService.forDto()
                        .map(rental, RentalDto.class))
                .collect(Collectors.toList());

        return new SuccessDataResult<List<RentalDto>>(response);
    }

    @Override
    public Result add(CreateRentalRequest createRentalRequest) {
        Rental rental = this.modelMapperService.forRequest().map(createRentalRequest, Rental.class);

        if (checkCarIdExist(rental.getCarId()) && checkCustomerIdExist(rental.getCustomerId())) {
            this.rentalDao.save(rental);

            return new SuccessResult("Rental added: " + rental);
        }
        return new ErrorResult("Rental with given carId or customerId doesn't exists." + rental);
    }

    @Override
    public Result update(UpdateRentalRequest updateRentalRequest) {
        Rental rental = this.modelMapperService.forRequest().map(updateRentalRequest, Rental.class);

        if (checkRentalIdExist(rental)) {
            this.rentalDao.save(rental);

            return new SuccessResult("Rental updated: " + rental);
        }

        return new ErrorResult("Rental can't be updated (Rental with given Id not exists) " + rental);
    }

    @Override
    public Result delete(DeleteRentalRequest deleteRentalRequest) {
        Rental rental = this.modelMapperService.forRequest().map(deleteRentalRequest, Rental.class);

        if (checkRentalIdExist(rental)) {
            this.rentalDao.deleteById(rental.getId());

            return new SuccessResult("Rental deleted with id: " + rental.getId());
        }

        return new ErrorResult("Rental can't be updated (Rental with given Id not exists) " + rental);
    }

    private boolean checkCarIdExist(Car car) {

        return Objects.nonNull(carDao.getCarById(car.getId()));
    }

    private boolean checkCustomerIdExist(int id) {

        return Objects.nonNull(rentalDao.getRentalsByCustomerId(id));
    }

    private boolean checkRentalIdExist(Rental rental) {

        return Objects.nonNull(rentalDao.getRentalsById(rental.getId()));
    }
}
