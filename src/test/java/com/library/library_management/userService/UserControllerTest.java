package com.library.library_management.userService;

import com.library.library_management.bookservoice.models.Book;
import com.library.library_management.bookservoice.service.BookService;
import com.library.library_management.userservice.UserController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;


@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTest {
    private UserController userController;
    private BookService bookService;

    @BeforeEach
    void setUp() {
        bookService = Mockito.mock(BookService.class);
        userController = new UserController(bookService);
    }

    @Test
    void getAllBooks_ReturnsBooks() {
        // Arrange
        Pageable pageable = PageRequest.of(0, 10);
        Book book1 = new Book(1L, "Book Title 1", null);
        Book book2 = new Book(2L, "Book Title 2", null);
        List<Book> bookList = Arrays.asList(book1, book2);
        Page<Book> bookPage = new PageImpl<>(bookList, pageable, bookList.size());
        when(bookService.findAllBooks(ArgumentMatchers.any(Pageable.class))).thenReturn(bookPage);

        // Act
        ResponseEntity<Page<Book>> response = userController.getAllBooks(0, 10);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(2, response.getBody().getContent().size());
        assertEquals("Book Title 1", response.getBody().getContent().get(0).getTitle());
    }

    @Test
    void getBooksByAuthor_ReturnsBooksByAuthor() {
        // Arrange
        Long authorId = 1L;
        Pageable pageable = PageRequest.of(0, 10);
        Book book1 = new Book(1L, "Book Title 1", null);
        Book book2 = new Book(2L, "Book Title 2", null);
        List<Book> bookList = Arrays.asList(book1, book2);
        Page<Book> bookPage = new PageImpl<>(bookList, pageable, bookList.size());
        when(bookService.findBooksByAuthor(authorId, pageable)).thenReturn(bookPage);

        // Act
        ResponseEntity<Page<Book>> response = userController.getBooksByAuthor(authorId, 0, 10);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(2, response.getBody().getContent().size());
        assertEquals("Book Title 1", response.getBody().getContent().get(0).getTitle());
    }
}
