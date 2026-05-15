package com.pharmacy.management.system.repository.mapper;

import com.pharmacy.management.system.domain.Medicine;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MedicineMapper implements RowMapper<Medicine> {

    @Override
    public Medicine mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new Medicine(
                rs.getInt("id"),
                rs.getString("name"),
                rs.getString("category"),
                rs.getFloat("price"),
                rs.getInt("available_quantity"),
                rs.getString("batch_no"),
                rs.getDate("manufacture_date").toLocalDate(),
                rs.getDate("expiry_date").toLocalDate(),
                rs.getTimestamp("created_date_time").toLocalDateTime()
        );
    }
}
