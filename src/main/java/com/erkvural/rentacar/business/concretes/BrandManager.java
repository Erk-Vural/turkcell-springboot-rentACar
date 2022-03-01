package com.erkvural.rentacar.business.concretes;

import com.erkvural.rentacar.business.abstracts.BrandService;
import com.erkvural.rentacar.business.dtos.BrandDto;
import com.erkvural.rentacar.business.requests.brand.CreateBrandRequest;
import com.erkvural.rentacar.business.requests.brand.DeleteBrandRequest;
import com.erkvural.rentacar.business.requests.brand.UpdateBrandRequest;
import com.erkvural.rentacar.core.utilities.mapping.ModelMapperService;
import com.erkvural.rentacar.core.utilities.results.*;
import com.erkvural.rentacar.dataaccess.abstracts.BrandDao;
import com.erkvural.rentacar.entities.concretes.Brand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class BrandManager implements BrandService {
    private final BrandDao brandDao;
    private final ModelMapperService modelMapperService;

    @Autowired
    public BrandManager(BrandDao brandDao, ModelMapperService modelMapperService) {
        this.brandDao = brandDao;
        this.modelMapperService = modelMapperService;
    }


    @Override
    public DataResult<List<BrandDto>> getAll() {
        List<Brand> result = brandDao.findAll();
        List<BrandDto> response = result.stream()
                .map(brand -> modelMapperService.forDto()
                        .map(brand, BrandDto.class))
                .collect(Collectors.toList());

        return new SuccessDataResult<List<BrandDto>>("Success", response);
    }

    @Override
    public DataResult<BrandDto> getById(int id) {
        Brand brand = this.brandDao.getById(id);

        if (brand != null) {
            BrandDto response = modelMapperService.forDto().map(brand, BrandDto.class);

            return new SuccessDataResult<BrandDto>("Brand with given ID found.", response);
        }
        return new ErrorDataResult<BrandDto>("Brand with given ID can't be found.");
    }

    @Override
    public Result add(CreateBrandRequest createBrandRequest) {
        Brand brand = modelMapperService.forRequest().map(createBrandRequest, Brand.class);
        if (!checkBrandNameExist(brand)) {
            this.brandDao.save(brand);

            return new SuccessResult("Brand added: " + brand.getName());
        }
        return new ErrorResult("Brand can't be added (Brand with same name exists) " + brand.getName());
    }

    @Override
    public Result update(UpdateBrandRequest updateBrandRequest) {
        Brand brand = this.modelMapperService.forRequest().map(updateBrandRequest, Brand.class);

        if (checkBrandIdExist(brand)) {
            this.brandDao.save(brand);

            return new SuccessResult("Brand updated: " + brand.getName());
        }
        return new ErrorResult("Brand can't be updated (Brand with given Id not exists) " + brand.getName());
    }

    @Override
    public Result delete(DeleteBrandRequest deleteBrandRequest) {
        Brand brand = this.modelMapperService.forRequest().map(deleteBrandRequest, Brand.class);

        if (checkBrandIdExist(brand)) {
            this.brandDao.deleteById(brand.getId());

            return new SuccessResult("Brand deleted with id: " + brand.getId());
        }
        return new ErrorResult("Brand can't be deleted (Brand with given Id not exists) " +  brand.getId());
    }

    private boolean checkBrandIdExist(Brand brand) {

        return this.brandDao.getBrandById(brand.getId()) != null;
    }

    private boolean checkBrandNameExist(Brand brand) {
        return Objects.nonNull(brandDao.getBrandByName(brand.getName()));
    }
}
