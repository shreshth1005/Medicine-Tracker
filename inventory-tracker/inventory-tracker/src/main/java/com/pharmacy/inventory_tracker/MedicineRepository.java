package com.pharmacy.inventory_tracker;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MedicineRepository extends JpaRepository<Medicine, String> {
    // Spring Data JPA will automatically provide methods like save(), findAll(), findById(), deleteById(), etc.
}
