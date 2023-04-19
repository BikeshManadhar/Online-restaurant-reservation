package com.bikku.onlinerestaurantreservation;

public class MainModel1 {

    String name, description, furl;
    double price;

    MainModel1()
    {

    }

    public MainModel1(String name, String description, String furl, double price) {
        this.name = name;
        this.description = description;
        this.furl = furl;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getFurl() {
        return furl;
    }

    public void setFurl(String furl) {
        this.furl = furl;
    }

    public double getPrice() {return price;}

    public void setPrice(double price) {
        this.price = price;
    }
}
