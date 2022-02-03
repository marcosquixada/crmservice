package com.crm.agile.monkeys.api.service;

import com.crm.agile.monkeys.api.model.Customer;
import com.crm.agile.monkeys.api.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    public Optional<Customer> getCustomerById(long id) {
        return this.customerRepository.findById(id);
    }

    public Customer createUpdateCustomer(Customer customer) {
        return this.customerRepository.save(customer);
    }

    public Page<Customer> findAll(Pageable pageable) {
        return this.customerRepository.findAll(pageable);
    }

    public void deleteCustomer(Customer customer){
        this.customerRepository.delete(customer);
    }

    public Optional<Customer> findById(Long id) {
        return this.customerRepository.findById(id);
    }
}
