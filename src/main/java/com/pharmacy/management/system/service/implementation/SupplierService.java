package com.pharmacy.management.system.service.implementation;

import com.pharmacy.management.system.domain.Supplier;
import com.pharmacy.management.system.repository.implementation.SupplierRepository;
import com.pharmacy.management.system.service.ISupplierService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class SupplierService implements ISupplierService {
    private final SupplierRepository supplierRepository;

    public SupplierService(SupplierRepository supplierRepository) {
        this.supplierRepository = supplierRepository;
    }

    @Override
    @Transactional
    public Supplier saveSupplier(Supplier supplier) {
        if (supplier.getCreatedDate() == null) {
            supplier.setCreatedDate(LocalDateTime.now());
        }
        System.out.println("[SupplierService] saveSupplier called for: " + supplier.getName());
        supplierRepository.save(supplier);
        return supplier;
    }

    @Override
    public Optional<Supplier> findSupplierById(int id) {
        System.out.println("[SupplierService] findSupplierById called for id: " + id);
        return supplierRepository.findById(id);
    }

    @Override
    public Optional<Supplier> findSupplierByPhone(String phone) {
        System.out.println("[SupplierService] findSupplierByPhone called for phone: " + phone);
        return supplierRepository.findByPhone(phone);
    }

    @Override
    public List<Supplier> findSuppliersByName(String name) {
        System.out.println("[SupplierService] findSuppliersByName called for name: " + name);
        return supplierRepository.findByName(name);
    }

    @Override
    public List<Supplier> findAllSuppliers() {
        System.out.println("[SupplierService] findAllSuppliers called");
        return supplierRepository.findAll();
    }

    @Override
    public List<Supplier> findAllSuppliersByPage(int pageNo, int pageSize) {
        System.out.println("[SupplierService] findAllSuppliersByPage called - page: " + pageNo + ", size: " + pageSize);
        return supplierRepository.findAllByPage(pageNo, pageSize);
    }

    @Override
    public int countSuppliers() {
        System.out.println("[SupplierService] countSuppliers called");
        return supplierRepository.count();
    }

    @Override
    @Transactional
    public Supplier updateSupplier(Supplier supplier) {
        System.out.println("[SupplierService] updateSupplier called for id: " + supplier.getId());
        supplierRepository.update(supplier);
        return supplier;
    }

    @Override
    @Transactional
    public void deleteSupplier(int id) {
        System.out.println("[SupplierService] deleteSupplier called for id: " + id);
        supplierRepository.delete(id);
    }

    @Override
    @Transactional
    public void deleteSupplierByPhone(String phone) {
        System.out.println("[SupplierService] deleteSupplierByPhone called for phone: " + phone);
        supplierRepository.deleteByPhone(phone);
    }

    @Override
    @Transactional
    public void deleteSupplierByName(String name) {
        System.out.println("[SupplierService] deleteSupplierByName called for name: " + name);
        supplierRepository.deleteByName(name);
    }

    @Override
    public String exportSuppliersToCsv() {
        List<Supplier> suppliers = supplierRepository.findAll();
        String fileName = "suppliers.csv";

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            writer.write("ID,Name,Contact Person,Email,Phone,Address\n");
            for (Supplier supplier : suppliers) {
                writer.write(String.format("%d,%s,%s,%s,%s,%s%n",
                        supplier.getId(),
                        supplier.getName(),
                        supplier.getContactPersonName(),
                        supplier.getEmail(),
                        supplier.getPhone(),
                        supplier.getAddress()));
            }
        } catch (IOException e) {
            throw new RuntimeException("Failed to export suppliers to CSV", e);
        }

        return fileName;
    }
}
