package com.example.rudolph.propertybazaar.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by rudol on 3/13/2017.
 */

public class LoggingInUser {

    @SerializedName("username")
    private String username;

    @SerializedName("password")
    private String password;

    public LoggingInUser(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
