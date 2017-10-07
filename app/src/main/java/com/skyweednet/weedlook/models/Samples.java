package com.skyweednet.weedlook.models;

/**
 * Created by osx on 05-10-17.
 */

public class Samples {

    private String number, category;
    private boolean taste;


    public Samples(String number, String category, boolean taste) {
        this.number = number;
        this.category = category;
        this.taste = taste;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public boolean isTaste() {
        return taste;
    }

    public void setTaste(boolean taste) {
        this.taste = taste;
    }

    public Samples() {






    }
}
