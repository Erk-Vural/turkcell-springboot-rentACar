package com.erkvural.rentacar.entities.concretes;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.bytebuddy.dynamic.loading.InjectionClassLoader;
import org.springframework.boot.context.properties.bind.DefaultValue;

import javax.persistence.*;
import java.time.LocalDate;

import static ch.qos.logback.core.joran.action.ActionConst.NULL;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "rentals")
@Entity
public class Rentals {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "rent_date")
    private LocalDate rentDate;

    @Column(name = "return_date")
    private LocalDate returnDate = null;

    @Column(name="customer_id")
    private int customerId;

    @ManyToOne()
    @JoinColumn(name = "car_id")
    private Car carId;

}
