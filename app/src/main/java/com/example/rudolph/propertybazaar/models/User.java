package com.example.rudolph.propertybazaar.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Rudolph Almeida on 3/13/2017.
 */

public class User {

    @SerializedName("username")
    private String username;

    @SerializedName("email")
    private String email;

    @SerializedName("id")
    private int id;

    @SerializedName("first_name")
    private String first_name;

    @SerializedName("last_name")
    private String last_name;

    @SerializedName("properties")
    private List<Property> properties;

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public int getId() {
        return id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public List<Property> getProperties() {
        return properties;
    }
}
