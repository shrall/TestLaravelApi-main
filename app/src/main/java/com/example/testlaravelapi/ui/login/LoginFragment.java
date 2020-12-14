package com.example.testlaravelapi.ui.login;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.testlaravelapi.R;
import com.example.testlaravelapi.model.response.TokenResponse;
import com.example.testlaravelapi.ui.MainActivity;
import com.example.testlaravelapi.utils.SharedPreferenceHelper;

import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginFragment extends Fragment {

    @BindView(R.id.edtEmail)
    EditText editEmail;
    @BindView(R.id.edtPassword)
    EditText editPassword;
    @BindView(R.id.btnLogin)
    Button btnLogin;
    @BindView(R.id.tvReg)
    TextView tvRegister;

    private LoginViewModel viewModel;
    private SharedPreferenceHelper helper;

    public LoginFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_login, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
        Objects.requireNonNull(((MainActivity) requireActivity()).getSupportActionBar()).setDisplayHomeAsUpEnabled(false);

        //TODO: Place viewModel implementation here
        viewModel = ViewModelProviders.of(requireActivity()).get(LoginViewModel.class);
        helper =  SharedPreferenceHelper.getInstance(requireActivity());
    }

    @OnClick({R.id.btnLogin, R.id.tvReg})
    public void onClick(View view) {
        NavDirections actions;
        switch (view.getId()) {
            case R.id.btnLogin:
                if(!editEmail.getText().toString().isEmpty() && !editPassword.getText().toString().isEmpty()){
                    String email = editEmail.getText().toString().trim();
                    String password = editPassword.getText().toString().trim();
                    viewModel.login(email, password).observe(requireActivity(), new Observer<TokenResponse>() {
                        @Override
                        public void onChanged(TokenResponse tokenResponse) {
                            if(tokenResponse != null){
                                helper.saveAccessToken(tokenResponse.getAuthorization());
                                NavDirections actions = LoginFragmentDirections.actionLoginFragmentToEventFragment();
                                Navigation.findNavController(view).navigate(actions);
                                Toast.makeText(requireActivity(),  "Success", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }

                break;
            case R.id.tvReg:
                actions = LoginFragmentDirections.actionLoginFragmentToRegisterFragment();
                Navigation.findNavController(view).navigate(actions);
                break;
        }
    }
}