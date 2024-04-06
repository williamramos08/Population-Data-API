package com.example.worldpopulationdatarest.model;

public class Currency {
    private String name;
    private String symbol;

    // Constructor
    public Currency(String name, String symbol) {
        this.name = name;
        this.symbol = symbol;
    }

    // Getters and setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }
}
