package com.pharmacy.management.system.api;

import com.pharmacy.management.system.domain.OrderDetails;
import com.pharmacy.management.system.service.implementation.OrderDetailsService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/order-details")
public class OrderDetailsApi {

    private final OrderDetailsService orderDetailsService;

    public OrderDetailsApi(OrderDetailsService orderDetailsService) {
        this.orderDetailsService = orderDetailsService;
    }

    @PostMapping
    public ResponseEntity<Map<String, Object>> save(@Valid @RequestBody OrderDetails orderDetails) {
        System.out.println("[OrderDetailsApi] POST /api/order-details - saving for orderId: " + orderDetails.getOrderId());
        OrderDetails saved = orderDetailsService.saveOrderDetails(orderDetails);
        return ResponseEntity.status(201).body(Map.of(
            "message", "Order detail created successfully",
            "data", saved
        ));
    }

    @GetMapping
    public ResponseEntity<List<OrderDetails>> findAll() {
        System.out.println("[OrderDetailsApi] GET /api/order-details - finding all");
        return ResponseEntity.ok(orderDetailsService.findAllOrderDetails());
    }

    @GetMapping("/page")
    public ResponseEntity<List<OrderDetails>> findAllByPage(@RequestParam int pageNo, @RequestParam int pageSize) {
        System.out.println("[OrderDetailsApi] GET /api/order-details/page - page: " + pageNo + ", size: " + pageSize);
        return ResponseEntity.ok(orderDetailsService.findAllOrderDetailsByPage(pageNo, pageSize));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable int id) {
        System.out.println("[OrderDetailsApi] GET /api/order-details/" + id);
        var detail = orderDetailsService.findOrderDetailsById(id);
        if (detail.isPresent()) {
            return ResponseEntity.ok(detail.get());
        }
        return ResponseEntity.status(404).body(Map.of("message", "Order detail not found with id: " + id));
    }

    @GetMapping("/order/{orderId}")
    public ResponseEntity<List<OrderDetails>> findByOrderId(@PathVariable int orderId) {
        System.out.println("[OrderDetailsApi] GET /api/order-details/order/" + orderId);
        return ResponseEntity.ok(orderDetailsService.findOrderDetailsByOrderId(orderId));
    }

    @GetMapping("/count")
    public ResponseEntity<Map<String, Object>> count() {
        System.out.println("[OrderDetailsApi] GET /api/order-details/count");
        return ResponseEntity.ok(Map.of("count", orderDetailsService.countOrderDetails()));
    }

    @PutMapping
    public ResponseEntity<Map<String, Object>> update(@Valid @RequestBody OrderDetails orderDetails) {
        System.out.println("[OrderDetailsApi] PUT /api/order-details - updating id: " + orderDetails.getId());
        OrderDetails updated = orderDetailsService.updateOrderDetails(orderDetails);
        return ResponseEntity.ok(Map.of(
            "message", "Order detail updated successfully",
            "data", updated
        ));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, String>> delete(@PathVariable int id) {
        System.out.println("[OrderDetailsApi] DELETE /api/order-details/" + id);
        orderDetailsService.deleteOrderDetails(id);
        return ResponseEntity.ok(Map.of("message", "Order detail deleted successfully"));
    }

    @DeleteMapping("/order/{orderId}")
    public ResponseEntity<Map<String, String>> deleteByOrderId(@PathVariable int orderId) {
        System.out.println("[OrderDetailsApi] DELETE /api/order-details/order/" + orderId);
        orderDetailsService.deleteOrderDetailsByOrderId(orderId);
        return ResponseEntity.ok(Map.of("message", "Order details deleted successfully"));
    }
}
