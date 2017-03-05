package com.plishkin.alex.mint;

import android.app.Application;
import android.content.SharedPreferences;

import com.plishkin.alex.mint.Db.HelperFactory;
import com.plishkin.alex.mint.Entities.Contact;

import java.util.List;

public class MyApplication extends Application {

    private SharedPreferences sharedPreferences;

    private List<Contact> contactList;

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

    public List<Contact> getContactList() {
        return contactList;
    }

    public void setContactList(List<Contact> contactList) {
        this.contactList = contactList;
    }
}
