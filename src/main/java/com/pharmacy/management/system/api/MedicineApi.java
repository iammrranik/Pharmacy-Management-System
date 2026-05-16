package com.pharmacy.management.system.api;

import com.pharmacy.management.system.domain.Medicine;
import com.pharmacy.management.system.service.implementation.MedicineService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/medicines")
public class MedicineApi {

    private final MedicineService medicineService;

    public MedicineApi(MedicineService medicineService) {
        this.medicineService = medicineService;
    }

    @PostMapping
    public ResponseEntity<Map<String, Object>> save(@Valid @RequestBody Medicine medicine) {
        System.out.println("[MedicineApi] POST /api/medicines - saving: " + medicine.getName());
        Medicine saved = medicineService.saveMedicine(medicine);
        return ResponseEntity.status(201).body(Map.of(
            "message", "Medicine created successfully",
            "data", saved
        ));
    }

    @GetMapping
    public ResponseEntity<List<Medicine>> findAll() {
        System.out.println("[MedicineApi] GET /api/medicines - finding all");
        return ResponseEntity.ok(medicineService.findAllMedicines());
    }

    @GetMapping("/page")
    public ResponseEntity<List<Medicine>> findAllByPage(@RequestParam int pageNo, @RequestParam int pageSize) {
        System.out.println("[MedicineApi] GET /api/medicines/page - page: " + pageNo + ", size: " + pageSize);
        return ResponseEntity.ok(medicineService.findAllMedicinesByPage(pageNo, pageSize));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable int id) {
        System.out.println("[MedicineApi] GET /api/medicines/" + id);
        var medicine = medicineService.findMedicineById(id);
        if (medicine.isPresent()) {
            return ResponseEntity.ok(medicine.get());
        }
        return ResponseEntity.status(404).body(Map.of("message", "Medicine not found with id: " + id));
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<List<Medicine>> findByName(@PathVariable String name) {
        System.out.println("[MedicineApi] GET /api/medicines/name/" + name);
        return ResponseEntity.ok(medicineService.findMedicinesByName(name));
    }

    @GetMapping("/category/{category}")
    public ResponseEntity<List<Medicine>> findByCategory(@PathVariable String category) {
        System.out.println("[MedicineApi] GET /api/medicines/category/" + category);
        return ResponseEntity.ok(medicineService.findMedicinesByCategory(category));
    }

    @GetMapping("/supplier/{supplierId}")
    public ResponseEntity<List<Medicine>> findBySupplierId(@PathVariable int supplierId) {
        System.out.println("[MedicineApi] GET /api/medicines/supplier/" + supplierId);
        return ResponseEntity.ok(medicineService.findMedicinesBySupplierId(supplierId));
    }

    @GetMapping("/batch/{batchId}")
    public ResponseEntity<List<Medicine>> findByBatchId(@PathVariable String batchId) {
        System.out.println("[MedicineApi] GET /api/medicines/batch/" + batchId);
        return ResponseEntity.ok(medicineService.findMedicinesByBatchId(batchId));
    }

    @GetMapping("/low-stock")
    public ResponseEntity<List<Medicine>> findLowStock(@RequestParam int quantity) {
        System.out.println("[MedicineApi] GET /api/medicines/low-stock?quantity=" + quantity);
        return ResponseEntity.ok(medicineService.findLowStockMedicines(quantity));
    }

    @GetMapping("/expired")
    public ResponseEntity<List<Medicine>> findExpired() {
        System.out.println("[MedicineApi] GET /api/medicines/expired");
        return ResponseEntity.ok(medicineService.findExpiredMedicines());
    }

    @GetMapping("/expiring-within")
    public ResponseEntity<List<Medicine>> findExpiringWithin(@RequestParam int days) {
        System.out.println("[MedicineApi] GET /api/medicines/expiring-within?days=" + days);
        return ResponseEntity.ok(medicineService.findExpiredMedicines(days));
    }

    @GetMapping("/count")
    public ResponseEntity<Map<String, Object>> count() {
        System.out.println("[MedicineApi] GET /api/medicines/count");
        return ResponseEntity.ok(Map.of("count", medicineService.countMedicines()));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Map<String, Object>> update(@PathVariable int id, @Valid @RequestBody Medicine medicine) {
        System.out.println("[MedicineApi] PUT /api/medicines/" + id + " - updating medicine");
        medicine.setId(id);
        Medicine updated = medicineService.updateMedicine(medicine);
        return ResponseEntity.ok(Map.of(
            "message", "Medicine updated successfully",
            "data", updated
        ));
    }

    @PutMapping("/quantity")
    public ResponseEntity<Map<String, String>> updateQuantity(@RequestParam int medicineId, @RequestParam int quantity) {
        System.out.println("[MedicineApi] PUT /api/medicines/quantity - id: " + medicineId + ", qty: " + quantity);
        medicineService.updateMedicineQuantity(medicineId, quantity);
        return ResponseEntity.ok(Map.of("message", "Medicine quantity updated successfully"));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, String>> delete(@PathVariable int id) {
        System.out.println("[MedicineApi] DELETE /api/medicines/" + id);
        medicineService.deleteMedicine(id);
        return ResponseEntity.ok(Map.of("message", "Medicine deleted successfully"));
    }
}
