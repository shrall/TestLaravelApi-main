package com.example.testlaravelapi.repository;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.example.testlaravelapi.model.local.Event;
import com.example.testlaravelapi.model.response.EventResponse;
import com.example.testlaravelapi.network.RetrofitService;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EventRepository {

    private static EventRepository eventRepository;
    private RetrofitService apiService;
    private static final String TAG = "EventRepository";

    private EventRepository(String token) {
        apiService = RetrofitService.getInstance(token);
    }

    public static EventRepository getInstance(String token) {
        if (eventRepository == null) {
            eventRepository = new EventRepository(token);
        }
        return eventRepository;
    }

    public synchronized void resetInstance() {
        if (eventRepository != null) {
            eventRepository = null;
        }
    }

    public MutableLiveData<List<Event>> getEvents() {
        MutableLiveData<List<Event>> liveEvents = new MutableLiveData<>();

        apiService.getEvents().enqueue(new Callback<EventResponse>() {
            @Override
            public void onResponse(Call<EventResponse> call, Response<EventResponse> response) {
                Log.d(TAG, "onResponse" + response.code());
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        liveEvents.postValue(response.body().getResults());
                    }
                }

            }

            @Override
            public void onFailure(Call<EventResponse> call, Throwable t) {
                Log.d(TAG, "onFailure" + t.getMessage());
            }
        });
        return liveEvents;
    }

    public MutableLiveData<String> logout() {
        MutableLiveData<String> message = new MutableLiveData<>();

        apiService.logout().enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                Log.d(TAG, "onResponse" + response.code());
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        try {
                            JSONObject object = new JSONObject(new Gson().toJson(response.body()));
                            String msg = object.getString("message");
                            Log.d(TAG, "onResponse" + msg);
                            message.postValue(msg);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }

            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                Log.d(TAG, "onFailure" + t.getMessage());
            }
        });
        return message;
    }
}
