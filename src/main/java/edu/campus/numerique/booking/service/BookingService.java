package edu.campus.numerique.booking.service;

import edu.campus.numerique.booking.Booking;
import edu.campus.numerique.booking.BookingRequest;
import edu.campus.numerique.booking.Vehicle;
import io.micrometer.observation.ObservationFilter;

import java.util.ArrayList;
import java.util.List;


public class BookingService {

    public  Booking createBooking (BookingRequest request, Vehicle vehicle, float estimatedPrice) {
        // Logique de création de réservation
        return new Booking();
    }

    public float calculatePrice(Vehicle vehicle, float estimatedKm) {
        float price;
        if (vehicle.getType().equals("Car")) {
            price = (float) (vehicle.getBasePrice() + vehicle.getMileagePrice() * estimatedKm);
        } else if (vehicle.getType().equals("Motorcycle")) {
            price = (float) (vehicle.getBasePrice() + vehicle.getMoteurcm3() * 0.001 * vehicle.getMileagePrice() * estimatedKm);
        } else if (vehicle.getType().equals("UtilityVehicle")) {  // Utilisez else if ici
            price = (float) (vehicle.getBasePrice() + vehicle.getVolumecm3() * 0.005 * vehicle.getMileagePrice() * estimatedKm);
        } else {
            throw new IllegalArgumentException("Type de véhicule inconnu : " + vehicle.getType());
        }
        return price;
    }

}



