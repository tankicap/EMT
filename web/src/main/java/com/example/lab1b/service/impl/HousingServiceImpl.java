package com.example.lab1b.service.impl;

import com.example.lab1b.model.Category;
import com.example.lab1b.model.Housing;
import com.example.lab1b.model.dto.HousingDto;
import com.example.lab1b.model.exception.InvalidHostIdExcetion;
import com.example.lab1b.model.exception.InvalidHousingIdException;
import com.example.lab1b.model.exception.NoAvailableNights;
import com.example.lab1b.repository.HousingRepository;
import com.example.lab1b.repository.view.MaterialiView;
import com.example.lab1b.service.HostService;
import com.example.lab1b.service.HousingService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HousingServiceImpl implements HousingService {

    private final HousingRepository housingRepository;
    private final HostService hostService;
    private final MaterialiView materialiView;

    public HousingServiceImpl(HousingRepository housingRepository, HostService hostService, MaterialiView materialiView) {
        this.housingRepository = housingRepository;
        this.hostService = hostService;
        this.materialiView = materialiView;
    }

    @Override
    public List<Housing> listAll() {
        return housingRepository.findAll();
    }

    @Override
    public Optional<Housing> findById(Long id) {
        return Optional.of(housingRepository.findById(id).orElseThrow(InvalidHousingIdException::new));
    }

    @Override
    public Optional<Housing> create(String name, Category category, Long hostId, Integer numRooms) {
        return Optional.of(housingRepository.save(new Housing(name,category,hostService.findById(hostId).orElseThrow(InvalidHostIdExcetion::new),numRooms,false)));
    }

    @Override
    public Optional<Housing> delete(Long id) {
        Housing housing=findById(id).orElseThrow(InvalidHousingIdException::new);
        housingRepository.delete(housing);
        return Optional.of(housing);
    }

    @Override
    public Optional<Housing> edit(Long id, String name, Category category, Long hostId, Integer numRooms) {
        Housing housing=findById(id).orElseThrow(InvalidHousingIdException::new);
        housing.setName(name);
        housing.setHost(hostService.findById(id).orElseThrow(InvalidHostIdExcetion::new));
        housing.setCategory(category);
        housing.setNumRooms(numRooms);
        return Optional.of(housingRepository.save(housing));

    }

    @Override
    public Optional<Housing> bookmark(Long id) {
        Housing housing=findById(id).orElseThrow(InvalidHousingIdException::new);
        if(housing.getNumRooms()==0)
        {
            throw new NoAvailableNights();
        }
        housing.setNumRooms(housing.getNumRooms()-1);
        return Optional.of(housingRepository.save(housing));
    }

    @Override
    public void refreshMaterializedView() {
        materialiView.refreshMaterializedView();
    }

    @Override
    public Optional<Housing> rent(Long housingId) {
        Housing housing = findById(housingId).get();

        if(!housing.getIsRent() || housing.getNumRooms()>0) {
            housing.setIsRent(true);
            housing.setNumRooms(housing.getNumRooms()-1);
            return Optional.of(housingRepository.save(housing));
        }

        throw new NoAvailableNights();
    }


}
