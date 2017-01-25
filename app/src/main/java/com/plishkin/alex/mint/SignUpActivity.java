package com.plishkin.alex.mint;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.plishkin.alex.mint.Helpers.Hasher;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SignUpActivity extends AppCompatActivity {

    Intent selfIntent;

    SharedPreferences mySettings;

    @BindView(R.id.sign_up_login)
    TextView signUpLogin;

    @BindView(R.id.sign_up_password)
    TextView signUpPassword;

    @BindView(R.id.sign_up_repeat_password)
    TextView repeatPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        ButterKnife.bind(this);
        this.selfIntent = getIntent();
        mySettings = getSharedPreferences(getString(R.string.settingsName), MODE_PRIVATE);
    }

    @OnClick(R.id.sign_up_send)
    public void sign_up_send(View view){
        if (mySettings.contains(signUpLogin.getText().toString())){
            Toast.makeText(this, "Login already exists", Toast.LENGTH_SHORT).show();
            return;
        }

        if (!signUpPassword.getText().toString().equals(repeatPassword.getText().toString())){
            Toast.makeText(this, "Passwords do not match", Toast.LENGTH_SHORT).show();
            return;
        }

        String hashedPassword = Hasher.md5(signUpPassword.getText().toString());
        SharedPreferences.Editor editor = mySettings.edit();
        editor.putString(signUpLogin.getText().toString(), hashedPassword);
        editor.apply();

        Toast.makeText(this, "You has been registered successfully", Toast.LENGTH_SHORT).show();
        finish();
    }

}
