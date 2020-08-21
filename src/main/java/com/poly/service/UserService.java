package com.poly.service;

import com.poly.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface UserService {
    Iterable<User> findAll();

    Optional<User> findById(Long id);

    void save(User user);

    void delete(Long id);


    User findByUserName(String userName);
}
