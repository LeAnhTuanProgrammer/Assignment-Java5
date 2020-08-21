package com.poly.service;

import com.poly.model.Staff;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface StaffService {
    Page<Staff> findAll(Pageable pageable);

    Optional<Staff> findById(Long id);

    Staff findByName(String name);

    void save(Staff staff);

    void delete(Long id);

    Page<Staff> findAllByNameContains(String name, Pageable pageable);
}
