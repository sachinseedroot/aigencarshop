package com.aigen.carshop.db.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class carmodel {


    @Expose
    @SerializedName("id")
    private String id;
    @Expose
    @SerializedName("car_name")
    private String car_name;
    @Expose
    @SerializedName("car_type")
    private String car_type;
    @Expose
    @SerializedName("manufactured_date")
    private String manufactured_date;
    @Expose
    @SerializedName("insurance")
    private String insurance;
    @Expose
    @SerializedName("color")
    private String color;
    @Expose
    @SerializedName("km_driven")
    private String km_driven;

    @Expose
    @SerializedName("fuel_type")
    private String fuel_type;
    @Expose
    @SerializedName("city_address")
    private String city_address;

    @Expose
    @SerializedName("added_on")
    private String added_on;

    @Expose
    @SerializedName("price")
    private String price;

    @Expose
    @SerializedName("image_array")
    private String image_array;



    @Override
    public String toString() {
        return super.toString();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getKm_driven() {
        return km_driven;
    }

    public void setKm_driven(String km_driven) {
        this.km_driven = km_driven;
    }

    public String getCity_address() {
        return city_address;
    }

    public void setCity_address(String city_address) {
        this.city_address = city_address;
    }

    public String getFuel_type() {
        return fuel_type;
    }

    public void setFuel_type(String fuel_type) {
        this.fuel_type = fuel_type;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getInsurance() {
        return insurance;
    }

    public void setInsurance(String insurance) {
        this.insurance = insurance;
    }

    public String getManufactured_date() {
        return manufactured_date;
    }

    public void setManufactured_date(String manufactured_date) {
        this.manufactured_date = manufactured_date;
    }

    public String getCar_type() {
        return car_type;
    }

    public void setCar_type(String car_type) {
        this.car_type = car_type;
    }

    public String getCar_name() {
        return car_name;
    }

    public void setCar_name(String car_name) {
        this.car_name = car_name;
    }

    public String getAdded_on() {
        return added_on;
    }

    public void setAdded_on(String added_on) {
        this.added_on = added_on;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getImage_array() {
        return image_array;
    }

    public void setImage_array(String image_array) {
        this.image_array = image_array;
    }
}
