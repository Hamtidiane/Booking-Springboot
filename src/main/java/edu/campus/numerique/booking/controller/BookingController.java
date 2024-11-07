package edu.campus.numerique.booking.controller;


import edu.campus.numerique.booking.Booking;
import edu.campus.numerique.booking.repository.BookingRepository;
import edu.campus.numerique.booking.service.BookingService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/booking")
@Tag(name = "Booking", description = "API pour la gestion des reservations")
public class BookingController {

    @Autowired
    private BookingRepository bookingRepository;
    @Autowired
    private BookingService bookingService;



    @Operation(summary = "Recupérer toutes les réservations", description = "Renvoie la liste de toutes les réservations")
    @ApiResponse(responseCode = "200", description = "Liste des réservations récupérée avec succès")
    @GetMapping
    public List<Booking> getAllBookings() {
        return bookingRepository.findAll();
    }

    @Operation(summary = "Recupérer une réservation par ID", description = "Renvoie les details d'une réservation spécifique par son ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Réservation trouvée"),
            @ApiResponse(responseCode = "404", description = "Réservation non trouvée")
    })
    @GetMapping("/{id}")
    public ResponseEntity<Optional<Booking>> getBookingById(@PathVariable Long id) {
        Optional<Booking> booking = bookingRepository.findById(id);
        return booking.isPresent() ? ResponseEntity.ok(booking) : ResponseEntity.notFound().build();
    }

    @Operation(summary = "Créer une nouvelle réservation", description = "Ajoute une nouvelle réservation dans la base de données")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Réservation créée avec succès"),
            @ApiResponse(responseCode = "400", description = "Requête invalide")
    })
    @PostMapping
    public ResponseEntity<Booking> createBooking(@RequestBody Booking booking) {
        return new ResponseEntity<>(bookingService.createBooking(booking), HttpStatus.CREATED);
    }


    @Operation(summary = "Mettre à jour une réservation existante", description = "Modifie les informations d'une réservation exisante par son ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Réservation mise à jour avec succès"),
            @ApiResponse(responseCode = "404", description = "Réservation non trouvée")
    })
    @PutMapping("/{id}")
    public ResponseEntity<Booking> updateBooking(@PathVariable Long id, @RequestBody Booking updatedbooking) {
        return bookingRepository.findById(id).map(booking -> {
            booking.setStartDate(updatedbooking.getStartDate());
            booking.setEndDate(updatedbooking.getEndDate());
            booking.setEstimatedMileage(updatedbooking.getEstimatedMileage());
            bookingRepository.save(booking);
            return ResponseEntity.ok(booking);
        }).orElseGet(() -> ResponseEntity.notFound().build());
    }


    @Operation(summary = "Supprimer une réservation par ID", description = "Supprime une réservation existant par son ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Réervation supprimée avec succès"),
            @ApiResponse(responseCode = "404", description = "Réservation non trouvée")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBooking(@PathVariable Long id) {
        if (bookingRepository.existsById(id)) {
            bookingRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }

    }
}