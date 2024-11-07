package edu.campus.numerique.booking.dto;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {


    private int id;
    private String name;
    private String firstName;
    private LocalDate birthDate;
    private String licenseNumber;
    private int licenseObtYear;

    public int getAge(){
        return LocalDate.now().getYear() - birthDate.getYear();
    }

}