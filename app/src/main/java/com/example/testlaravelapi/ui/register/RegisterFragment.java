package com.example.testlaravelapi.ui.register;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;

import com.example.testlaravelapi.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RegisterFragment extends Fragment {

    @BindView(R.id.edtRegName)
    EditText editName;
    @BindView(R.id.edtRegEmail)
    EditText editEmail;
    @BindView(R.id.edtRegPassword)
    EditText editPassword;
    @BindView(R.id.edtRegRePassword)
    EditText editRePassword;
    @BindView(R.id.btnRegister)
    Button btnRegister;

    public RegisterFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_register, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);

        //TODO: Place viewModel implementation here
    }

    @OnClick(R.id.btnRegister)
    public void onClick(View view) {
        NavDirections actions;
        if (view.getId() == R.id.btnRegister) {
            actions = RegisterFragmentDirections.actionRegisterFragmentToLoginFragment3();
            Navigation.findNavController(view).navigate(actions);
        }
    }
}