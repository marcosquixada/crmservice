package com.crm.agile.monkeys.api.repository;

import com.crm.agile.monkeys.api.model.Customer;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface CustomerRepository extends PagingAndSortingRepository<Customer, Long> {

}
