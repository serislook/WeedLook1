package com.skyweednet.weedlook.models;

import java.util.Map;

/**
 * Created by osx on 16-12-17.
 */

public class Tasting {

    private String key, owner, name, sampleKey;
    private Map<String, Double> characteristics;
    private double average;

    public Tasting() {
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSampleKey() {
        return sampleKey;
    }

    public void setSampleKey(String sampleKey) {
        this.sampleKey = sampleKey;
    }

    public Map<String, Double> getCharacteristics() {
        return characteristics;
    }

    public void setCharacteristics(Map<String, Double> characteristics) {
        this.characteristics = characteristics;
    }

    public double getAverage() {
        return average;
    }

    public void setAverage(double average) {
        this.average = average;
    }
}
