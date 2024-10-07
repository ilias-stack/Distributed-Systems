package org.sid.billingservice;

import org.sid.billingservice.entities.Bill;
import org.sid.billingservice.entities.ProductItem;
import org.sid.billingservice.feign.CustomerRestClient;
import org.sid.billingservice.feign.ProductRestClient;
import org.sid.billingservice.models.Customer;
import org.sid.billingservice.models.Product;
import org.sid.billingservice.repositories.BillRepository;
import org.sid.billingservice.repositories.ProductItemRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.hateoas.PagedModel;

import java.util.Date;
import java.util.Random;

@SpringBootApplication
@EnableFeignClients
public class BillingServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(BillingServiceApplication.class, args);
    }

    @Bean
    CommandLineRunner start(BillRepository billRepository
            , ProductItemRepository productItemRepository
            , CustomerRestClient customerRestClient
            , ProductRestClient productRestClient){
        return args -> {
            Random rand = new Random();
            Customer customer = customerRestClient.getCustomerById(1L);
            Bill bill = billRepository.save(new Bill(null,new Date(),null,customer.getId(),customer));

            PagedModel<Product> productPagedModel = productRestClient.getPageProducts(0,10);
            productPagedModel.forEach(product -> {

                ProductItem productItem = new
                        ProductItem(null,
                        rand.nextInt(20)+1,
                        (float) product.getPrice(),
                        product.getId(),
                        bill,product);
                productItemRepository.save(productItem);
            });

        };
    }

}
