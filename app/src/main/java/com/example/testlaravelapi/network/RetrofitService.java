package com.example.testlaravelapi.network;

import com.example.testlaravelapi.model.response.EventResponse;
import com.example.testlaravelapi.model.response.TokenResponse;
import com.example.testlaravelapi.utils.Constants;
import com.google.gson.JsonObject;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitService {
    private final Endpoints api;
    private static RetrofitService service;

    private RetrofitService(String token) {

        OkHttpClient.Builder client = new OkHttpClient.Builder();

        if(token.equals("")){
            client.addInterceptor(chain -> {
                Request request = chain.request().newBuilder()
                        .addHeader("Accept", "application/json")
                        .build();
                return chain.proceed(request);
            });
        } else {
            client.addInterceptor(chain -> {
                Request request = chain.request().newBuilder()
                        .addHeader("Accept", "application/json")
                        .addHeader("Authorization", token).build();
                return chain.proceed(request);
            });

        }

        api = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client.build())
                .build()
                .create(Endpoints.class);
    }

    public static RetrofitService getInstance(String token) {
        if (service == null) {
            service = new RetrofitService(token);
        } else if (!token.equals("")){
            service = new RetrofitService(token);
        }
        return service;
    }

//    public static RetrofitService getInstance() {
//        return service;
//    }

    public Call<TokenResponse> login(String email, String password) {
        return api.login(email, password);
    }

    public Call<EventResponse> getEvents(){
        return api.getEvents();
    }

    public Call<JsonObject> logout(){
        return api.logout();
    }
}
