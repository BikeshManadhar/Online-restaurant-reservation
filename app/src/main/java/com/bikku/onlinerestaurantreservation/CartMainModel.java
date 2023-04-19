package com.bikku.onlinerestaurantreservation;

public class CartMainModel {
    String name, email, phone, quantity, furl;

    CartMainModel()
    {

    }

    public CartMainModel(String name, String email, String phone, String quantity, String furl) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.quantity = quantity;
        this.furl = furl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getFurl() {
        return furl;
    }

    public void setFurl(String furl) {
        this.furl = furl;
    }
}
