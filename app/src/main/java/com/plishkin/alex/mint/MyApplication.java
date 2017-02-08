package com.plishkin.alex.mint;

import android.app.Application;
import android.content.SharedPreferences;

import com.plishkin.alex.mint.Db.HelperFactory;

public class MyApplication extends Application {

    private SharedPreferences sharedPreferences;

    @Override
    public void onCreate() {
        super.onCreate();
        sharedPreferences = getSharedPreferences(getString(R.string.settingsName), MODE_PRIVATE);
        HelperFactory.setHelper(getApplicationContext());
    }

    @Override
    public void onTerminate() {
        HelperFactory.releaseHelper();
        super.onTerminate();
    }

    public SharedPreferences getSharedPreferences() {
        return sharedPreferences;
    }

}
