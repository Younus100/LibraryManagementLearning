package com.library.library_management.userservice;

import com.library.library_management.bookservoice.service.BookService;
import com.library.library_management.bookservoice.models.Book;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private final BookService bookService;

    public UserController(BookService bookService) {
        this.bookService = bookService;
    }

    // Endpoint to get all books with pagination
    @GetMapping("/books")
    @Operation(summary = "Get all books", description = "Returns a paginated list of all books")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved list of books"),
            @ApiResponse(responseCode = "404", description = "Books not found")
    })
    public ResponseEntity<Page<Book>> getAllBooks(
            @Parameter(description = "Page number to retrieve", example = "0") @RequestParam(defaultValue = "0") int page,
            @Parameter(description = "Number of books per page", example = "10") @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Book> books = bookService.findAllBooks(pageable);
        return new ResponseEntity<>(books, HttpStatus.OK);
    }

    // Endpoint to get books by author with pagination
    @GetMapping("/books/author/{authorId}")
    @Operation(summary = "Get books by author", description = "Returns a paginated list of books by a specific author")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved list of books by author"),
            @ApiResponse(responseCode = "404", description = "Books not found for the given author ID")
    })
    public ResponseEntity<Page<Book>> getBooksByAuthor(
            @Parameter(description = "ID of the author to filter books", required = true) @PathVariable Long authorId,
            @Parameter(description = "Page number to retrieve", example = "0") @RequestParam(defaultValue = "0") int page,
            @Parameter(description = "Number of books per page", example = "10") @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Book> books = bookService.findBooksByAuthor(authorId, pageable);
        return new ResponseEntity<>(books, HttpStatus.OK);
    }
}
