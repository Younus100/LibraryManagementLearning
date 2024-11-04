package com.library.library_management.bookservoice.repository;

import com.library.library_management.bookservoice.models.BookItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookItemRepository extends JpaRepository<BookItem, Long>{
}
