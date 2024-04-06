package com.example.worldpopulationdatarest.service;

import com.example.worldpopulationdatarest.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class PopulationDataService {
    @Autowired
    private CountryService countryService;


    private final String API_BASE_URL = "https://restcountries.com/v3.1/lang/eng";

    public List<Country> getAllCountries() {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Map<String, Country>> responseEntity = restTemplate.exchange(
                API_BASE_URL + "all",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<Map<String, Country>>() {}
        );

        if (responseEntity != null && responseEntity.hasBody()) {
            Map<String, Country> countryMap = responseEntity.getBody();
            if (countryMap != null) {
                return new ArrayList<>(countryMap.values());
            }
        }
        return new ArrayList<>();
    }



    public PopulationStats calculatePopulationStats() {
        List<Country> countries = countryService.getAllCountries();

        // Calculate mean population density
        double totalDensity = countries.stream()
                .mapToDouble(country -> (double) country.getPopulation() / country.getArea())
                .sum();
        double meanDensity = totalDensity / countries.size();

        // Calculate median population density
        double[] densities = countries.stream()
                .mapToDouble(country -> (double) country.getPopulation() / country.getArea())
                .sorted()
                .toArray();
        double medianDensity = calculateMedian(densities);

        // Calculate standard deviation of population density
        double sumOfSquares = countries.stream()
                .mapToDouble(country -> Math.pow((double) country.getPopulation() / country.getArea() - meanDensity, 2))
                .sum();
        double standardDeviation = Math.sqrt(sumOfSquares / countries.size());

        // Count UN members and Euro users
        long unMemberCount = countUNMembers(countries);
        long euroUserCount = countEuroUsers(countries);

        return new PopulationStats(meanDensity, medianDensity, standardDeviation, unMemberCount, euroUserCount);
    }


    public double calculatePopulationDensity(Country country) {
        return country.getPopulation() / country.getArea();
    }

    public List<PopulationData> calculatePopulationDensities(List<Country> countries) {
        return countries.stream()
                .map(country -> {
                    String countryName = null;
                    if (country.getName() != null) {
                        for (String languageCode : country.getName().keySet()) {
                            Name name = country.getName().get(languageCode);
                            if (name != null && "eng".equals(languageCode)) {
                                Map<String, NativeName> nativeName = name.getNativeName();
                                if (nativeName != null && nativeName.containsKey(languageCode)) {
                                    countryName = nativeName.get(languageCode).getCommon("eng");
                                    break;
                                }
                            }
                        }
                    } else {
                        countryName = "Unknown"; // or handle this case as needed
                    }

                    return new PopulationData(countryName, calculatePopulationDensity(country));
                })
                .collect(Collectors.toList());
    }

    public double calculateMean(List<PopulationData> populationDataList) {
        double sum = populationDataList.stream().mapToDouble(PopulationData::getPopulationDensity).sum();
        return sum / populationDataList.size();
    }

//    public double calculateMedian(List<PopulationData> populationDataList) {
//        List<Double> densities = populationDataList.stream().map(PopulationData::getPopulationDensity).sorted().collect(Collectors.toList());
//        int size = densities.size();
//        return size % 2 == 0 ? (densities.get(size / 2 - 1) + densities.get(size / 2)) / 2 : densities.get(size / 2);
//    }

    private double calculateMedian(double[] arr) {
        int n = arr.length;
        Arrays.sort(arr);

        if (n % 2 == 0) {
            // If the number of elements is even, return the average of the two middle elements
            return (arr[n / 2 - 1] + arr[n / 2]) / 2.0;
        } else {
            // If the number of elements is odd, return the middle element
            return arr[n / 2];
        }
    }


    public double calculateStandardDeviation(List<PopulationData> populationDataList) {
        double mean = calculateMean(populationDataList);
        double sumSquaredDiff = populationDataList.stream()
                .mapToDouble(data -> Math.pow(data.getPopulationDensity() - mean, 2))
                .sum();
        return Math.sqrt(sumSquaredDiff / populationDataList.size());
    }

    public long countUNMembers(List<Country> countries) {
        return countries.stream().filter(country -> country.getUNMember()).count();
    }

    public long countEuroUsers(List<Country> countries) {
        return countries.stream()
                .filter(country -> {
                    Map<String, Currency> currencies = country.getCurrencies();
                    return currencies != null && currencies.values().stream()
                            .anyMatch(currency -> "Euro".equals(currency.getName()));
                })
                .count();
    }

}
