package com.pharmacy.inventory_tracker;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface BatchRepository extends JpaRepository<Batch, String> {
    // We can define custom query methods just by naming them correctly!
    List<Batch> findByMedicineId(String medicineId);
}
