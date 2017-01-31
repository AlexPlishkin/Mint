package com.plishkin.alex.mint.Entities;

import com.google.gson.Gson;

public class User {

    private String firstName;

    private String lastName;

    public String getFirstName() {
        return firstName;
    }

    public User setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public User setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public String toJSON(){
        Gson gson = new Gson();
        return gson.toJson(this);
    }

    public static User fromJson(String json){
        Gson gson = new Gson();
        return gson.fromJson(json, User.class);
    }

}
