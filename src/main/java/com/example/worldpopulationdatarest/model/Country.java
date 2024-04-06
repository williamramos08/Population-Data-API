package com.example.worldpopulationdatarest.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Map;

public class Country {
    private String common;
    private String official;
//    private NativeName nativeName;
    private Map<String, Name> name;

    private int population;
    private double area;
    private Map<String, Currency> currencies;

    private boolean unMember;
    private double density;

    // Constructor, getters, and setters


    public Map<String, Name> getName() {
        return  name;
    }

    public void setName(Map<String, Name> name) {
        this.name = name;
    }

    public String getCommon() {
        return common;
    }

    public void setCommon(String common) {
        this.common = common;
    }

    public String getOfficial() {
        return official;
    }

    public void setOfficial(String official) {
        this.official = official;
    }

    public int getPopulation() {
        return population;
    }

    public void setPopulation(int population) {
        this.population = population;
    }

    public double getArea() {
        return area;
    }

    public void setArea(double area) {
        this.area = area;
    }

    public Map<String, Currency> getCurrencies() {
        return currencies;
    }

    public void setCurrencies(Map<String, Currency> currencies) {
        this.currencies = currencies;
    }

    public boolean isUNMember() {
        return unMember;
    }

    public void setUNMember(boolean unMember) {
        this.unMember = unMember;
    }

    public boolean getUNMember() {
        return unMember;
    }


    public boolean isUnMember() {
        return unMember;
    }

    public double getDensity() {
        return density;
    }

    public void setDensity(double density) {
        this.density = density;
    }
}
