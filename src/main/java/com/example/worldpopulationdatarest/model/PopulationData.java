package com.example.worldpopulationdatarest.model;


public class PopulationData {
    private String countryName;
    private Country country;
    private double populationDensity;

    // Constructor
    public PopulationData(String country, double populationDensity) {
        this.countryName = country;
        this.populationDensity = populationDensity;
    }

    // Getters and setters
    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public double getPopulationDensity() {
        return populationDensity;
    }

    public void setPopulationDensity(double populationDensity) {
        this.populationDensity = populationDensity;
    }
}
