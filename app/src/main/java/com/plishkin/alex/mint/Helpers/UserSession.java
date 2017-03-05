package com.plishkin.alex.mint.Helpers;

import android.content.SharedPreferences;

import com.plishkin.alex.mint.MyApplication;


public class UserSession {
    private MyApplication application;

    public UserSession(MyApplication application) {
        this.application = application;
    }

    public void createNewSession(){
        application.getSharedPreferences().edit()
                .putString("SESS_ID", application.getSharedPreferences().getString("login", "")).apply();
    }

    public void sessionDestroy(){
        application.getSharedPreferences().edit()
                .remove("SESS_ID")
                .apply();
    }

    public boolean userSignedIn(){
        if (!application.getSharedPreferences().contains("SESS_ID")){
            return false;
        }

        return true;
    }
}
