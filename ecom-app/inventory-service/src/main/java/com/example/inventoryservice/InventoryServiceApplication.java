package com.example.inventoryservice;

import com.example.inventoryservice.entities.Product;
import com.example.inventoryservice.repositories.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.awt.*;
import java.util.List;
import java.util.UUID;

@SpringBootApplication
public class InventoryServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(InventoryServiceApplication.class, args);
	}

	@Bean
	CommandLineRunner start(ProductRepository productRepository){
		return args -> {
			List.of("Lenovo","HP","MacBook","Pavillon","Alienware","Smartphone").forEach(productName ->{
				productRepository.save(
						Product.builder()
								.id(UUID.randomUUID().toString())
								.name(productName)
								.quantity((int) (Math.random() * 100) + 1)
								.price(Math.random() * 10_000 + 2000)
								.build()
				);
			} );
		};
	}

}
