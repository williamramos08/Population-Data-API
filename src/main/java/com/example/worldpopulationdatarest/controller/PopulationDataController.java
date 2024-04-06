package com.example.worldpopulationdatarest.controller;

import com.example.worldpopulationdatarest.model.*;
import com.example.worldpopulationdatarest.service.PopulationDataService;
import com.example.worldpopulationdatarest.service.CountryService; // Import CountryService
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/population")
public class PopulationDataController {

    private final PopulationDataService populationDataService;
    private final CountryService countryService; // Autowire CountryService

    @Autowired
    public PopulationDataController(PopulationDataService populationDataService, CountryService countryService) {
        this.populationDataService = populationDataService;
        this.countryService = countryService; // Assign CountryService
    }

    @GetMapping("/density")
    public ResponseEntity<List<PopulationData>> getPopulationDensity() {
        List<Country> countries = countryService.getAllCountries();
        List<PopulationData> populationDataList = new ArrayList<>();

        for (Country country : countries) {
            double density = populationDataService.calculatePopulationDensity(country);
            String countryName = "Unknown";
            if (country.getName() != null) {
                for (String languageCode : country.getName().keySet()) {
                    Name name = country.getName().get(languageCode);
                    countryName = name.getCommon();

                    PopulationData populationData = new PopulationData(countryName, density);

                    populationDataList.add(populationData);
                    break;
                }
            }

        }

        return new ResponseEntity<>(populationDataList, HttpStatus.OK);
    }



    @GetMapping("/stats")
    public ResponseEntity<PopulationStats> getPopulationStats() {
        PopulationStats populationStats = populationDataService.calculatePopulationStats();
        return new ResponseEntity<>(populationStats, HttpStatus.OK);
    }

    @GetMapping("/un-members")
    public ResponseEntity<Long> countUNMembers() {
        List<Country> countries = countryService.getAllCountries(); // Use CountryService
        long count = populationDataService.countUNMembers(countries);
        return new ResponseEntity<>(count, HttpStatus.OK);
    }

    @GetMapping("/euro-users")
    public ResponseEntity<Long> countEuroUsers() {
        List<Country> countries = countryService.getAllCountries(); // Assuming you have a service to retrieve countries
        long count = populationDataService.countEuroUsers(countries);
        return new ResponseEntity<>(count, HttpStatus.OK);
    }

}
