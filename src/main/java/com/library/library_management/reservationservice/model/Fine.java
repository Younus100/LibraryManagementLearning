package com.library.library_management.reservationservice.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data // Generates getters, setters, toString, equals, and hashCode
@NoArgsConstructor // Generates a no-args constructor
@AllArgsConstructor // Generates an all-args constructor
@Builder
public class Fine {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "lender_id")
    private BookLender bookLender;

    private double amount; // Amount of the fine
    private boolean paid; // Indicates if the fine has been paid
//    @OneToOne(mappedBy = "fine", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//    private Payment payment; // Payment details for this fine

    // Getters and setters
}