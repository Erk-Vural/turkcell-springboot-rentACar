package com.erkvural.rentacar.dataAccess.abstracts;

import com.erkvural.rentacar.entities.concretes.Brand;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BrandDao extends JpaRepository<Brand, Integer> {

    Brand getBrandByBrandId(int brandId);

    Brand getBrandByBrandName(String name);
}
