package com.plishkin.alex.mint;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

//TODO:Registration, Login, Hello page, Hash password, SharePreference

public class MainActivity extends AppCompatActivity {

    private final String USER_NAME = "Alex";
    private final String PASSWORD = "Alex12345";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final TextView name = (TextView) findViewById(R.id.name);
        final TextView password = (TextView) findViewById(R.id.password);

        Button login = (Button) findViewById(R.id.login);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (name.getText().equals(USER_NAME) && password.getText().equals(PASSWORD)) {
                    Toast.makeText(MainActivity.this, getString(R.string.login_success_text), Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, getString(R.string.login_failed_text), Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
}
