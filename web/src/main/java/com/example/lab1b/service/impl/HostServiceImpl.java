package com.example.lab1b.service.impl;

import com.example.lab1b.model.Host;
import com.example.lab1b.model.Housing;
import com.example.lab1b.model.exception.InvalidCountryIdException;
import com.example.lab1b.model.exception.InvalidHostIdExcetion;
import com.example.lab1b.repository.HostRepository;
import com.example.lab1b.service.CountryService;
import com.example.lab1b.service.HostService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HostServiceImpl implements HostService {
    private final HostRepository hostRepository;
    private final CountryService countryService;

    public HostServiceImpl(HostRepository hostRepository, CountryService countryService) {
        this.hostRepository = hostRepository;
        this.countryService = countryService;
    }

    @Override
    public List<Host> listAll() {
        return hostRepository.findAll();
    }

    @Override
    public Optional<Host> findById(Long id) {
        return Optional.of(hostRepository.findById(id).orElseThrow(InvalidHostIdExcetion::new));
    }

    @Override
    public Optional<Host> create(String name, String surname, Long countryId) {
        return Optional.of(hostRepository.save(new Host(name,surname,countryService.findById(countryId).orElseThrow(InvalidCountryIdException::new))));
    }

    @Override
    public Optional<Host> delete(Long id) {
        Host host=findById(id).orElseThrow(InvalidHostIdExcetion::new);
        hostRepository.delete(host);
        return Optional.of(host);
    }

    @Override
    public Optional<Host> edit(Long id, String name, String surname, Long countryId) {
        Host host=findById(id).orElseThrow(InvalidHostIdExcetion::new);
        host.setCountry(countryService.findById(countryId).orElseThrow(InvalidCountryIdException::new));
        host.setName(name);
        host.setSurname(surname);
        return Optional.of(hostRepository.save(host));
    }
}
