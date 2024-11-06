package edu.campus.numerique.booking;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookingRequest {

    private String name;
    private String lastName;
    private LocalDate birthDate;
    private String licenseNumber;
    private int licenseObtYear;
    private Long vehicleId;
    private LocalDate startDate;
    private LocalDate endDate;
    private float estimatedMileage;


}
