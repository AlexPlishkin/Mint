package com.plishkin.alex.mint.Entities;

import com.google.gson.Gson;

public class Fruit {

    private String name;

    public Fruit() {
    }

    public Fruit(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Fruit setName(String name) {
        this.name = name;
        return this;
    }

    public String toJSON(){
        Gson gson = new Gson();
        return gson.toJson(this);
    }

    public static Fruit fromJson(String json){
        Gson gson = new Gson();
        return gson.fromJson(json, Fruit.class);
    }

}
