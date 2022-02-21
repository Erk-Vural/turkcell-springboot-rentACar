package com.erkvural.rentacar.business.abstracts;

import com.erkvural.rentacar.business.dtos.BrandDto;
import com.erkvural.rentacar.business.requests.brand.CreateBrandRequest;
import com.erkvural.rentacar.business.requests.brand.DeleteBrandRequest;
import com.erkvural.rentacar.business.requests.brand.UpdateBrandRequest;
import com.erkvural.rentacar.core.utilities.results.DataResult;
import com.erkvural.rentacar.core.utilities.results.Result;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface BrandService {

    DataResult<List<BrandDto>> getAll();

    DataResult<BrandDto> getById(int id);

    Result add(CreateBrandRequest createBrandRequest);

    Result update(UpdateBrandRequest updateBrandRequest);

    Result delete(DeleteBrandRequest deleteBrandRequest);

}
