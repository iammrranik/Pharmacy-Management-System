package com.pharmacy.management.system.repository.mapper;

import com.pharmacy.management.system.domain.Order;
import com.pharmacy.management.system.domain.enums.OrderStatus;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class OrderMapper implements RowMapper<Order> {

    @Override
    public Order mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new Order(
                rs.getInt("id"),
                rs.getInt("customer_id"),
                rs.getString("customer_phone"),
                rs.getTimestamp("order_date_time").toLocalDateTime(),
                rs.getTimestamp("return_date_time") != null
                        ? rs.getTimestamp("return_date_time").toLocalDateTime()
                        : null,
                rs.getFloat("total_amount"),
                rs.getFloat("refund_amount"),
                OrderStatus.valueOf(rs.getString("status")),
                rs.getInt("seller_id")
        );
    }
}
