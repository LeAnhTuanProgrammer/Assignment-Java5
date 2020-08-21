package com.poly.repository;

import com.poly.model.Depart;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface DepartRepository extends PagingAndSortingRepository<Depart, Long> {

}