package com.plishkin.alex.mint;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.os.Build;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.plishkin.alex.mint.Db.DatabaseHelper;
import com.plishkin.alex.mint.Db.FruitDAO;
import com.plishkin.alex.mint.Db.HelperFactory;
import com.plishkin.alex.mint.Helpers.Hasher;
import com.plishkin.alex.mint.Helpers.UserSession;

import java.sql.SQLException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class MainActivity extends AppCompatActivity {

    @BindView(R.id.name)
    TextView login;

    @BindView(R.id.password)
    TextView password;

    MyApplication application;
    UserSession userSession;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        application = (MyApplication) getApplicationContext();
        userSession = new UserSession(application);
        if (userSession.userSignedIn()){
            Intent signInIntent = new Intent(MainActivity.this, SingInActivity.class);
            signInIntent.putExtra("login", login.getText().toString());
            startActivity(signInIntent);
        }
    }

    @OnClick(R.id.button_sign_in)
    public void signIn(View view){
        String Login = login.getText().toString();
        String passwordValue = application.getSharedPreferences().getString(Login, "");

        if (!application.getSharedPreferences().contains(Login) || passwordValue.equals("")){
            Toast.makeText(this, R.string.toast_regiter_alerady, Toast.LENGTH_SHORT).show();
            return;
        }

        if (!passwordValue.equals(Hasher.md5(password.getText().toString()))){
            Toast.makeText(this, R.string.toast_incorrect_password, Toast.LENGTH_SHORT).show();
            return;
        }

        Intent signInIntent = new Intent(MainActivity.this, SingInActivity.class);
        userSession.createNewSession();
        signInIntent.putExtra("login", login.getText().toString());
        startActivity(signInIntent);

    }

    @OnClick(R.id.button_sign_up)
    public void signUp(View view){
        Intent signInIntent = new Intent(MainActivity.this, SignUpActivity.class);
        startActivity(signInIntent);
    }
}
