package com.pharmacy.management.system.service.implementation;

import com.pharmacy.management.system.domain.Supplier;
import com.pharmacy.management.system.repository.implementation.SupplierRepository;
import com.pharmacy.management.system.service.ISupplierService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class SupplierService implements ISupplierService {
    private final SupplierRepository supplierRepository;

    public SupplierService(SupplierRepository supplierRepository) {
        this.supplierRepository = supplierRepository;
    }

    @Override
    public Supplier saveSupplier(Supplier supplier) {
        System.out.println("[SupplierService] saveSupplier called for: " + supplier.getSupplierName());
        supplierRepository.save(supplier);
        return supplier;
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Supplier> findSupplierById(int id) {
        System.out.println("[SupplierService] findSupplierById called for id: " + id);
        return supplierRepository.findById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Supplier> findSupplierByPhone(String phone) {
        System.out.println("[SupplierService] findSupplierByPhone called for phone: " + phone);
        return supplierRepository.findByPhone(phone);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Supplier> findSuppliersByName(String supplierName) {
        System.out.println("[SupplierService] findSuppliersByName called for name: " + supplierName);
        return supplierRepository.findBySupplierName(supplierName);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Supplier> findAllSuppliers() {
        System.out.println("[SupplierService] findAllSuppliers called");
        return supplierRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Supplier> findAllSuppliersByPage(int pageNo, int pageSize) {
        System.out.println("[SupplierService] findAllSuppliersByPage called - page: " + pageNo + ", size: " + pageSize);
        return supplierRepository.findAllByPage(pageNo, pageSize);
    }

    @Override
    @Transactional(readOnly = true)
    public int countSuppliers() {
        System.out.println("[SupplierService] countSuppliers called");
        return supplierRepository.count();
    }

    @Override
    public Supplier updateSupplier(Supplier supplier) {
        System.out.println("[SupplierService] updateSupplier called for id: " + supplier.getId());
        supplierRepository.update(supplier);
        return supplier;
    }

    @Override
    public void deleteSupplier(int id) {
        System.out.println("[SupplierService] deleteSupplier called for id: " + id);
        supplierRepository.delete(id);
    }

    @Override
    public void deleteSupplierByPhone(String phone) {
        System.out.println("[SupplierService] deleteSupplierByPhone called for phone: " + phone);
        supplierRepository.deleteByPhone(phone);
    }

    @Override
    public void deleteSupplierByName(String supplierName) {
        System.out.println("[SupplierService] deleteSupplierByName called for name: " + supplierName);
        supplierRepository.deleteBySupplierName(supplierName);
    }
}
