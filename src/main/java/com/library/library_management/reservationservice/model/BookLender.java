package com.library.library_management.reservationservice.model;

import com.library.library_management.bookservoice.models.BookItem;
import com.library.library_management.memberservice.model.Member;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Data // Generates getters, setters, toString, equals, and hashCode
@NoArgsConstructor // Generates a no-args constructor
@AllArgsConstructor // Generates an all-args constructor
@Builder
public class BookLender {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne
    @JoinColumn(name = "book_id")
    private BookItem book;

    private LocalDate lendDate; // Date when the book was lent

    private LocalDate returnDate; // Date when the book is due to be returned


    @OneToOne(mappedBy = "bookLender", cascade = CascadeType.ALL)
    private Fine fine; // Fine associated with this lending

    // Getters and setters
}

