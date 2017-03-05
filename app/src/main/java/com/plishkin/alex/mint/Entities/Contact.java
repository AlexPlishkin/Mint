package com.plishkin.alex.mint.Entities;

import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "contacts")
public class Contact {

    @DatabaseField(generatedId = true)
    private int id;

    @DatabaseField(columnName = "name", dataType = DataType.STRING, canBeNull = false)
    private String name;

    @DatabaseField(columnName = "phone_number", dataType = DataType.STRING, canBeNull = false)
    private String phoneNumber;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
