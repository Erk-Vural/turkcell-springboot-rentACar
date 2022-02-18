package com.erkvural.rentacar.business.concretes;

import com.erkvural.rentacar.business.abstracts.BrandService;
import com.erkvural.rentacar.business.dtos.ListBrandDto;
import com.erkvural.rentacar.business.requests.brand.CreateBrandRequest;
import com.erkvural.rentacar.business.requests.brand.DeleteBrandRequest;
import com.erkvural.rentacar.business.requests.brand.UpdateBrandRequest;
import com.erkvural.rentacar.core.utilities.mapping.ModelMapperService;
import com.erkvural.rentacar.dataAccess.abstracts.BrandDao;
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
    public List<ListBrandDto> getAll() {
        List<Brand> result = brandDao.findAll();
        List<ListBrandDto> response = result.stream()
                .map(brand -> modelMapperService.forDto()
                        .map(brand, ListBrandDto.class))
                .collect(Collectors.toList());

        return response;
    }

    @Override
    public ListBrandDto getById(int id) {
        Brand brand = brandDao.getById(id);
        ListBrandDto response = modelMapperService.forDto().map(brand, ListBrandDto.class);

        return response;
    }

    private boolean checkBrandNameExist(Brand brand) {
        return Objects.nonNull(brandDao.getBrandByName(brand.getName()));
    }

    @Override
    public void add(CreateBrandRequest createBrandRequest) {
        Brand brand = modelMapperService.forRequest().map(createBrandRequest, Brand.class);
        if (!checkBrandNameExist(brand)) {
            brandDao.save(brand);
        }
    }

    @Override
    public void update(UpdateBrandRequest updateBrandRequest) {
        Brand brand = this.modelMapperService.forRequest().map(updateBrandRequest, Brand.class);

        if(checkBrandIdExist(brand)) {
            this.brandDao.save(brand);
        }
    }

    @Override
    public void delete(DeleteBrandRequest deleteBrandRequest) {
        Brand brand = this.modelMapperService.forRequest().map(deleteBrandRequest, Brand.class);

        if(checkBrandIdExist(brand)) {
            this.brandDao.deleteById(brand.getId());
    }
    }

    private boolean checkBrandIdExist(Brand brand) {

        if (this.brandDao.getBrandById(brand.getId()) != null) {
            return true;
        }
        System.out.println("Can't find brand by Id to operate");
        return false;
    }
}
