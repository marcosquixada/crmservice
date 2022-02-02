package com.crm.agile.monkeys.api.controller;

import com.crm.agile.monkeys.api.model.User;
import com.crm.agile.monkeys.api.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@CrossOrigin(origins = "*")
public class UserController {
    private static final Logger log = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @GetMapping
    public Page<User> list(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "3") int size){
        log.info("Listing users: {}", "");
        Pageable pageable = PageRequest.of(page, size);
        return this.userService.findAll(pageable);
    }
}
