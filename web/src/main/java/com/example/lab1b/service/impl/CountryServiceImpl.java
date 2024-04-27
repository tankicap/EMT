package com.example.lab1b.service.impl;

import com.example.lab1b.model.Country;
import com.example.lab1b.model.exception.InvalidCountryIdException;
import com.example.lab1b.repository.CountryRepository;
import com.example.lab1b.service.CountryService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CountryServiceImpl implements CountryService {
    private final CountryRepository countryRepository;

    public CountryServiceImpl(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }


    @Override
    public List<Country> listAll() {
        return countryRepository.findAll();
    }

    @Override
    public Optional<Country> findById(Long id) {
        return Optional.of(countryRepository.findById(id).orElseThrow(InvalidCountryIdException::new));
    }

    @Override
    public Optional<Country> create(String name, String continent) {
        return Optional.of(countryRepository.save(new Country(name,continent)));
    }

    @Override
    public Optional<Country> delete(Long id) {
        Country country=findById(id).orElseThrow(InvalidCountryIdException::new);
        countryRepository.delete(country);
        return Optional.of(country);
    }

    @Override
    public Optional<Country> edit(Long id, String name, String continent) {
        Country country=findById(id).orElseThrow(InvalidCountryIdException::new);
        country.setContinent(continent);
        country.setName(name);
        return Optional.of(countryRepository.save(country));
    }
}
