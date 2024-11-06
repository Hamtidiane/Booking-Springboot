package edu.campus.numerique.booking;


import com.fasterxml.jackson.annotation.JsonTypeId;
import lombok.Data;
import jakarta.persistence.*;

import lombok.Generated;

import java.time.LocalDate;
import java.time.LocalDateTime;


@Data//Lombok
@Entity//BDD
@Table(name = "booking"  )
public class Booking {

    @Id//lombok
    @GeneratedValue(strategy = GenerationType.IDENTITY)//lombok
    private long id;

    private LocalDate startDate;

    private LocalDate endDate;

    private long vehicleId;

    private long userId;

    private float estimatedMileage;



}
