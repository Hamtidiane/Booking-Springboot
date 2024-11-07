package edu.campus.numerique.booking.service;

import edu.campus.numerique.booking.Booking;
import edu.campus.numerique.booking.dto.Vehicle;
import edu.campus.numerique.booking.dto.User;
import edu.campus.numerique.booking.repository.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.client.HttpClientErrorException;

@Service
public class BookingService {

    private final BookingRepository bookingRepository;

    @Autowired
    private RestTemplate restTemplate;  // Injection du RestTemplate pour appeler le microservice Vehicle
    private static final String USER_SERVICE_URL = "http://192.168.1.248:8080/api/users"; // URL du microservice User
    private static final String VEHICLE_SERVICE_URL = "http://localhost:8085/vehicles";
    // URL du microservice Vehicle

    public BookingService(BookingRepository bookingRepository) {
        this.bookingRepository = bookingRepository;
    }

    // Méthode pour créer une réservation avec l'objet Booking directement
    public Booking createBooking(Booking booking) {
        // Appel du microservice Vehicle pour récupérer les informations du véhicule
        Vehicle vehicle = getVehicleById(booking.getVehicleId());

        User user = getUserById(booking.getUserId());
        // Calculer le prix en utilisant les informations du véhicule
        double price = calculatePrice(vehicle, booking.getEstimatedMileage());
        booking.setPrice(price);
        booking.setUserId(user.getId());

        return bookingRepository.save(booking);
    }

    // Méthode pour récupérer le véhicule par son ID en appelant le microservice Vehicle
    private Vehicle getVehicleById(Long vehicleId) {
        try {
            // Effectuer une requête GET pour récupérer le véhicule par son ID
            return restTemplate.getForObject(VEHICLE_SERVICE_URL + "/" + vehicleId, Vehicle.class);
        } catch (HttpClientErrorException e) {
            // Gérer les erreurs liées à la communication avec le service Vehicle
            throw new IllegalArgumentException("Erreur lors de la récupération du véhicule: " + e.getMessage());
        }
    }

    private User getUserById(Long userId) {
        try {
            return restTemplate.getForObject(USER_SERVICE_URL + "/" + userId, User.class);
        } catch (HttpClientErrorException e) {
            throw new IllegalArgumentException("Erreur lors de la récupération de l'utilisateur: " + e.getMessage());
        }
    }

    // Méthode pour calculer le prix en fonction du type de véhicule et du kilométrage estimé
    public float calculatePrice(Vehicle vehicle, float estimatedMileage) {
        float price;
        if (vehicle.getType().equals("Car")) {
            price = (float) (vehicle.getBasePrice() + vehicle.getMileagePrice() * estimatedMileage);
        } else if (vehicle.getType().equals("Motorcycle")) {
            price = (float) (vehicle.getBasePrice() + vehicle.getMoteurcm3() * 0.001 * vehicle.getMileagePrice() * estimatedMileage);
        } else if (vehicle.getType().equals("UtilityVehicle")) {
            price = (float) (vehicle.getBasePrice() + vehicle.getVolumecm3() * 0.005 * vehicle.getMileagePrice() * estimatedMileage);
        } else {
            throw new IllegalArgumentException("Type de véhicule inconnu : " + vehicle.getType());
        }
        return price;
    }
}
