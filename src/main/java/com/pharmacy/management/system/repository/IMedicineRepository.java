package com.pharmacy.management.system.repository;

import com.pharmacy.management.system.domain.Medicine;

import java.util.List;
import java.util.Optional;

public interface IMedicineRepository {
    int save(Medicine medicine);
    Optional<Medicine> findById(int id);
    List<Medicine> findByName(String name);
    List<Medicine> findByCategory(String category);
    List<Medicine> findBySupplierId(int supplierId);
    List<Medicine> findByBatchId(int batchId);
    List<Medicine> findAll();
    List<Medicine> findAllByPage(int pageNo, int pageSize);
    int count();
    int update(Medicine medicine);
    int delete(int id);
    int delete(Medicine medicine);
    List<Medicine> findLowStockMedicines(int quantity);
    List<Medicine> findExpiredMedicines();
    List<Medicine> findExpiredMedicines(int days);

}
