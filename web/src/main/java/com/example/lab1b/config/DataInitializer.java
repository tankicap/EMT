package com.example.lab1b.config;

import com.example.lab1b.model.Category;
import com.example.lab1b.model.Country;
import com.example.lab1b.model.Host;
import com.example.lab1b.model.Housing;
import com.example.lab1b.repository.CountryRepository;
import com.example.lab1b.repository.HostRepository;
import com.example.lab1b.repository.HousingRepository;
import jakarta.annotation.PostConstruct;
import lombok.Getter;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
@Getter
public class DataInitializer {

    public List<Host> hosts = new ArrayList<>();
    public List<Housing> housings = new ArrayList<>();
    public List<Country> countries = new ArrayList<>();

    private final HousingRepository housingRepository;
    private final HostRepository hostRepository;
    private final CountryRepository countryRepository;

    public DataInitializer(HousingRepository housingRepository, HostRepository hostRepository, CountryRepository countryRepository) {
        this.housingRepository = housingRepository;
        this.hostRepository = hostRepository;
        this.countryRepository = countryRepository;
    }

    @PostConstruct
    public void init() {

        //Countries init
        countries.add(new Country("Belgium", "Europe"));
        countries.add(new Country("USA", "North America"));
        countries.add(new Country("Chile", "South America"));
        countries.add(new Country("Japan", "Asia"));

        countryRepository.saveAll(countries);

        //Host
        Host host1 = new Host("Agatha", "Christie", countries.get((int) (Math.random() * countries.size())));
        Host host2 = new Host("Georges", "Simenon", countries.get((int) (Math.random() * countries.size())));
        Host host3 = new Host("William", "Shakespeare", countries.get((int) (Math.random() * countries.size())));
        Host host4 = new Host("Sidney", "Sidney", countries.get((int) (Math.random() * countries.size())));
        Host host5 = new Host("Enid", "Blyton", countries.get((int) (Math.random() * countries.size())));
        hosts.add(host1);
        hosts.add(host2);
        hosts.add(host3);
        hosts.add(host4);
        hosts.add(host5);

        hostRepository.saveAll(hosts);

        //Housings

        Housing housing1 = new Housing("St.Agatha", Category.HOUSE ,hosts.get((int) (Math.random() * hosts.size())),3,true);
        Housing housing2 =new Housing("Mike's Apartments", Category.FLAT ,hosts.get((int) (Math.random() * hosts.size())),5,false);
        Housing housing3 = new Housing("DownTheRoad", Category.APARTMENT ,hosts.get((int) (Math.random() * hosts.size())),10,false);
        Housing housing4 = new Housing("RogerThat", Category.MOTEL ,hosts.get((int) (Math.random() * hosts.size())),2,true);
        Housing housing5 = new Housing("EmilyHome", Category.HOTEL ,hosts.get((int) (Math.random() * hosts.size())),0,true);

        housings.add(housing1);
        housings.add(housing2);
        housings.add(housing3);
        housings.add(housing4);
        housings.add(housing5);

        housingRepository.saveAll(housings);
    }
}