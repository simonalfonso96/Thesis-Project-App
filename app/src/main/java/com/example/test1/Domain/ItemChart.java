package com.example.test1.Domain;

public class ItemChart {
    String name;
    int image;
    float progressBarValue;



    public ItemChart(String name, int image ) {
        this.name = name;
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public float getProgressBarValue() {
        return progressBarValue;
    }

    public void setProgressBarValue(float progressBarValue) {
        this.progressBarValue = progressBarValue;
    }
}
