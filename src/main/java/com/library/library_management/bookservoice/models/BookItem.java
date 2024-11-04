package com.library.library_management.bookservoice.models;

import com.library.library_management.reservationservice.model.BookLender;
import com.library.library_management.reservationservice.model.BookReservation;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Entity
@PrimaryKeyJoinColumn(name = "book_id")
@Data // Generates getters, setters, toString, equals, and hashCode
@NoArgsConstructor // Generates a no-args constructor
@AllArgsConstructor // Generates an all-args constructor
public class BookItem extends Book {
    @OneToOne // One BookItem has one Location
    @JoinColumn(name = "location_id", nullable = false)
    private Location location; // Reference to Location entity

    @OneToMany(mappedBy = "book", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<BookReservation> bookReservations;

    // One-to-Many relationship with BookLender
    @OneToMany(mappedBy = "book", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<BookLender> bookLenders;

}
