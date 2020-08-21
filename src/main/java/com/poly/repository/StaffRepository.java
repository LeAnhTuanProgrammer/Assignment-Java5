package com.poly.repository;

import com.poly.model.Staff;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface StaffRepository extends PagingAndSortingRepository<Staff, Long> {
    //    Page<Staff> findAll(Pageable pageable);
    Page<Staff> findAllByNameContains(String name, Pageable pageable);

    Staff findAllByNameContains(String name);

    Staff findByName(String name);
}