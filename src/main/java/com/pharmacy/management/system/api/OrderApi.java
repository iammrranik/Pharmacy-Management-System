package com.pharmacy.management.system.api;

import com.pharmacy.management.system.domain.Order;
import com.pharmacy.management.system.domain.OrderDetails;
import com.pharmacy.management.system.domain.enums.OrderStatus;
import com.pharmacy.management.system.service.implementation.OrderService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/orders")
public class OrderApi {

    private final OrderService orderService;

    public OrderApi(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping
    public ResponseEntity<Map<String, Object>> createOrder(@Valid @RequestBody Order order, @RequestBody List<OrderDetails> orderDetails) {
        System.out.println("[OrderApi] POST /api/orders - creating order for: " + order.getCustomerPhone());
        Order created = orderService.createOrder(order, orderDetails);
        return ResponseEntity.status(201).body(Map.of(
            "message", "Order created successfully",
            "data", created
        ));
    }

    @PostMapping("/save")
    public ResponseEntity<Map<String, Object>> save(@Valid @RequestBody Order order) {
        System.out.println("[OrderApi] POST /api/orders/save - saving order for: " + order.getCustomerPhone());
        Order saved = orderService.saveOrder(order);
        return ResponseEntity.status(201).body(Map.of(
            "message", "Order saved successfully",
            "data", saved
        ));
    }

    @PostMapping("/return/{id}")
    public ResponseEntity<Map<String, Object>> returnMedicine(@PathVariable int id) {
        System.out.println("[OrderApi] POST /api/orders/return/" + id);
        Order returned = orderService.returnMedicine(id);
        return ResponseEntity.ok(Map.of(
            "message", "Medicine returned successfully",
            "data", returned
        ));
    }

    @GetMapping
    public ResponseEntity<List<Order>> findAll() {
        System.out.println("[OrderApi] GET /api/orders - finding all");
        return ResponseEntity.ok(orderService.findAllOrders());
    }

    @GetMapping("/page")
    public ResponseEntity<List<Order>> findAllByPage(@RequestParam int pageNo, @RequestParam int pageSize) {
        System.out.println("[OrderApi] GET /api/orders/page - page: " + pageNo + ", size: " + pageSize);
        return ResponseEntity.ok(orderService.findAllOrdersByPage(pageNo, pageSize));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable int id) {
        System.out.println("[OrderApi] GET /api/orders/" + id);
        var order = orderService.findOrderById(id);
        if (order.isPresent()) {
            return ResponseEntity.ok(order.get());
        }
        return ResponseEntity.status(404).body(Map.of("message", "Order not found with id: " + id));
    }

    @GetMapping("/phone/{phone}")
    public ResponseEntity<List<Order>> findByCustomerPhone(@PathVariable String phone) {
        System.out.println("[OrderApi] GET /api/orders/phone/" + phone);
        return ResponseEntity.ok(orderService.findOrdersByCustomerPhone(phone));
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<List<Order>> findByStatus(@PathVariable OrderStatus status) {
        System.out.println("[OrderApi] GET /api/orders/status/" + status);
        return ResponseEntity.ok(orderService.findOrdersByStatus(status));
    }

    @GetMapping("/count")
    public ResponseEntity<Map<String, Object>> count() {
        System.out.println("[OrderApi] GET /api/orders/count");
        return ResponseEntity.ok(Map.of("count", orderService.countOrders()));
    }

    @PutMapping
    public ResponseEntity<Map<String, Object>> update(@Valid @RequestBody Order order) {
        System.out.println("[OrderApi] PUT /api/orders - updating id: " + order.getId());
        Order updated = orderService.updateOrder(order);
        return ResponseEntity.ok(Map.of(
            "message", "Order updated successfully",
            "data", updated
        ));
    }

    @PutMapping("/status/{id}")
    public ResponseEntity<Map<String, Object>> updateStatus(@PathVariable int id, @RequestParam OrderStatus status) {
        System.out.println("[OrderApi] PUT /api/orders/status/" + id + "?status=" + status);
        Order updated = orderService.updateOrderStatus(id, status);
        return ResponseEntity.ok(Map.of(
            "message", "Order status updated successfully",
            "data", updated
        ));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, String>> delete(@PathVariable int id) {
        System.out.println("[OrderApi] DELETE /api/orders/" + id);
        orderService.deleteOrder(id);
        return ResponseEntity.ok(Map.of("message", "Order deleted successfully"));
    }
}
