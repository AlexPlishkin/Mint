package com.plishkin.alex.mint.Entities;

import com.google.gson.Gson;
import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "fruits")
public class Fruit {

    @DatabaseField(generatedId = true)
    private int id;

    @DatabaseField(columnName = "name", dataType = DataType.STRING, canBeNull = false)
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

    public int getId() {
        return id;
    }
}
