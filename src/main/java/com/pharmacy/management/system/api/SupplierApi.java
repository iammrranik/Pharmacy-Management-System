package com.pharmacy.management.system.api;

import com.pharmacy.management.system.domain.Supplier;
import com.pharmacy.management.system.service.implementation.SupplierService;
import jakarta.validation.Valid;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/suppliers")
public class SupplierApi {

    private final SupplierService supplierService;

    public SupplierApi(SupplierService supplierService) {
        this.supplierService = supplierService;
    }

    @PostMapping
    public ResponseEntity<Map<String, Object>> save(@Valid @RequestBody Supplier supplier) {
        System.out.println("[SupplierApi] POST /api/suppliers - saving: " + supplier.getName());
        Supplier saved = supplierService.saveSupplier(supplier);
        return ResponseEntity.status(201).body(Map.of(
            "message", "Supplier created successfully",
            "data", saved
        ));
    }

    @GetMapping
    public ResponseEntity<List<Supplier>> findAll() {
        System.out.println("[SupplierApi] GET /api/suppliers - finding all");
        return ResponseEntity.ok(supplierService.findAllSuppliers());
    }

    @GetMapping("/page")
    public ResponseEntity<List<Supplier>> findAllByPage(@RequestParam int pageNo, @RequestParam int pageSize) {
        System.out.println("[SupplierApi] GET /api/suppliers/page - page: " + pageNo + ", size: " + pageSize);
        return ResponseEntity.ok(supplierService.findAllSuppliersByPage(pageNo, pageSize));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable int id) {
        System.out.println("[SupplierApi] GET /api/suppliers/" + id);
        var supplier = supplierService.findSupplierById(id);
        if (supplier.isPresent()) {
            return ResponseEntity.ok(supplier.get());
        }
        return ResponseEntity.status(404).body(Map.of("message", "Supplier not found with id: " + id));
    }

    @GetMapping("/phone/{phone}")
    public ResponseEntity<?> findByPhone(@PathVariable String phone) {
        System.out.println("[SupplierApi] GET /api/suppliers/phone/" + phone);
        var supplier = supplierService.findSupplierByPhone(phone);
        if (supplier.isPresent()) {
            return ResponseEntity.ok(supplier.get());
        }
        return ResponseEntity.status(404).body(Map.of("message", "Supplier not found with phone: " + phone));
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<List<Supplier>> findByName(@PathVariable String name) {
        System.out.println("[SupplierApi] GET /api/suppliers/name/" + name);
        return ResponseEntity.ok(supplierService.findSuppliersByName(name));
    }

    @GetMapping("/count")
    public ResponseEntity<Map<String, Object>> count() {
        System.out.println("[SupplierApi] GET /api/suppliers/count");
        return ResponseEntity.ok(Map.of("count", supplierService.countSuppliers()));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Map<String, Object>> update(@PathVariable int id, @Valid @RequestBody Supplier supplier) {
        System.out.println("[SupplierApi] PUT /api/suppliers/" + id + " - updating supplier");
        supplier.setId(id);
        Supplier updated = supplierService.updateSupplier(supplier);
        return ResponseEntity.ok(Map.of(
            "message", "Supplier updated successfully",
            "data", updated
        ));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, String>> delete(@PathVariable int id) {
        System.out.println("[SupplierApi] DELETE /api/suppliers/" + id);
        supplierService.deleteSupplier(id);
        return ResponseEntity.ok(Map.of("message", "Supplier deleted successfully"));
    }

    @DeleteMapping("/phone/{phone}")
    public ResponseEntity<Map<String, String>> deleteByPhone(@PathVariable String phone) {
        System.out.println("[SupplierApi] DELETE /api/suppliers/phone/" + phone);
        supplierService.deleteSupplierByPhone(phone);
        return ResponseEntity.ok(Map.of("message", "Supplier deleted successfully"));
    }

    @DeleteMapping("/name/{name}")
    public ResponseEntity<Map<String, String>> deleteByName(@PathVariable String name) {
        System.out.println("[SupplierApi] DELETE /api/suppliers/name/" + name);
        supplierService.deleteSupplierByName(name);
        return ResponseEntity.ok(Map.of("message", "Supplier deleted successfully"));
    }

    @GetMapping("/export/csv")
    public ResponseEntity<Resource> exportCsv() {
        System.out.println("[SupplierApi] GET /api/suppliers/export/csv");
        String fileName = supplierService.exportSuppliersToCsv();
        File file = new File(fileName);
        Resource resource = new FileSystemResource(file);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + file.getName())
                .contentType(MediaType.parseMediaType("text/csv"))
                .body(resource);
    }

}
