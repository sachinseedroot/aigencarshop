package com.aigen.carshop.db.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import io.reactivex.annotations.NonNull;

@Entity(tableName = "carmodelTable")
public class carmodel {


    @NonNull
    @PrimaryKey
    public int id;
   
    @ColumnInfo(name = "car_name")
    private String car_name;
   
    @ColumnInfo(name = "car_type")
    private String car_type;
   
    @ColumnInfo(name = "manufactured_date")
    private String manufactured_date;
   
    @ColumnInfo(name = "insurance")
    private String insurance;
   
    @ColumnInfo(name = "color")
    private String color;
   
    @ColumnInfo(name = "km_driven")
    private String km_driven;

    @ColumnInfo(name = "fuel_type")
    private String fuel_type;
   
    @ColumnInfo(name = "city_address")
    private String city_address;

    @ColumnInfo(name = "added_on")
    private String added_on;

    @ColumnInfo(name = "price")
    private String price;
   
    @ColumnInfo(name = "image_array")
    private String image_array;



    @Override
    public String toString() {
        return super.toString();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
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


//[
//        {
//        "id": 14,
//        "car_name": "MARUTI SUZUKI Dzire ZXI+ AMT",
//        "car_type": "Sedan",
//        "manufactured_date": "2015",
//        "insurance": "till March'20",
//        "color": "Silky White",
//        "km_driven": "8000km",
//        "fuel_type": "Petrol",
//        "city_address": "Kandivali(w), Mumbai",
//        "added_on": "28-10-2019",
//        "price": "Rs.800000/-",
//        "image_array": "['https://auto.ndtvimg.com/car-images/colors/maruti-suzuki/dzire/maruti-suzuki-dzire-pearl-arctic-white.png','https://auto.ndtvimg.com/car-images/colors/maruti-suzuki/dzire/maruti-suzuki-dzire-pearl-arctic-white.png']"
//        }
//        ]