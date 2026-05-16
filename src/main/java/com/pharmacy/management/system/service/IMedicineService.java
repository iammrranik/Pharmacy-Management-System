package com.pharmacy.management.system.service;

import com.pharmacy.management.system.domain.Medicine;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface IMedicineService {
    Medicine saveMedicine(Medicine medicine);
    Optional<Medicine> findMedicineById(int id);
    List<Medicine> findMedicinesByName(String name);
    List<Medicine> findMedicinesByCategory(String category);
    List<Medicine> findMedicinesBySupplierId(int supplierId);
    List<Medicine> findMedicinesByBatchId(String batchId);
    List<Medicine> findAllMedicines();
    List<Medicine> findAllMedicinesByPage(int pageNo, int pageSize);
    int countMedicines();
    Medicine updateMedicine(Medicine medicine);
    void deleteMedicine(int id);
    List<Medicine> findLowStockMedicines(int quantity);
    List<Medicine> findExpiredMedicines();
    List<Medicine> findExpiredMedicines(int days);
    void updateMedicineQuantity(int medicineId, int quantity);
}
