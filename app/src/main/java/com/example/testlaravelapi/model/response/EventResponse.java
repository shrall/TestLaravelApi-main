package com.example.testlaravelapi.model.response;

import com.example.testlaravelapi.model.local.Event;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class EventResponse {
    @SerializedName("data")
    private List<Event> results;

    public List<Event> getResults(){
        return results;
    }
}
