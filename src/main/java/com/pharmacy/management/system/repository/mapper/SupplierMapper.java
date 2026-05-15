package com.pharmacy.management.system.repository.mapper;

import com.pharmacy.management.system.domain.Supplier;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SupplierMapper implements RowMapper<Supplier> {

    @Override
    public Supplier mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new Supplier(
                rs.getInt("id"),
                rs.getString("supplier_name"),
                rs.getString("contact_person_name"),
                rs.getString("email"),
                rs.getString("phone"),
                rs.getString("address"),
                rs.getTimestamp("created_date").toLocalDateTime()
        );
    }
}
