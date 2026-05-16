package com.pharmacy.management.system.service;

import com.pharmacy.management.system.domain.Supplier;

import java.util.List;
import java.util.Optional;

public interface ISupplierService {
    Supplier saveSupplier(Supplier supplier);
    Optional<Supplier> findSupplierById(int id);
    Optional<Supplier> findSupplierByPhone(String phone);
    List<Supplier> findSuppliersByName(String name);
    List<Supplier> findAllSuppliers();
    List<Supplier> findAllSuppliersByPage(int pageNo, int pageSize);
    int countSuppliers();
    Supplier updateSupplier(Supplier supplier);
    void deleteSupplier(int id);
    void deleteSupplierByPhone(String phone);
    void deleteSupplierByName(String name);
    String exportSuppliersToCsv();
}
