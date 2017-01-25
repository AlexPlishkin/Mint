package com.plishkin.alex.mint;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SingInActivity extends AppCompatActivity {

    Intent selfIntent;

    @BindView(R.id.hello_text_view)
    TextView helloView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        ButterKnife.bind(this);
        selfIntent = getIntent();

        if (selfIntent.hasExtra("login")){
            String login = selfIntent.getStringExtra("login");
            helloView.setText(helloView.getText().toString() + " " +login);
        }

    }
}
