package com.erkvural.rentacar.business.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CarMaintenanceDto {
    private int id;

    private String description;

    private LocalDate returnDate;

    private int carId;

}
