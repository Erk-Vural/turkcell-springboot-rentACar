package com.erkvural.rentacar.dataaccess.abstracts;

import com.erkvural.rentacar.entities.concretes.Color;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ColorDao extends JpaRepository<Color, Integer> {

    Color getColorById(int id);

    Color getColorByName(String name);
}
