package com.library.library_management.adminservice.repository;

import com.library.library_management.adminservice.model.Librarien;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LibrarianRepository extends JpaRepository<Librarien,Long> {
    Optional<Librarien> findById(Long id);
}
