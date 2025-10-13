package com.pharmacy.inventory_tracker;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
public class InventoryController {

    private final InventoryService inventoryService;

    
    public InventoryController(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }
    
    // --- NEW ENDPOINT for creating a medicine ---
    @PostMapping("/api/medicines")
    public Medicine createMedicine(@RequestBody CreateMedicineRequest request) {
        return inventoryService.createNewMedicine(request);
    }
    
    // --- NEW ENDPOINT for deleting a medicine ---
    // Example URL: /api/medicines/1234-abcd-5678
    @DeleteMapping("/api/medicines/{id}")
    public ResponseEntity<Void> deleteMedicine(@PathVariable String id) {
        inventoryService.deleteMedicine(id);
        return ResponseEntity.ok().build(); // Return a success status
    }

    @GetMapping("/api/alerts/low-stock")
    public List<Medicine> getLowStockAlerts() {
        return inventoryService.getLowStockAlerts();
    }

    @GetMapping("/api/alerts/expiring")
    public List<Batch> getExpiryAlerts() {
        return inventoryService.getExpiryAlerts(60);
    }
    
    @GetMapping("/api/medicines")
    public List<Medicine> getAllMedicines() {
        return inventoryService.getAllMedicines();
    }

    @PostMapping("/api/stock")
    public void addStock(@RequestBody AddStockRequest request) {
        inventoryService.addStock(
            request.getMedicineId(),
            request.getBatchNumber(),
            request.getQuantity(),
            request.getExpiryDate()
        );
    }
}
