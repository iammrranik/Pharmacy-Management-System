package com.pharmacy.management.system.service.implementation;

import com.pharmacy.management.system.domain.Medicine;
import com.pharmacy.management.system.repository.implementation.MedicineRepository;
import com.pharmacy.management.system.service.IMedicineService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class MedicineService implements IMedicineService {
    private final MedicineRepository medicineRepository;

    public MedicineService(MedicineRepository medicineRepository) {
        this.medicineRepository = medicineRepository;
    }

    @Override
    public Medicine saveMedicine(Medicine medicine) {
        System.out.println("[MedicineService] saveMedicine called for: " + medicine.getName());
        medicineRepository.save(medicine);
        return medicine;
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Medicine> findMedicineById(int id) {
        System.out.println("[MedicineService] findMedicineById called for id: " + id);
        return medicineRepository.findById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Medicine> findMedicinesByName(String name) {
        System.out.println("[MedicineService] findMedicinesByName called for name: " + name);
        return medicineRepository.findByName(name);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Medicine> findMedicinesByCategory(String category) {
        System.out.println("[MedicineService] findMedicinesByCategory called for category: " + category);
        return medicineRepository.findByCategory(category);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Medicine> findMedicinesBySupplierId(int supplierId) {
        System.out.println("[MedicineService] findMedicinesBySupplierId called for supplierId: " + supplierId);
        return medicineRepository.findBySupplierId(supplierId);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Medicine> findMedicinesByBatchId(int batchId) {
        System.out.println("[MedicineService] findMedicinesByBatchId called for batchId: " + batchId);
        return medicineRepository.findByBatchId(batchId);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Medicine> findAllMedicines() {
        System.out.println("[MedicineService] findAllMedicines called");
        return medicineRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Medicine> findAllMedicinesByPage(int pageNo, int pageSize) {
        System.out.println("[MedicineService] findAllMedicinesByPage called - page: " + pageNo + ", size: " + pageSize);
        return medicineRepository.findAllByPage(pageNo, pageSize);
    }

    @Override
    @Transactional(readOnly = true)
    public int countMedicines() {
        System.out.println("[MedicineService] countMedicines called");
        return medicineRepository.count();
    }

    @Override
    public Medicine updateMedicine(Medicine medicine) {
        System.out.println("[MedicineService] updateMedicine called for id: " + medicine.getId());
        medicineRepository.update(medicine);
        return medicine;
    }

    @Override
    public void deleteMedicine(int id) {
        System.out.println("[MedicineService] deleteMedicine called for id: " + id);
        medicineRepository.delete(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Medicine> findLowStockMedicines(int quantity) {
        System.out.println("[MedicineService] findLowStockMedicines called with quantity: " + quantity);
        return medicineRepository.findLowStockMedicines(quantity);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Medicine> findExpiredMedicines() {
        System.out.println("[MedicineService] findExpiredMedicines called");
        return medicineRepository.findExpiredMedicines();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Medicine> findExpiredMedicines(int days) {
        System.out.println("[MedicineService] findExpiredMedicines called for days: " + days);
        return medicineRepository.findExpiredMedicines(days);
    }

    @Override
    public void updateMedicineQuantity(int medicineId, int quantity) {
        System.out.println("[MedicineService] updateMedicineQuantity called for id: " + medicineId + ", quantity: " + quantity);
        medicineRepository.updateMedicineQuantity(medicineId, quantity);
    }
}
