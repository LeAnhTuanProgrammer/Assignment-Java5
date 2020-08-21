package com.poly.service;

import com.poly.model.Depart;
import com.poly.repository.DepartRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;


public class DepartServiceImpl implements DepartService{
    @Autowired
    private DepartRepository departRepository;

    @Override
    public Iterable<Depart> findAll() {
        return  departRepository.findAll();
    }

    @Override
    public Optional<Depart> findById(Long id) {
        return departRepository.findById(id);
    }

    @Override
    public void save(Depart depart) {
        departRepository.save(depart);
    }



    @Override
    public void delete(Long id) {
        departRepository.deleteById(id);
    }
}
