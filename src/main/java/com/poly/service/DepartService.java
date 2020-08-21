package com.poly.service;

import com.poly.model.Depart;

import java.util.Optional;

public interface DepartService {
    Iterable<Depart> findAll();

    Optional<Depart> findById(Long id);

    void save(Depart depart);



    void delete(Long id);
}
