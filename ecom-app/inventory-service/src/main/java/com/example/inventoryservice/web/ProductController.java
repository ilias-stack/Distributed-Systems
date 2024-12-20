package com.example.inventoryservice.web;

import com.example.inventoryservice.entities.Product;
import com.example.inventoryservice.repositories.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/products")
@AllArgsConstructor
@EnableMethodSecurity(prePostEnabled = true)
public class ProductController {
    private ProductRepository productRepository;

    @GetMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public List<Product> allProducts(){
        return productRepository.findAll();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('USER')")
    public Product getProductById(@PathVariable String id){
        return productRepository.findById(id).orElse(null);
    }

}
