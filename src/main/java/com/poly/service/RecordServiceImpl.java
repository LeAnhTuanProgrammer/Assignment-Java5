package com.poly.service;

import com.poly.model.Depart;
import com.poly.model.Record;
import com.poly.model.Staff;
import com.poly.repository.DepartRepository;
import com.poly.repository.RecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;


public class RecordServiceImpl implements RecordService{
    @Autowired
    private RecordRepository recordRepository;



    @Override
    public Page<Record> findAll(Pageable pageable) {
        return recordRepository.findAll(pageable);
    }

    @Override
    public Optional<Record> findById(Long id) {
        return recordRepository.findById(id);
    }

    @Override
    public void save(Record record) {
        recordRepository.save(record);
    }

    @Override
    public void delete(Long id) {
        recordRepository.deleteById(id);
    }

    @Override
    public Page<Record> findAllByReasonContains(String reason, Pageable pageable) {

            return recordRepository.findAllByReasonContains(reason, pageable);

    }


}
