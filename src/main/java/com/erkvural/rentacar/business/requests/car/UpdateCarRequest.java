package com.erkvural.rentacar.business.requests.car;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateCarRequest {
    private int id;

    private double dailyPrice;

    private int modelYear;

    private String description;

    private int brandId;

    private int colorId;
}
