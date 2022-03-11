package com.erkvural.rentacar.dataaccess.abstracts;

import com.erkvural.rentacar.entities.concretes.AdditionalService;
import com.erkvural.rentacar.entities.concretes.Rental;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AdditionalServiceDao extends JpaRepository<AdditionalService, Integer> {
    AdditionalService getAdditionalServiceById(int id);

    List<AdditionalService> getAdditionalServiceByRentalId(Rental rental);
}
