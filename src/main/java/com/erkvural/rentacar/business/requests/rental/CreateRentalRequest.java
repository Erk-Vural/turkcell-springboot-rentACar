package com.erkvural.rentacar.business.requests.rental;

import com.erkvural.rentacar.entities.concretes.Car;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateRentalRequest {

    private LocalDate rentDate;

    private LocalDate returnDate;

    private int customerId;

    private int carId;
}
