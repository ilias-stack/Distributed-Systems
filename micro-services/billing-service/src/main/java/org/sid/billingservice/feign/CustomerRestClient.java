package org.sid.billingservice.feign;

import jakarta.ws.rs.QueryParam;
import org.sid.billingservice.models.Customer;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.hateoas.PagedModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "CUSTOMER-SERVICE")
public interface CustomerRestClient {
    @GetMapping(path = "/customers/{id}")
    public Customer getCustomerById(@PathVariable Long id);

    @GetMapping(path = "/customers")
    PagedModel<Customer> getPageCustomers(@RequestParam("page") int page,
                                        @RequestParam("size") int size);
}
