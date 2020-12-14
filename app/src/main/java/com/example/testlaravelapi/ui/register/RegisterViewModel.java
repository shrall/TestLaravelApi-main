package com.example.testlaravelapi.ui.register;

import androidx.lifecycle.ViewModel;

import com.example.testlaravelapi.repository.AuthRepository;

class RegisterViewModel extends ViewModel {

    private AuthRepository repository;

    public RegisterViewModel() {
        repository = AuthRepository.getInstance();
    }
}
