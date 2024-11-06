package edu.campus.numerique.booking;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor

public class Vehicle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String type;
    private int tax_horses;
    private boolean availability;
    private double basePrice;
    private float mileagePrice;
    private Integer moteurcm3;
    private Integer volumecm3;

    public String getType() {
        return null;
    }
}
