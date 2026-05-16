package com.pharmacy.management.system.repository.implementation;

import com.pharmacy.management.system.domain.Supplier;
import com.pharmacy.management.system.repository.ISupplierRepository;
import com.pharmacy.management.system.repository.mapper.SupplierMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class SupplierRepository implements ISupplierRepository {
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    private final SupplierMapper supplierMapper;

    public SupplierRepository(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
        this.supplierMapper = new SupplierMapper();
    }

    @Override
    public int save(Supplier supplier) {
        String sql = """
                INSERT INTO suppliers (supplier_name, contact_person_name, email, phone, address)
                VALUES (:supplierName, :contactPersonName, :email, :phone, :address)
                """;
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("supplierName", supplier.getName());
        params.addValue("contactPersonName", supplier.getContactPersonName());
        params.addValue("email", supplier.getEmail());
        params.addValue("phone", supplier.getPhone());
        params.addValue("address", supplier.getAddress());

        System.out.println("[SupplierRepository] save called for supplier: " + supplier.getName());
        return namedParameterJdbcTemplate.update(sql, params);
    }

    @Override
    public Optional<Supplier> findById(int id) {
        String sql = """
                SELECT *
                FROM suppliers
                WHERE id = :id
                """;
        MapSqlParameterSource params = new MapSqlParameterSource("id", id);
        List<Supplier> suppliers = namedParameterJdbcTemplate.query(sql, params, supplierMapper);
        System.out.println("[SupplierRepository] findById called for id: " + id);
        return suppliers.stream().findFirst();
    }

    @Override
    public Optional<Supplier> findByPhone(String phone) {
        String sql = """
                SELECT *
                FROM suppliers
                WHERE phone = :phone
                """;
        MapSqlParameterSource params = new MapSqlParameterSource("phone", phone);
        List<Supplier> suppliers = namedParameterJdbcTemplate.query(sql, params, supplierMapper);
        System.out.println("[SupplierRepository] findByPhone called for phone: " + phone);
        return suppliers.stream().findFirst();
    }

    @Override
    public List<Supplier> findByName(String name) {
        String sql = """
                SELECT *
                FROM suppliers
                WHERE supplier_name LIKE :supplierName
                ORDER BY id
                """;
        MapSqlParameterSource params = new MapSqlParameterSource("supplierName", "%" + name + "%");
        System.out.println("[SupplierRepository] findByName called for name: " + name);
        return namedParameterJdbcTemplate.query(sql, params, supplierMapper);
    }

    @Override
    public List<Supplier> findAll() {
        String sql = """
                SELECT *
                FROM suppliers
                ORDER BY id
                """;
        System.out.println("[SupplierRepository] findAll called");
        return namedParameterJdbcTemplate.query(sql, supplierMapper);
    }

    @Override
    public List<Supplier> findAllByPage(int pageNo, int pageSize) {
        String sql = """
                SELECT *
                FROM suppliers
                ORDER BY id
                LIMIT :limit
                OFFSET :offset
                """;
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("limit", pageSize);
        params.addValue("offset", (pageNo - 1) * pageSize);
        System.out.println("[SupplierRepository] findAllByPage called - page: " + pageNo + ", size: " + pageSize);
        return namedParameterJdbcTemplate.query(sql, params, supplierMapper);
    }

    @Override
    public int count() {
        String sql = """
                SELECT COUNT(*)
                FROM suppliers
                """;
        MapSqlParameterSource params = new MapSqlParameterSource();
        Integer count = namedParameterJdbcTemplate.queryForObject(sql, params, Integer.class);
        System.out.println("[SupplierRepository] count called");
        return count != null ? count : 0;
    }

    @Override
    public int update(Supplier supplier) {
        String sql = """
                UPDATE suppliers
                SET supplier_name = :supplierName,
                    contact_person_name = :contactPersonName,
                    email = :email,
                    phone = :phone,
                    address = :address
                WHERE id = :id
                """;
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("id", supplier.getId());
        params.addValue("supplierName", supplier.getName());
        params.addValue("contactPersonName", supplier.getContactPersonName());
        params.addValue("email", supplier.getEmail());
        params.addValue("phone", supplier.getPhone());
        params.addValue("address", supplier.getAddress());

        System.out.println("[SupplierRepository] update called for id: " + supplier.getId());
        return namedParameterJdbcTemplate.update(sql, params);
    }

    @Override
    public int delete(int id) {
        String sql = """
                DELETE
                FROM suppliers
                WHERE id = :id
                """;
        MapSqlParameterSource params = new MapSqlParameterSource("id", id);
        System.out.println("[SupplierRepository] delete called for id: " + id);
        return namedParameterJdbcTemplate.update(sql, params);
    }

    @Override
    public int deleteByPhone(String phone) {
        String sql = """
                DELETE
                FROM suppliers
                WHERE phone = :phone
                """;
        MapSqlParameterSource params = new MapSqlParameterSource("phone", phone);
        System.out.println("[SupplierRepository] deleteByPhone called for phone: " + phone);
        return namedParameterJdbcTemplate.update(sql, params);
    }

    @Override
    public int deleteByName(String name) {
        String sql = """
                DELETE
                FROM suppliers
                WHERE supplier_name = :supplierName
                """;
        MapSqlParameterSource params = new MapSqlParameterSource("supplierName", name);
        System.out.println("[SupplierRepository] deleteByName called for name: " + name);
        return namedParameterJdbcTemplate.update(sql, params);
    }
}
