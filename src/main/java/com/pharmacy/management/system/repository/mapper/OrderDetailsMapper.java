package com.pharmacy.management.system.repository.mapper;

import com.pharmacy.management.system.domain.OrderDetails;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class OrderDetailsMapper implements RowMapper<OrderDetails> {

    @Override
    public OrderDetails mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new OrderDetails(
                rs.getInt("id"),
                rs.getInt("order_id"),
                rs.getInt("medicine_id"),
                rs.getInt("quantity"),
                rs.getFloat("unit_price")
        );
    }
}
