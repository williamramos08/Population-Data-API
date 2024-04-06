package com.example.worldpopulationdatarest.model;

public class PopulationStats {
    private double meanDensity;
    private double medianDensity;
    private double standardDeviation;
    private long unMemberCount;
    private long euroUserCount;

    // Constructors
    public PopulationStats() {
    }

    public PopulationStats(double meanDensity, double medianDensity, double standardDeviation, long unMemberCount, long euroUserCount) {
        this.meanDensity = meanDensity;
        this.medianDensity = medianDensity;
        this.standardDeviation = standardDeviation;
        this.unMemberCount = unMemberCount;
        this.euroUserCount = euroUserCount;
    }

    // Getters and setters
    public double getMeanDensity() {
        return meanDensity;
    }

    public void setMeanDensity(double meanDensity) {
        this.meanDensity = meanDensity;
    }

    public double getMedianDensity() {
        return medianDensity;
    }

    public void setMedianDensity(double medianDensity) {
        this.medianDensity = medianDensity;
    }

    public double getStandardDeviation() {
        return standardDeviation;
    }

    public void setStandardDeviation(double standardDeviation) {
        this.standardDeviation = standardDeviation;
    }

    public long getUnMemberCount() {
        return unMemberCount;
    }

    public void setUnMemberCount(long unMemberCount) {
        this.unMemberCount = unMemberCount;
    }

    public long getEuroUserCount() {
        return euroUserCount;
    }

    public void setEuroUserCount(long euroUserCount) {
        this.euroUserCount = euroUserCount;
    }
}
