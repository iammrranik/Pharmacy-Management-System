package com.pharmacy.management.system.repository.implementation;

import com.pharmacy.management.system.domain.Order;
import com.pharmacy.management.system.domain.enums.OrderStatus;
import com.pharmacy.management.system.repository.IOrderRepository;
import com.pharmacy.management.system.repository.mapper.OrderMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class OrderRepository implements IOrderRepository {
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    private final OrderMapper orderMapper;

    public OrderRepository(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
        this.orderMapper = new OrderMapper();
    }

    @Override
    public int save(Order order) {
        String sql = """
                INSERT INTO orders (customer_id, customer_phone, order_date_time,
                                    return_date_time, total_amount, refund_amount,
                                    status, seller_id)
                VALUES (:customerId, :customerPhone, :orderDateTime,
                        :returnDateTime, :totalAmount, :refundAmount,
                        :status, :sellerId)
                """;
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("customerId", order.getCustomerId());
        params.addValue("customerPhone", order.getCustomerPhone());
        params.addValue("orderDateTime", order.getOrderDateTime());
        params.addValue("returnDateTime", order.getReturnDateTime());
        params.addValue("totalAmount", order.getTotalAmount());
        params.addValue("refundAmount", order.getRefundAmount());
        params.addValue("status", order.getStatus().name());
        params.addValue("sellerId", order.getSellerId());

        System.out.println("[OrderRepository] save called for customer: " + order.getCustomerPhone());
        return namedParameterJdbcTemplate.update(sql, params);
    }

    @Override
    public Optional<Order> findById(int id) {
        String sql = """
                SELECT *
                FROM orders
                WHERE id = :id
                """;
        MapSqlParameterSource params = new MapSqlParameterSource("id", id);
        List<Order> orders = namedParameterJdbcTemplate.query(sql, params, orderMapper);
        System.out.println("[OrderRepository] findById called for id: " + id);
        return orders.stream().findFirst();
    }

    @Override
    public List<Order> findByCustomerPhone(String phone) {
        String sql = """
                SELECT *
                FROM orders
                WHERE customer_phone = :phone
                ORDER BY id
                """;
        MapSqlParameterSource params = new MapSqlParameterSource("phone", phone);
        System.out.println("[OrderRepository] findByCustomerPhone called for phone: " + phone);
        return namedParameterJdbcTemplate.query(sql, params, orderMapper);
    }

    @Override
    public List<Order> findByStatus(OrderStatus status) {
        String sql = """
                SELECT *
                FROM orders
                WHERE status = :status
                ORDER BY id
                """;
        MapSqlParameterSource params = new MapSqlParameterSource("status", status.name());
        System.out.println("[OrderRepository] findByStatus called for status: " + status);
        return namedParameterJdbcTemplate.query(sql, params, orderMapper);
    }

    @Override
    public List<Order> findAll() {
        String sql = """
                SELECT *
                FROM orders
                ORDER BY id
                """;
        System.out.println("[OrderRepository] findAll called");
        return namedParameterJdbcTemplate.query(sql, orderMapper);
    }

    @Override
    public List<Order> findAllByPage(int pageNo, int pageSize) {
        String sql = """
                SELECT *
                FROM orders
                ORDER BY id
                LIMIT :limit
                OFFSET :offset
                """;
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("limit", pageSize);
        params.addValue("offset", (pageNo - 1) * pageSize);
        System.out.println("[OrderRepository] findAllByPage called - page: " + pageNo + ", size: " + pageSize);
        return namedParameterJdbcTemplate.query(sql, params, orderMapper);
    }

    @Override
    public int count() {
        String sql = """
                SELECT COUNT(*)
                FROM orders
                """;
        MapSqlParameterSource params = new MapSqlParameterSource();
        Integer count = namedParameterJdbcTemplate.queryForObject(sql, params, Integer.class);
        System.out.println("[OrderRepository] count called");
        return count != null ? count : 0;
    }

    @Override
    public int update(Order order) {
        String sql = """
                UPDATE orders
                SET customer_id = :customerId,
                    customer_phone = :customerPhone,
                    order_date_time = :orderDateTime,
                    return_date_time = :returnDateTime,
                    total_amount = :totalAmount,
                    refund_amount = :refundAmount,
                    status = :status,
                    seller_id = :sellerId
                WHERE id = :id
                """;
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("id", order.getId());
        params.addValue("customerId", order.getCustomerId());
        params.addValue("customerPhone", order.getCustomerPhone());
        params.addValue("orderDateTime", order.getOrderDateTime());
        params.addValue("returnDateTime", order.getReturnDateTime());
        params.addValue("totalAmount", order.getTotalAmount());
        params.addValue("refundAmount", order.getRefundAmount());
        params.addValue("status", order.getStatus().name());
        params.addValue("sellerId", order.getSellerId());

        System.out.println("[OrderRepository] update called for id: " + order.getId());
        return namedParameterJdbcTemplate.update(sql, params);
    }

    @Override
    public int delete(int id) {
        String sql = """
                DELETE
                FROM orders
                WHERE id = :id
                """;
        MapSqlParameterSource params = new MapSqlParameterSource("id", id);
        System.out.println("[OrderRepository] delete called for id: " + id);
        return namedParameterJdbcTemplate.update(sql, params);
    }

    @Override
    public int delete(Order order) {
        System.out.println("[OrderRepository] delete called for order id: " + order.getId());
        return delete(order.getId());
    }

    @Override
    public int[] saveAll(List<Order> orders) {
        int[] results = new int[orders.size()];
        System.out.println("[OrderRepository] saveAll called for " + orders.size() + " orders");
        for (int i = 0; i < orders.size(); i++) {
            results[i] = save(orders.get(i));
        }
        return results;
    }
}
