package com.example.rudolph.propertybazaar.rest;

import com.example.rudolph.propertybazaar.models.Property;
import com.example.rudolph.propertybazaar.models.PropertyResponse;
import com.example.rudolph.propertybazaar.models.User;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
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

    @Multipart
    @POST("property/")
    Call<Property> uploadProperty(
            @Header("Authorization") String authorization,
            @Part("title") RequestBody title,
            @Part("description") RequestBody description,
            @Part("address") RequestBody address,
            @Part("city") RequestBody city,
            @Part("garages") RequestBody garages,
            @Part("bedrooms") RequestBody bedrooms,
            @Part("bathrooms") RequestBody bathrooms,
            @Part("rooms") RequestBody rooms,
            @Part("price") RequestBody price,
            @Part("area") RequestBody area,
            @Part("propertytype") RequestBody propertytype,
            @Part("owner") RequestBody owner,
            @Part("image\"; filename=\"prop.png\"") RequestBody image);
}
