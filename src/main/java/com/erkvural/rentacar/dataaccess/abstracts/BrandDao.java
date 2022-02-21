package com.erkvural.rentacar.dataaccess.abstracts;

import com.erkvural.rentacar.entities.concretes.Brand;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BrandDao extends JpaRepository<Brand, Integer> {

    Brand getBrandById(int id);

    Brand getBrandByName(String name);

    boolean existsBrandByName(String name);
}
