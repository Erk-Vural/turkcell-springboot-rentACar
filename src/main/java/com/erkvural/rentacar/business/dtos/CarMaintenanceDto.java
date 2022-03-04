package com.erkvural.rentacar.business.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collector;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CarMaintenanceDto {
    private int id;

    private String description;

    private LocalDate returnDate;

    private int carId;

}
