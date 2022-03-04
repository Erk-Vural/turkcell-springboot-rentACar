package com.erkvural.rentacar.business.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RentalDto {
    private int id;

    private LocalDate rentalDate;

    private LocalDate returnDate;

    private int customerId;

    private int carId;
}
