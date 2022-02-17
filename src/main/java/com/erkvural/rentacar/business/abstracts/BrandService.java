package com.erkvural.rentacar.business.abstracts;

import com.erkvural.rentacar.business.dtos.ListBrandDto;
import com.erkvural.rentacar.business.requests.brand.CreateBrandRequest;
import com.erkvural.rentacar.business.requests.brand.DeleteBrandRequest;
import com.erkvural.rentacar.business.requests.brand.UpdateBrandRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface BrandService {

    List<ListBrandDto> getAll();

    ListBrandDto getById(int id);

    void add(CreateBrandRequest createBrandRequest);

    void update(UpdateBrandRequest updateBrandRequest);

    void delete(DeleteBrandRequest deleteBrandRequest);

}
