package com.example.rudolph.propertybazaar.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Rudolph Almeida on 3/13/2017.
 */

public class NewProperty {

    @SerializedName("title")
    private String title;

    @SerializedName("description")
    private String description;

    @SerializedName("address")
    private String address;

    @SerializedName("city")
    private String city;

    @SerializedName("garages")
    private int garages;

    @SerializedName("bedrooms")
    private int bedrooms;

    @SerializedName("bathrooms")
    private int bathrooms;

    @SerializedName("area")
    private int area;

    @SerializedName("price")
    private int price;

    @SerializedName("rooms")
    private int rooms;

    @SerializedName("propertytype")
    private String propertytype;

    @SerializedName("image")
    private String image;

    public NewProperty(String title, String description, String address, String city, int garages, int bedrooms, int bathrooms, int area, int price, int rooms, String propertytype, String image) {
        this.title = title;
        this.description = description;
        this.address = address;
        this.city = city;
        this.garages = garages;
        this.bedrooms = bedrooms;
        this.bathrooms = bathrooms;
        this.area = area;
        this.price = price;
        this.rooms = rooms;
        this.propertytype = propertytype;
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getGarages() {
        return garages;
    }

    public void setGarages(int garages) {
        this.garages = garages;
    }

    public int getBedrooms() {
        return bedrooms;
    }

    public void setBedrooms(int bedrooms) {
        this.bedrooms = bedrooms;
    }

    public int getBathrooms() {
        return bathrooms;
    }

    public void setBathrooms(int bathrooms) {
        this.bathrooms = bathrooms;
    }

    public int getArea() {
        return area;
    }

    public void setArea(int area) {
        this.area = area;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getRooms() {
        return rooms;
    }

    public void setRooms(int rooms) {
        this.rooms = rooms;
    }

    public String getPropertytype() {
        return propertytype;
    }

    public void setPropertytype(String propertytype) {
        this.propertytype = propertytype;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
