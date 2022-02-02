package com.crm.agile.monkeys.api.service;

import com.crm.agile.monkeys.api.model.User;
import com.crm.agile.monkeys.api.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public Optional<User> getUserById(long id) {
        return this.userRepository.findById(id);
    }

    public User createUpdateUser(User user) {
        return this.userRepository.save(user);
    }

    public Page<User> findAll(Pageable pageable) {
        return this.userRepository.findAll(pageable);
    }

    public void deleteCustomer(User user){
        this.userRepository.delete(user);
    }

    public User changeAdminStatus(User user, String newStatus){
        user.setStatus(newStatus);
        return this.createUpdateUser(user);
    }
}
