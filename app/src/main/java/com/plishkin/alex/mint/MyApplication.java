package com.plishkin.alex.mint;

import android.app.Application;
import android.content.SharedPreferences;

public class MyApplication extends Application {

    private SharedPreferences sharedPreferences;

    @Override
    public void onCreate() {
        sharedPreferences = getSharedPreferences(getString(R.string.settingsName), MODE_PRIVATE);
        super.onCreate();
    }

    public SharedPreferences getSharedPreferences() {
        return sharedPreferences;
    }

}
