package org.sid.inventory_service.repositories;

import org.sid.inventory_service.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RestResource;

@RestResource
public interface ProductRepository extends JpaRepository<Product,Long> {
}
