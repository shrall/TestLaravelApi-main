package com.example.testlaravelapi.repository;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.example.testlaravelapi.model.response.TokenResponse;
import com.example.testlaravelapi.network.RetrofitService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AuthRepository {
    private static AuthRepository authRepository;
    private RetrofitService apiService;
    private static final String TAG = "AuthRepository";

    private AuthRepository() {
        apiService = RetrofitService.getInstance("");
    }

    public static AuthRepository getInstance() {
        if (authRepository == null) {
            authRepository = new AuthRepository();
        }
        return authRepository;
    }

    public MutableLiveData<TokenResponse> login(String email, String password) {
        MutableLiveData<TokenResponse> tokenResponse = new MutableLiveData<>();

        apiService.login(email, password).enqueue(new Callback<TokenResponse>() {
            @Override
            public void onResponse(Call<TokenResponse> call, Response<TokenResponse> response) {
                Log.d(TAG, "onRessponse" + response.code());
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        tokenResponse.postValue(response.body());
                    }
                }
            }

            @Override
            public void onFailure(Call<TokenResponse> call, Throwable t) {
                Log.d(TAG, "onFailure" + t.getMessage());
            }
        });
        return tokenResponse;
    }

    public void register() {

    }
}
