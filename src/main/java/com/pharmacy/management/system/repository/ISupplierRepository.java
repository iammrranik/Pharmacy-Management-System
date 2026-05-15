package com.pharmacy.management.system.repository;

import com.pharmacy.management.system.domain.Supplier;

import java.util.List;
import java.util.Optional;

public interface ISupplierRepository {
    int save(Supplier supplier);
    Optional<Supplier> findById(int id);
    Optional<Supplier> findByPhone(String phone);
    List<Supplier> findBySupplierName(String supplierName);
    List<Supplier> findAll();
    List<Supplier> findAllByPage(int pageNo, int pageSize);
    int count();
    int update(Supplier supplier);
    int delete(int id);
    int deleteByPhone(String phone);
    int deleteBySupplierName(String supplierName);
}
