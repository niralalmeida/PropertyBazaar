package com.example.rudolph.propertybazaar.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Rudolph Almeida on 3/12/2017.
 */

public class PropertyResponse {

    @SerializedName("count")
    private int count;

    @SerializedName("next")
    private String next;

    @SerializedName("previous")
    private String previous;

    @SerializedName("results")
    private List<Property> results;

    public PropertyResponse(int count, String next, String previous, List<Property> results) {
        this.count = count;
        this.next = next;
        this.previous = previous;
        this.results = results;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getNext() {
        return next;
    }

    public void setNext(String next) {
        this.next = next;
    }

    public String getPrevious() {
        return previous;
    }

    public void setPrevious(String previous) {
        this.previous = previous;
    }

    public List<Property> getResults() {
        return results;
    }

    public void setResults(List<Property> results) {
        this.results = results;
    }
}
