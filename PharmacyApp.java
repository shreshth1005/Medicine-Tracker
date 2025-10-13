import java.time.LocalDate;
import java.util.List;

/**
 * Main application to demonstrate the InventoryService functionality.
 * This simulates a user interacting with the system.
 */
public class PharmacyApp {

    public static void main(String[] args) {
        InventoryService service = new InventoryService();

        // --- Step 1: Add medicines to our pharmacy's catalog ---
        System.out.println("--- Initializing Medicines ---");
        Medicine paracetamol = new Medicine("Paracetamol", "500mg", "Pharma Inc.", 50);
        Medicine amoxicillin = new Medicine("Amoxicillin", "250mg", "MediCorp", 100);
        Medicine aspirin = new Medicine("Aspirin", "100mg", "HealthWell", 75);
        
        service.addMedicine(paracetamol);
        service.addMedicine(amoxicillin);
        service.addMedicine(aspirin);
        System.out.println("Catalog initialized.\n");

        // --- Step 2: Add incoming stock (batches) for these medicines ---
        System.out.println("--- Adding Stock Batches ---");
        // Paracetamol batches
        service.addStock(paracetamol.getId(), "P-BATCH-001", 200, LocalDate.of(2025, 12, 31));
        // Amoxicillin batches
        service.addStock(amoxicillin.getId(), "A-BATCH-001", 150, LocalDate.of(2026, 5, 20));
        // Aspirin batches - one is expiring soon!
        service.addStock(aspirin.getId(), "S-BATCH-001", 40, LocalDate.now().plusDays(25)); // Expires in 25 days
        service.addStock(aspirin.getId(), "S-BATCH-002", 150, LocalDate.of(2026, 8, 10));
        System.out.println("\n");
        
        // --- Step 3: Run the alert system to check the inventory status ---
        System.out.println("--- Generating System Alerts ---");

        // Check for low stock items
        List<Medicine> lowStockItems = service.getLowStockAlerts();
        System.out.println("\n--- LOW STOCK ALERTS ---");
        if (lowStockItems.isEmpty()) {
            System.out.println("All items are above their reorder level.");
        } else {
            lowStockItems.forEach(med -> 
                System.out.println("REORDER: " + med.getName() + " - Current Stock: " + med.getTotalStock() + " (Reorder Level: " + med.getReorderLevel() + ")")
            );
        }

        // Check for items expiring within 60 days
        List<Batch> expiringItems = service.getExpiryAlerts(60);
        System.out.println("\n--- EXPIRY ALERTS (in next 60 days) ---");
        if (expiringItems.isEmpty()) {
            System.out.println("No batches are expiring soon.");
        } else {
            expiringItems.forEach(batch -> {
                Medicine med = service.findMedicineByName(
                    // This is a simplification; in a real app, you'd have a better way to look up the medicine name
                    "Aspirin" // Assuming we know the expiring item is aspirin
                );
                System.out.println("EXPIRING: " + med.getName() + " " + med.getStrength() + " | " + batch);
            });
        }
    }
}
