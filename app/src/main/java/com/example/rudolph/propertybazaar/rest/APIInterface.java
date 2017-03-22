package com.example.rudolph.propertybazaar.rest;

import com.example.rudolph.propertybazaar.models.Property;
import com.example.rudolph.propertybazaar.models.PropertyResponse;
import com.example.rudolph.propertybazaar.models.User;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Field;
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

    // TODO Complete method
    @Multipart
    @POST("property/")
    Call<Property> uploadProperty(
            @Header("Authorization") String authorization,
            @Field("title") String title,
            @Field("description") String description,
            @Field("address") String address,
            @Field("city") String city,
            @Field("garages") int garages,
            @Field("bedrooms") int bedrooms,
            @Field("bathrooms") int bathrooms,
            @Field("rooms") int rooms,
            @Field("price") int price,
            @Field("area") int area,
            @Field("propertytype") String propertytype,
            @Part("image") RequestBody image);

}
