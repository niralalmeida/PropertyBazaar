package com.example.rudolph.propertybazaar.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Rudolph Almeida on 3/12/2017.
 */

public class User {

    @SerializedName("email")
    private String email;

    @SerializedName("first_name")
    private String first_name;

    @SerializedName("last_name")
    private String last_name;

    @SerializedName("password")
    private String password;

    @SerializedName("id")
    private int id;

    @SerializedName("properties")
    private List<Property> properties;

    public User(String email, String first_name, String last_name, String password, List<Property> properties) {
        this.email = email;
        this.first_name = first_name;
        this.last_name = last_name;
        this.password = password;
        this.properties = properties;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Property> getProperties() {
        return properties;
    }

    public void setProperties(List<Property> properties) {
        this.properties = properties;
    }
}
