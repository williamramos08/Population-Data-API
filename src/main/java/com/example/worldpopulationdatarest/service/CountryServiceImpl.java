package com.example.worldpopulationdatarest.service;

import com.example.worldpopulationdatarest.model.Country;
import com.example.worldpopulationdatarest.service.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.util.Arrays;
import java.util.List;

@Service
public class CountryServiceImpl implements CountryService {

    private final RestTemplate restTemplate;

    @Autowired
    public CountryServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public List<Country> getAllCountries() {
        String url = "https://restcountries.com/v3.1/lang/eng";
        Country[] countries = restTemplate.getForObject(url, Country[].class);
        return Arrays.asList(countries);
    }
}
