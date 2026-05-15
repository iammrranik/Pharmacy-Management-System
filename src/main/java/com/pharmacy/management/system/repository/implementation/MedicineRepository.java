package com.pharmacy.management.system.repository.implementation;

import com.pharmacy.management.system.domain.Medicine;
import com.pharmacy.management.system.repository.IMedicineRepository;
import com.pharmacy.management.system.repository.mapper.MedicineMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class MedicineRepository implements IMedicineRepository {
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    private final MedicineMapper medicineMapper;

    public MedicineRepository(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
        this.medicineMapper = new MedicineMapper();
    }

    @Override
    public int save(Medicine medicine) {
        String sql = """
                INSERT INTO medicines (name, category, price, available_quantity, batch_no,
                                       manufacture_date, expiry_date, created_date_time)
                VALUES (:name, :category, :price, :availableQuantity, :batchNo,
                        :manufactureDate, :expiryDate, :createdDateTime)
                """;
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("name", medicine.getName());
        params.addValue("category", medicine.getCategory());
        params.addValue("price", medicine.getPrice());
        params.addValue("availableQuantity", medicine.getAvailableQuantity());
        params.addValue("batchNo", medicine.getBatchNo());
        params.addValue("manufactureDate", medicine.getManufactureDate());
        params.addValue("expiryDate", medicine.getExpiryDate());
        params.addValue("createdDateTime", medicine.getCreatedDateTime());

        System.out.println("[MedicineRepository] save called for medicine: " + medicine.getName());
        return namedParameterJdbcTemplate.update(sql, params);
    }

    @Override
    public Optional<Medicine> findById(int id) {
        String sql = """
                SELECT *
                FROM medicines
                WHERE id = :id
                """;
        MapSqlParameterSource params = new MapSqlParameterSource("id", id);
        List<Medicine> medicines = namedParameterJdbcTemplate.query(sql, params, medicineMapper);
        System.out.println("[MedicineRepository] findById called for id: " + id);
        return medicines.stream().findFirst();
    }

    @Override
    public List<Medicine> findByName(String name) {
        String sql = """
                SELECT *
                FROM medicines
                WHERE name LIKE :name
                ORDER BY id
                """;
        MapSqlParameterSource params = new MapSqlParameterSource("name", "%" + name + "%");
        System.out.println("[MedicineRepository] findByName called for name: " + name);
        return namedParameterJdbcTemplate.query(sql, params, medicineMapper);
    }

    @Override
    public List<Medicine> findByCategory(String category) {
        String sql = """
                SELECT *
                FROM medicines
                WHERE category = :category
                ORDER BY id
                """;
        MapSqlParameterSource params = new MapSqlParameterSource("category", category);
        System.out.println("[MedicineRepository] findByCategory called for category: " + category);
        return namedParameterJdbcTemplate.query(sql, params, medicineMapper);
    }

    @Override
    public List<Medicine> findBySupplierId(int supplierId) {
        String sql = """
                SELECT *
                FROM medicines
                WHERE supplier_id = :supplierId
                ORDER BY id
                """;
        MapSqlParameterSource params = new MapSqlParameterSource("supplierId", supplierId);
        System.out.println("[MedicineRepository] findBySupplierId called for supplierId: " + supplierId);
        return namedParameterJdbcTemplate.query(sql, params, medicineMapper);
    }

    @Override
    public List<Medicine> findByBatchId(int batchId) {
        String sql = """
                SELECT *
                FROM medicines
                WHERE batch_no LIKE :batchNo
                ORDER BY id
                """;
        MapSqlParameterSource params = new MapSqlParameterSource("batchNo", "%" + batchId + "%");
        System.out.println("[MedicineRepository] findByBatchId called for batchId: " + batchId);
        return namedParameterJdbcTemplate.query(sql, params, medicineMapper);
    }

    @Override
    public List<Medicine> findAll() {
        String sql = """
                SELECT *
                FROM medicines
                ORDER BY id
                """;
        System.out.println("[MedicineRepository] findAll called");
        return namedParameterJdbcTemplate.query(sql, medicineMapper);
    }

    @Override
    public List<Medicine> findAllByPage(int pageNo, int pageSize) {
        String sql = """
                SELECT *
                FROM medicines
                ORDER BY id
                LIMIT :limit
                OFFSET :offset
                """;
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("limit", pageSize);
        params.addValue("offset", (pageNo - 1) * pageSize);
        System.out.println("[MedicineRepository] findAllByPage called - page: " + pageNo + ", size: " + pageSize);
        return namedParameterJdbcTemplate.query(sql, params, medicineMapper);
    }

    @Override
    public int count() {
        String sql = """
                SELECT COUNT(*)
                FROM medicines
                """;
        MapSqlParameterSource params = new MapSqlParameterSource();
        Integer count = namedParameterJdbcTemplate.queryForObject(sql, params, Integer.class);
        System.out.println("[MedicineRepository] count called");
        return count != null ? count : 0;
    }

    @Override
    public int update(Medicine medicine) {
        String sql = """
                UPDATE medicines
                SET name = :name,
                    category = :category,
                    price = :price,
                    available_quantity = :availableQuantity,
                    batch_no = :batchNo,
                    manufacture_date = :manufactureDate,
                    expiry_date = :expiryDate
                WHERE id = :id
                """;
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("id", medicine.getId());
        params.addValue("name", medicine.getName());
        params.addValue("category", medicine.getCategory());
        params.addValue("price", medicine.getPrice());
        params.addValue("availableQuantity", medicine.getAvailableQuantity());
        params.addValue("batchNo", medicine.getBatchNo());
        params.addValue("manufactureDate", medicine.getManufactureDate());
        params.addValue("expiryDate", medicine.getExpiryDate());

        System.out.println("[MedicineRepository] update called for id: " + medicine.getId());
        return namedParameterJdbcTemplate.update(sql, params);
    }

    @Override
    public int delete(int id) {
        String sql = """
                DELETE
                FROM medicines
                WHERE id = :id
                """;
        MapSqlParameterSource params = new MapSqlParameterSource("id", id);
        System.out.println("[MedicineRepository] delete called for id: " + id);
        return namedParameterJdbcTemplate.update(sql, params);
    }

    @Override
    public int delete(Medicine medicine) {
        System.out.println("[MedicineRepository] delete called for medicine id: " + medicine.getId());
        return delete(medicine.getId());
    }

    @Override
    public List<Medicine> findLowStockMedicines(int quantity) {
        String sql = """
                SELECT *
                FROM medicines
                WHERE available_quantity <= :quantity
                ORDER BY available_quantity
                """;
        MapSqlParameterSource params = new MapSqlParameterSource("quantity", quantity);
        System.out.println("[MedicineRepository] findLowStockMedicines called with quantity: " + quantity);
        return namedParameterJdbcTemplate.query(sql, params, medicineMapper);
    }

    @Override
    public List<Medicine> findExpiredMedicines() {
        String sql = """
                SELECT *
                FROM medicines
                WHERE expiry_date <= CURDATE()
                ORDER BY expiry_date
                """;
        System.out.println("[MedicineRepository] findExpiredMedicines called");
        return namedParameterJdbcTemplate.query(sql, medicineMapper);
    }

    @Override
    public List<Medicine> findExpiredMedicines(int days) {
        String sql = """
                SELECT *
                FROM medicines
                WHERE expiry_date BETWEEN CURDATE() AND DATE_ADD(CURDATE(), INTERVAL :days DAY)
                ORDER BY expiry_date
                """;
        MapSqlParameterSource params = new MapSqlParameterSource("days", days);
        System.out.println("[MedicineRepository] findExpiredMedicines called for days: " + days);
        return namedParameterJdbcTemplate.query(sql, params, medicineMapper);
    }

    @Override
    public int updateMedicineQuantity(int medicineId, int quantity) {
        String sql = """
                UPDATE medicines
                SET available_quantity = :quantity
                WHERE id = :id
                """;
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("id", medicineId);
        params.addValue("quantity", quantity);
        System.out.println("[MedicineRepository] updateMedicineQuantity called for id: " + medicineId + ", quantity: " + quantity);
        return namedParameterJdbcTemplate.update(sql, params);
    }
}
