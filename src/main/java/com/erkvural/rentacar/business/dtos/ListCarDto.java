package com.erkvural.rentacar.business.dtos;

import com.erkvural.rentacar.entities.concretes.Brand;
import com.erkvural.rentacar.entities.concretes.Color;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ListCarDto {

    private int carId;

    private double dailyPrice;

    private int modelYear;

    private String description;

    private String brandName;

    private String colorName;
}
