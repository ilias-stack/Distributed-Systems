package org.sid.inventory_service;

import org.sid.inventory_service.entities.Product;
import org.sid.inventory_service.repositories.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;

import java.util.List;

@SpringBootApplication
public class InventoryServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(InventoryServiceApplication.class, args);
	}

	@Bean
	CommandLineRunner start(ProductRepository productRepository, RepositoryRestConfiguration restConfiguration){
		restConfiguration.exposeIdsFor(Product.class);
		return args -> {
			List.of("Computer","Phone","Tablet","DishWasher").forEach(s -> {
				productRepository.save(new Product(null,s,1000+Math.random()*9000,(int) (1+Math.random()*10)));
			});
		};
	}

}
