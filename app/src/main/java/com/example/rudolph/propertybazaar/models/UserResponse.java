package com.example.rudolph.propertybazaar.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by rudol on 3/12/2017.
 */

public class UserResponse {

    @SerializedName("count")
    private int count;

    @SerializedName("previous")
    private String previous;

    @SerializedName("next")
    private String next;

    @SerializedName("results")
    private List<User> results;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getPrevious() {
        return previous;
    }

    public void setPrevious(String previous) {
        this.previous = previous;
    }

    public String getNext() {
        return next;
    }

    public void setNext(String next) {
        this.next = next;
    }

    public List<User> getResults() {
        return results;
    }

    public void setResults(List<User> results) {
        this.results = results;
    }

    public UserResponse(int count, String previous, String next, List<User> results) {
        this.count = count;
        this.previous = previous;
        this.next = next;
        this.results = results;
    }
}
