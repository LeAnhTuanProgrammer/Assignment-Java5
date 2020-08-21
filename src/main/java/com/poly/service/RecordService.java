package com.poly.service;

import com.poly.model.Record;
import com.poly.model.Staff;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface RecordService {
    Page<Record> findAll(Pageable pageable);

    Optional<Record> findById(Long id);

    void save(Record record);

    void delete(Long id);

    Page<Record> findAllByReasonContains(String reason, Pageable pageable);
}
