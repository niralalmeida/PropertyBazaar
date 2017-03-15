package com.example.rudolph.propertybazaar.rest;

import com.example.rudolph.propertybazaar.models.Property;
import com.example.rudolph.propertybazaar.models.PropertyResponse;
import com.example.rudolph.propertybazaar.models.User;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by Rudolph Almeida on 3/12/2017.
 */

public interface APIInterface {

    @GET("user/{id}/")
    Call<User> getUser(@Path("id") int id);

    @GET("property/")
    Call<PropertyResponse> getProperties();

    @GET("property/{id}/")
    Call<Property> getProperty(@Path("id") int id);

}
