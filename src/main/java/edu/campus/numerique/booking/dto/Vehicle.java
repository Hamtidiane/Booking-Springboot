package edu.campus.numerique.booking.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor

public class Vehicle {

     private String type;
    private int tax_horses;
    private boolean availability;
    private double basePrice;
    private float mileagePrice;
    private Integer moteurcm3;
    private Integer volumecm3;


}
