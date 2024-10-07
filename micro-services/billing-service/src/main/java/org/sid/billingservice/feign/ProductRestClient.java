package org.sid.billingservice.feign;

import jakarta.ws.rs.QueryParam;
import org.sid.billingservice.models.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.hateoas.PagedModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "INVENTORY-SERVICE")
public interface ProductRestClient {
    @GetMapping(path = "/products/{id}")
    Product getProductById(@PathVariable Long id);

    @GetMapping(path = "/products")
    PagedModel<Product> getPageProducts(@RequestParam("page") int page,
                                        @RequestParam("size") int size);


}
