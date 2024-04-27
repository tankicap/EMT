package com.example.lab1b.service;

import com.example.lab1b.model.Country;
import com.example.lab1b.model.Housing;
import com.example.lab1b.model.dto.CountryDto;
import com.example.lab1b.model.dto.HousingDto;

import java.util.List;
import java.util.Optional;

public interface CountryService {
    List<Country> listAll();
    Optional<Country> findById(Long id);
    Optional<Country> create(String name, String continent);
    Optional<Country> delete(Long id);
    Optional<Country> edit(Long id,String name, String continent);
}
