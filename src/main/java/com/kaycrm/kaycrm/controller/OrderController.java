package com.kaycrm.kaycrm.controller;

import com.kaycrm.kaycrm.model.Order;
import com.kaycrm.kaycrm.service.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/orders")
public class OrderController {
    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    // Отримати всі замовлення
    @GetMapping
    public List<Order> getAllOrders() {
        return orderService.getAllOrders();
    }

    // Отримати замовлення за ID
    @GetMapping("/{id}")
    public ResponseEntity<Order> getOrderById(@PathVariable Long id) {
        Optional<Order> order = orderService.getOrderById(id);
        return order.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Створити нове замовлення
    @PostMapping
    public Order createOrder(@RequestBody Order order) {
        return orderService.saveOrder(order);
    }

    // Оновити існуюче замовлення
    @PutMapping("/{id}")
    public ResponseEntity<Order> updateOrder(@PathVariable Long id, @RequestBody Order updatedOrder) {
        Optional<Order> existingOrder = orderService.getOrderById(id);
        if (existingOrder.isPresent()) {
            Order order = existingOrder.get();
            order.setProduct(updatedOrder.getProduct());
            order.setPrice(updatedOrder.getPrice());
            order.setCustomer(updatedOrder.getCustomer());
            return ResponseEntity.ok(orderService.saveOrder(order));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Видалити замовлення
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrder(@PathVariable Long id) {
        orderService.deleteOrder(id);
        return ResponseEntity.noContent().build();
    }
}
