package com.poly.repository;

import com.poly.model.Record;
import com.poly.model.Staff;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;

public interface RecordRepository extends PagingAndSortingRepository<Record, Long> {

Page<Record> findAllByReasonContains(String reason, Pageable pageable);

}
