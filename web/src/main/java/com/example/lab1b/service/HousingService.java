package com.example.lab1b.service;

import com.example.lab1b.model.Category;
import com.example.lab1b.model.Housing;
import com.example.lab1b.model.dto.HousingDto;

import java.util.List;
import java.util.Optional;

public interface HousingService {
    List<Housing> listAll();
    Optional<Housing> findById(Long id);
    Optional<Housing> create(String name, Category category, Long hostId, Integer numRooms);
    Optional<Housing> delete(Long id);
    Optional<Housing> edit(Long id,String name, Category category, Long hostId, Integer numRooms);
    Optional<Housing> bookmark(Long id);
    void refreshMaterializedView();

    Optional<Housing> rent(Long id);

}
