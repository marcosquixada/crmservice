package com.crm.agile.monkeys.api.repository;

import com.crm.agile.monkeys.api.model.User;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface UserRepository extends PagingAndSortingRepository<User, Long> {

}
