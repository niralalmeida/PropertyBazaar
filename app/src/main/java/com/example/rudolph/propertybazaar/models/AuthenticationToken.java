package com.example.rudolph.propertybazaar.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by rudol on 3/13/2017.
 */

public class AuthenticationToken {

    @SerializedName("token")
    private String token;

    public AuthenticationToken(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
