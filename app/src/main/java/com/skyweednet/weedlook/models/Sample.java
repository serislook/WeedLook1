package com.skyweednet.weedlook.models;

import java.io.Serializable;

/**
 * Created by osx on 05-10-17.
 */

public class Sample implements Serializable {

    private String image, key, name, category, flowering_time;

    public Sample() {
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getFlowering_time() {
        return flowering_time;
    }

    public void setFlowering_time(String flowering_time) {
        this.flowering_time = flowering_time;
    }
}