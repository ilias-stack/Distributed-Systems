package org.sid.billingservice.repositories;

import org.sid.billingservice.entities.Bill;
import org.sid.billingservice.entities.ProductItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.Collection;
import java.util.List;

@RepositoryRestResource
public interface BillRepository extends JpaRepository<Bill,Long> {
//    Collection<ProductItem> findByBillId(Long id);
}
