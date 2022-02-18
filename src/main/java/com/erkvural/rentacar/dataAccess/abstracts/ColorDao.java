package com.erkvural.rentacar.dataAccess.abstracts;

import com.erkvural.rentacar.entities.concretes.Color;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ColorDao extends JpaRepository<Color, Integer> {

    Color getColorById(int colorId);

    Color getColorByName(String name);
}
