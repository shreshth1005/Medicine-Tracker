package com.pharmacy.inventory_tracker;

// This class is a simple "Data Transfer Object" (DTO). Its only job is to
// hold the information from the "Create New Medicine" form so it can be sent
// as a single, clean JSON object from the frontend to the backend.

public class CreateMedicineRequest {

    private String name;
    private String strength;
    private String manufacturer;
    private int reorderLevel;

    // Getters
    public String getName() {
        return name;
    }

    public String getStrength() {
        return strength;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public int getReorderLevel() {
        return reorderLevel;
    }

    // Setters
    public void setName(String name) {
        this.name = name;
    }

    public void setStrength(String strength) {
        this.strength = strength;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public void setReorderLevel(int reorderLevel) {
        this.reorderLevel = reorderLevel;
    }
}