package com.pharmacy.inventory_tracker;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import java.time.LocalDate;
import java.util.UUID;

@Entity
public class Batch {

    @Id
    private String id;
    private String medicineId; // Foreign key to the Medicine table
    private String batchNumber;
    private int quantity;
    private LocalDate expiryDate;

    public Batch() {}

    public Batch(String medicineId, String batchNumber, int quantity, LocalDate expiryDate) {
        this.id = UUID.randomUUID().toString();
        this.medicineId = medicineId;
        this.batchNumber = batchNumber;
        this.quantity = quantity;
        this.expiryDate = expiryDate;
    }
    
    // --- Getters remain the same ---
    public String getId() { return id; }
    public String getMedicineId() { return medicineId; }
    public String getBatchNumber() { return batchNumber; }
    public int getQuantity() { return quantity; }
    public LocalDate getExpiryDate() { return expiryDate; }
}