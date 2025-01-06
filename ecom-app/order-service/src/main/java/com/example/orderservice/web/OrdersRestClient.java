package com.example.orderservice.web;

import com.example.orderservice.entities.Order;
import com.example.orderservice.feign.InventoryRestClient;
import com.example.orderservice.repositories.OrderRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
@AllArgsConstructor
@EnableMethodSecurity(prePostEnabled = true)
public class OrdersRestClient {
    private OrderRepository orderRepository;
    private InventoryRestClient restClient;

    @GetMapping
    public List<Order> getAllOrders(){
        return orderRepository.findAll();
    }

    @GetMapping("/{id}")
    public Order getOrderById(@PathVariable String id){
        Order order = orderRepository.findById(id).get();
        order.getProductItems().forEach(productItem -> {
            productItem.setProduct(restClient.getProductById(productItem.getProductId()));
        });
        return order;
    }

}
