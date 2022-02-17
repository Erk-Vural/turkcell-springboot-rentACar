package com.erkvural.rentacar.business.abstracts;

import com.erkvural.rentacar.business.dtos.ListBrandDto;
import com.erkvural.rentacar.business.dtos.ListColorDto;
import com.erkvural.rentacar.business.requests.CreateBrandRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface BrandService {

    List<ListBrandDto> getAll();

    void add(CreateBrandRequest createBrandRequest);

    ListBrandDto getBrandByBrandId(int id);

}
