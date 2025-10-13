package com.pharmacy.inventory_tracker;

import org.springframework.stereotype.Service;
import jakarta.annotation.PostConstruct;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
    
@Service
public class InventoryService {

    private final MedicineRepository medicineRepository;
    private final BatchRepository batchRepository;

    public InventoryService(MedicineRepository medicineRepository, BatchRepository batchRepository) {
        this.medicineRepository = medicineRepository;
        this.batchRepository = batchRepository;
    }

    @PostConstruct
    private void init() {
        // This will only run if the database is empty, preventing duplicate data on restart.
        if (medicineRepository.count() == 0) {
            System.out.println("Database is empty. Initializing with sample data...");
            // We need to create a dummy request object to use the createNewMedicine method
            CreateMedicineRequest aspirinRequest = new CreateMedicineRequest();
            aspirinRequest.setName("Aspirin");
            aspirinRequest.setStrength("100mg");
            aspirinRequest.setManufacturer("Bayer");
            aspirinRequest.setReorderLevel(50);
            Medicine aspirin = createNewMedicine(aspirinRequest);
            addStock(aspirin.getId(), "ASP-A01", 30, LocalDate.now().plusMonths(12));

            CreateMedicineRequest ibuprofenRequest = new CreateMedicineRequest();
            ibuprofenRequest.setName("Ibuprofen");
            ibuprofenRequest.setStrength("200mg");
            ibuprofenRequest.setManufacturer("Advil");
            ibuprofenRequest.setReorderLevel(100);
            Medicine ibuprofen = createNewMedicine(ibuprofenRequest);
            addStock(ibuprofen.getId(), "IBU-B01", 150, LocalDate.now().plusDays(45));
            addStock(ibuprofen.getId(), "IBU-B02", 200, LocalDate.now().plusMonths(18));
        }
    }
    
    public Medicine createNewMedicine(CreateMedicineRequest request) {
        Medicine medicine = new Medicine(request.getName(), request.getStrength(), request.getManufacturer(), request.getReorderLevel());
        return medicineRepository.save(medicine);
    }
    
    public void deleteMedicine(String medicineId) {
        List<Batch> batchesToDelete = batchRepository.findByMedicineId(medicineId);
        batchRepository.deleteAll(batchesToDelete);
        medicineRepository.deleteById(medicineId);
    }

    public void addStock(String medicineId, String batchNumber, int quantity, LocalDate expiryDate) {
        Batch batch = new Batch(medicineId, batchNumber, quantity, expiryDate);
        batchRepository.save(batch);
    }

    private int calculateTotalStock(String medicineId) {
        return batchRepository.findByMedicineId(medicineId).stream()
                .mapToInt(Batch::getQuantity)
                .sum();
    }

    public List<Medicine> getAllMedicines() {
        List<Medicine> allMedicines = medicineRepository.findAll();
        // Calculate total stock for each medicine before returning
        allMedicines.forEach(med -> med.setTotalStock(calculateTotalStock(med.getId())));
        return allMedicines.stream()
            .sorted((m1, m2) -> m1.getName().compareToIgnoreCase(m2.getName()))
            .collect(Collectors.toList());
    }

    public List<Medicine> getLowStockAlerts() {
        List<Medicine> allMedicines = getAllMedicines(); // This already calculates stock
        return allMedicines.stream()
                .filter(m -> m.getTotalStock() <= m.getReorderLevel())
                .collect(Collectors.toList());
    }

    public List<Batch> getExpiryAlerts(int days) {
        LocalDate expiryLimit = LocalDate.now().plusDays(days);
        return batchRepository.findAll().stream()
                .filter(b -> !b.getExpiryDate().isAfter(expiryLimit))
                .collect(Collectors.toList());
    }
}
