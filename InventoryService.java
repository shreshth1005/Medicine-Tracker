import java.time.LocalDate;
import java.util.UUID;

/**
 * Represents a specific batch or shipment of a medicine.
 * This is where expiry dates and quantities are tracked.
 */
public class Batch {
    private final String batchId;
    private final String medicineId; // Links to the Medicine class
    private final String batchNumber;
    private int quantity;
    private final LocalDate expiryDate;
    private final LocalDate purchaseDate;

    public Batch(String medicineId, String batchNumber, int quantity, LocalDate expiryDate) {
        this.batchId = UUID.randomUUID().toString();
        this.medicineId = medicineId;
        this.batchNumber = batchNumber;
        this.quantity = quantity;
        this.expiryDate = expiryDate;
        this.purchaseDate = LocalDate.now(); // Set purchase date to today
    }

    // --- Getters ---
    public String getBatchId() { return batchId; }
    public String getMedicineId() { return medicineId; }
    public String getBatchNumber() { return batchNumber; }
    public int getQuantity() { return quantity; }
    public LocalDate getExpiryDate() { return expiryDate; }

    // --- Setters ---
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    
    @Override
    public String toString() {
        return "Batch #" + batchNumber + " | Qty: " + quantity + " | Expires: " + expiryDate;
    }
}
