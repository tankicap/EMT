package com.example.lab1b.service;

import com.example.lab1b.model.Host;
import com.example.lab1b.model.Housing;
import com.example.lab1b.model.dto.HostDto;
import com.example.lab1b.model.dto.HousingDto;

import java.util.List;
import java.util.Optional;

public interface HostService {
    List<Host> listAll();
    Optional<Host> findById(Long id);
    Optional<Host> create(String name, String surname, Long countryId);
    Optional<Host> delete(Long id);
    Optional<Host> edit(Long id,String name, String surname, Long countryId);
}
