package com.example.rudolph.propertybazaar.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Rudolph on 3/11/2017.
 */

public class Property {

    @SerializedName("title")
    private String title;

    @SerializedName("description")
    private String description;

    @SerializedName("address")
    private String address;

    @SerializedName("city")
    private String city;

    public Property(String title, String description, String address, String city, String propertytype, int rooms, int bathrooms, int bedrooms, int garages, int area, String image, String owner, String created, int price) {
        this.title = title;
        this.description = description;
        this.address = address;
        this.city = city;
        this.propertytype = propertytype;
        this.rooms = rooms;
        this.bathrooms = bathrooms;
        this.bedrooms = bedrooms;
        this.garages = garages;
        this.area = area;
        this.image = image;
        this.owner = owner;
        this.created = created;
        this.price = price;
    }

    @SerializedName("propertytype")
    private String propertytype;

    @SerializedName("rooms")
    private int rooms;

    @SerializedName("bathrooms")
    private int bathrooms;

    @SerializedName("bedrooms")
    private int bedrooms;

    @SerializedName("garages")
    private int garages;

    @SerializedName("area")
    private int area;

    @SerializedName("image")
    private String image;

    @SerializedName("owner")
    private String owner;

    @SerializedName("created")
    private String created;

    @SerializedName("id")
    private int id;

    @SerializedName("price")
    private int price;

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

    public String getPropertytype() {
        return propertytype;
    }

    public void setPropertytype(String propertytype) {
        this.propertytype = propertytype;
    }

    public int getRooms() {
        return rooms;
    }

    public void setRooms(int rooms) {
        this.rooms = rooms;
    }

    public int getBathrooms() {
        return bathrooms;
    }

    public void setBathrooms(int bathrooms) {
        this.bathrooms = bathrooms;
    }

    public int getBedrooms() {
        return bedrooms;
    }

    public void setBedrooms(int bedrooms) {
        this.bedrooms = bedrooms;
    }

    public int getGarages() {
        return garages;
    }

    public void setGarages(int garages) {
        this.garages = garages;
    }

    public int getArea() {
        return area;
    }

    public void setArea(int area) {
        this.area = area;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
