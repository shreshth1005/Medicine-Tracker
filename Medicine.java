package com.pharmacy.inventorytracker;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Transient;
import java.util.UUID;

@Entity // Marks this class as a database table
public class Medicine {

    @Id // Marks this field as the primary key
    private String id;
    private String name;
    private String strength;
    private String manufacturer;
    private int reorderLevel;

    @Transient // This field will not be saved to the database; it's calculated
    private int totalStock;

    public Medicine() {
        // Default constructor required by JPA
    }

    public Medicine(String name, String strength, String manufacturer, int reorderLevel) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.strength = strength;
        this.manufacturer = manufacturer;
        this.reorderLevel = reorderLevel;
    }
    
    // --- Getters and Setters remain the same ---
    public String getId() { return id; }
    public String getName() { return name; }
    public String getStrength() { return strength; }
    public String getManufacturer() { return manufacturer; }
    public int getReorderLevel() { return reorderLevel; }
    public int getTotalStock() { return totalStock; }
    public void setTotalStock(int totalStock) { this.totalStock = totalStock; }
}
