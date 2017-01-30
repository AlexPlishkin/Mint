package com.plishkin.alex.mint;

import android.app.FragmentTransaction;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.plishkin.alex.mint.Helpers.Hasher;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * TODO: Fragment transfer params - done
 * TODO: RecycleView
 * TODO: Create static list of Users
 * TODO: Implements operation : Add, Edit, Delete, Search
 * TODO: Top - search,
 * TODO: Body - RecycleView
 * TODO: Bottom : addButton
 */

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.name)
    TextView login;

    @BindView(R.id.password)
    TextView password;

    SharedPreferences mySettings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Toast.makeText(this, "onCreate", Toast.LENGTH_SHORT).show();
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        mySettings = getSharedPreferences(getString(R.string.settingsName), MODE_PRIVATE);
    }

    /*@Override
    protected void onStart() {
        super.onStart();
        Toast.makeText(this, "onStart", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onStop() {
        super.onStop();
        Toast.makeText(this, "onStop", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Toast.makeText(this, "onDestroy", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onPause() {
        super.onPause();
        Toast.makeText(this, "onPause", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onResume() {
        super.onResume();
        Toast.makeText(this, "onResume", Toast.LENGTH_SHORT).show();
    }*/

    @OnClick(R.id.button_sign_in)
    public void signIn(View view){
        String Login = login.getText().toString();
        String passwordValue = mySettings.getString(Login, "");

        if (!mySettings.contains(Login) || passwordValue.equals("")){
            Toast.makeText(this, R.string.toast_regiter_alerady, Toast.LENGTH_SHORT).show();
            return;
        }

        if (!passwordValue.equals(Hasher.md5(password.getText().toString()))){
            Toast.makeText(this, R.string.toast_incorrect_password, Toast.LENGTH_SHORT).show();
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
