package com.example.orderservice;

import com.example.orderservice.entities.Order;
import com.example.orderservice.entities.OrderState;
import com.example.orderservice.entities.ProductItem;
import com.example.orderservice.feign.InventoryRestClient;
import com.example.orderservice.feign.Product;
import com.example.orderservice.repositories.OrderRepository;
import com.example.orderservice.repositories.ProductItemRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

@SpringBootApplication
@EnableFeignClients
public class OrderServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(OrderServiceApplication.class, args);
    }

    @Bean
    CommandLineRunner start(OrderRepository orderRepository,
                            ProductItemRepository productItemRepository,
                            InventoryRestClient inventoryRestClient){
        return args -> {
            var productIds = List.of("P01","P02","P03","P04","P05","P06");

            for (int i = 0; i < 5; i++) {
                Order order = Order.builder()
                        .id(UUID.randomUUID().toString())
                        .date(LocalDate.now())
                        .state(OrderState.PENDING)
                        .build();
                Order saved = orderRepository.save(order);

                productIds.forEach(product -> {
                    ProductItem productItem = ProductItem.builder()
                            .productId(product)
                            .quantity((int)(Math.random()*9)+1)
                            .price(Math.random()*1000+100)
                            .order(saved)
                            .build();
                    productItemRepository.save(productItem);

                });
            }



        };
    }

}
