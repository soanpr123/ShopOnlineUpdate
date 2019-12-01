package com.example.shoponline.presenter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.example.shoponline.modelgson.User;
import com.example.shoponline.Retrofit2.APIUtils;
import com.example.shoponline.Retrofit2.DataClient;
import com.example.shoponline.activity.MainActivity;
import com.example.shoponline.interfac.LoginView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginPresenter  {
    LoginView loginView;
Context context;

    public LoginPresenter(LoginView loginView, Context context) {
        this.loginView = loginView;
        this.context = context;
    }

    public void  Login(final String user, String PassWord){
        DataClient dataClient= APIUtils.dataClient();
        Call<List<User>> call=dataClient.getLogin(user,PassWord);
        call.enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                ArrayList<User>users= (ArrayList<User>) response.body();
                if(users.get(0).getSuccess().equals("success")){
                    Intent intent = new Intent(context, MainActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putInt("ID",users.get(0).getId());
                    bundle.putString("Username", users.get(0).getUsername());
                    bundle.putString("Phone", users.get(0).getPhone());
                    bundle.putString("Email", users.get(0).getEmail());
                    bundle.putString("Location", users.get(0).getLocation());
                    intent.putExtras(bundle);
                    context.startActivity(intent);
loginView.LoginSucessfull();

                }
            }

            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {
loginView.LoginFail();
            }
        });



    }
}
