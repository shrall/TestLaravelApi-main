package com.example.testlaravelapi.ui.login;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.testlaravelapi.model.response.TokenResponse;
import com.example.testlaravelapi.repository.AuthRepository;

public class LoginViewModel extends ViewModel {

    private AuthRepository repository;

    public LoginViewModel() {
        repository = AuthRepository.getInstance();
    }

    public MutableLiveData<TokenResponse> login(String email, String password){
        return repository.login(email, password);
    }
}
