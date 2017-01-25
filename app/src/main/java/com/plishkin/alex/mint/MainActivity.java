package com.plishkin.alex.mint;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.plishkin.alex.mint.Helpers.Hasher;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

//TODO:Registration, Login, Hello page, Hash password, SharePreference

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.name)
    TextView login;

    @BindView(R.id.password)
    TextView password;

    SharedPreferences mySettings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        mySettings = getSharedPreferences(getString(R.string.settingsName), MODE_PRIVATE);
    }

    @OnClick(R.id.button_sign_in)
    public void signIn(View view){

        String Login = login.getText().toString();
        String passwordValue = mySettings.getString(Login, "");

        if (!mySettings.contains(Login) || passwordValue.equals("")){
            Toast.makeText(this, "You have not been registered already", Toast.LENGTH_SHORT).show();
            return;
        }

        if (!passwordValue.equals(Hasher.md5(password.getText().toString()))){
            Toast.makeText(this, "Incorrect login or password", Toast.LENGTH_SHORT).show();
            return;
        }

        Intent signInIntent = new Intent(MainActivity.this, SingInActivity.class);
        signInIntent.putExtra("login", login.getText().toString());
        startActivity(signInIntent);

    }

    @OnClick(R.id.button_sign_up)
    public void signUp(View view){
        Intent signInIntent = new Intent(MainActivity.this, SignUpActivity.class);
        startActivity(signInIntent);
    }
}
