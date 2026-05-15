package com.pharmacy.management.system.repository.implementation;

import com.pharmacy.management.system.domain.OrderDetails;
import com.pharmacy.management.system.repository.IOrderDetailsRepository;
import com.pharmacy.management.system.repository.mapper.OrderDetailsMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class OrderDetailsRepository implements IOrderDetailsRepository {
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    private final OrderDetailsMapper orderDetailsMapper;

    public OrderDetailsRepository(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
        this.orderDetailsMapper = new OrderDetailsMapper();
    }

    @Override
    public int save(OrderDetails orderDetails) {
        String sql = """
                INSERT INTO order_details (order_id, medicine_id, quantity, unit_price)
                VALUES (:orderId, :medicineId, :quantity, :unitPrice)
                """;
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("orderId", orderDetails.getOrderId());
        params.addValue("medicineId", orderDetails.getMedicineId());
        params.addValue("quantity", orderDetails.getQuantity());
        params.addValue("unitPrice", orderDetails.getUnitPrice());

        System.out.println("[OrderDetailsRepository] save called for orderId: " + orderDetails.getOrderId());
        return namedParameterJdbcTemplate.update(sql, params);
    }

    @Override
    public Optional<OrderDetails> findById(int id) {
        String sql = """
                SELECT *
                FROM order_details
                WHERE id = :id
                """;
        MapSqlParameterSource params = new MapSqlParameterSource("id", id);
        List<OrderDetails> details = namedParameterJdbcTemplate.query(sql, params, orderDetailsMapper);
        System.out.println("[OrderDetailsRepository] findById called for id: " + id);
        return details.stream().findFirst();
    }

    @Override
    public List<OrderDetails> findByOrderId(int orderId) {
        String sql = """
                SELECT *
                FROM order_details
                WHERE order_id = :orderId
                ORDER BY id
                """;
        MapSqlParameterSource params = new MapSqlParameterSource("orderId", orderId);
        System.out.println("[OrderDetailsRepository] findByOrderId called for orderId: " + orderId);
        return namedParameterJdbcTemplate.query(sql, params, orderDetailsMapper);
    }

    @Override
    public List<OrderDetails> findAll() {
        String sql = """
                SELECT *
                FROM order_details
                ORDER BY id
                """;
        System.out.println("[OrderDetailsRepository] findAll called");
        return namedParameterJdbcTemplate.query(sql, orderDetailsMapper);
    }

    @Override
    public List<OrderDetails> findAllByPage(int pageNo, int pageSize) {
        String sql = """
                SELECT *
                FROM order_details
                ORDER BY id
                LIMIT :limit
                OFFSET :offset
                """;
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("limit", pageSize);
        params.addValue("offset", (pageNo - 1) * pageSize);
        System.out.println("[OrderDetailsRepository] findAllByPage called - page: " + pageNo + ", size: " + pageSize);
        return namedParameterJdbcTemplate.query(sql, params, orderDetailsMapper);
    }

    @Override
    public int count() {
        String sql = """
                SELECT COUNT(*)
                FROM order_details
                """;
        MapSqlParameterSource params = new MapSqlParameterSource();
        Integer count = namedParameterJdbcTemplate.queryForObject(sql, params, Integer.class);
        System.out.println("[OrderDetailsRepository] count called");
        return count != null ? count : 0;
    }

    @Override
    public int update(OrderDetails orderDetails) {
        String sql = """
                UPDATE order_details
                SET order_id = :orderId,
                    medicine_id = :medicineId,
                    quantity = :quantity,
                    unit_price = :unitPrice
                WHERE id = :id
                """;
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("id", orderDetails.getId());
        params.addValue("orderId", orderDetails.getOrderId());
        params.addValue("medicineId", orderDetails.getMedicineId());
        params.addValue("quantity", orderDetails.getQuantity());
        params.addValue("unitPrice", orderDetails.getUnitPrice());

        System.out.println("[OrderDetailsRepository] update called for id: " + orderDetails.getId());
        return namedParameterJdbcTemplate.update(sql, params);
    }

    @Override
    public int delete(int id) {
        String sql = """
                DELETE
                FROM order_details
                WHERE id = :id
                """;
        MapSqlParameterSource params = new MapSqlParameterSource("id", id);
        System.out.println("[OrderDetailsRepository] delete called for id: " + id);
        return namedParameterJdbcTemplate.update(sql, params);
    }

    @Override
    public int deleteByOrderId(int orderId) {
        String sql = """
                DELETE
                FROM order_details
                WHERE order_id = :orderId
                """;
        MapSqlParameterSource params = new MapSqlParameterSource("orderId", orderId);
        System.out.println("[OrderDetailsRepository] deleteByOrderId called for orderId: " + orderId);
        return namedParameterJdbcTemplate.update(sql, params);
    }

    @Override
    public int delete(OrderDetails orderDetails) {
        System.out.println("[OrderDetailsRepository] delete called for orderDetails id: " + orderDetails.getId());
        return delete(orderDetails.getId());
    }
}
