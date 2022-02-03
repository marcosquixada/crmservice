package com.crm.agile.monkeys.api.controller;

import com.crm.agile.monkeys.api.dto.CustomerDto;
import com.crm.agile.monkeys.api.model.Customer;
import com.crm.agile.monkeys.api.service.CustomerService;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.Optional;


@RestController
@RequestMapping("/customers")
@CrossOrigin(origins = "*")
public class CustomerController {

    private static final Logger log = LoggerFactory.getLogger(CustomerController.class);

    @Autowired
    private CustomerService customerService;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping
    public Page<Customer> list(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "3") int size){
        log.info("Listing customers: {}", "");
        Pageable pageable = PageRequest.of(page, size);
        return this.customerService.findAll(pageable);
    }

    @GetMapping("/{id}")
    public CustomerDto getCustomer(@PathVariable Long id){
        //log.info("Listing customers: {}", "");
        Customer customer = customerService.findById(id).get();
        CustomerDto dto = CustomerDto.builder()
                .name(customer.getName())
                .build();
        return dto;
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<CustomerDto> deletePost(@PathVariable Long id) {
        Optional<Customer> customer = customerService.findById(id);
        if (!customer.isPresent())
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        customerService.deleteCustomer(customer.get());
        return new ResponseEntity<CustomerDto>(convertToDto(customer.get()), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    ResponseEntity<CustomerDto> updateCustomer(@RequestBody CustomerDto customerDto, @PathVariable Long id) {
        Optional<Customer> customerOptional = customerService.findById(id);
        if (!customerOptional.isPresent())
            return new ResponseEntity<CustomerDto>(HttpStatus.NOT_FOUND);
        try {
            Customer customer = convertToEntity(customerDto);
            customer = customerService.createOrUpdateCustomer(customer);
            CustomerDto dto = CustomerDto.builder()
                    .name(customer.getName())
                    .surname(customer.getSurname())
                    .build();

            return new ResponseEntity<CustomerDto>(dto, HttpStatus.OK);
        } catch (ParseException e) {
            e.printStackTrace();
            return new ResponseEntity<CustomerDto>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    private CustomerDto convertToDto(Customer customer) {
        CustomerDto customerDto = modelMapper.map(customer, CustomerDto.class);
        return customerDto;
    }

    private Customer convertToEntity(CustomerDto customerDto) throws ParseException {
        Customer customer = modelMapper.map(customerDto, Customer.class);

        if (customerDto.getId() != null) {
            Customer oldCustomer = customerService.getCustomerById(customerDto.getId()).get();
            customer.setName(oldCustomer.getName());
            customer.setSurname(oldCustomer.getSurname());
        }
        return customer;
    }
}