package com.skyweednet.weedlook.models;

import java.io.Serializable;

/**
 * Created by osx on 07-02-18.
 */

public class Score implements Serializable{

    private String key,owner,samplekey,sampleowner;
    private Double average;

    public Score() {
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

    public String getSamplekey() {
        return samplekey;
    }

    public void setSamplekey(String samplekey) {
        this.samplekey = samplekey;
    }

    public Double getAverage() {
        return average;
    }

    public void setAverage(Double average) {
        this.average = average;
    }

    public String getSampleowner() {
        return sampleowner;
    }

    public void setSampleowner(String sampleowner) {
        this.sampleowner = sampleowner;
    }
}
