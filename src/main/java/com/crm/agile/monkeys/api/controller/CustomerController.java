package com.crm.agile.monkeys.api.controller;

import com.crm.agile.monkeys.api.model.Customer;
import com.crm.agile.monkeys.api.service.CustomerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/customers")
@CrossOrigin(origins = "*")
public class CustomerController {

    private static final Logger log = LoggerFactory.getLogger(CustomerController.class);

    @Autowired
    private CustomerService customerService;

    @GetMapping
    public Page<Customer> list(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "3") int size){
        log.info("Listing customers: {}", "");
        Pageable pageable = PageRequest.of(page, size);
        return this.customerService.findAll(pageable);
    }
}