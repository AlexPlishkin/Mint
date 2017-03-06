package com.plishkin.alex.mint.Entities;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Weather {

    @SerializedName("location")
    @Expose
    private Location location;

    @SerializedName("current")
    @Expose
    private Current current;


    public static class Location{

        @SerializedName("name")
        @Expose
        private String name;

        @SerializedName("region")
        @Expose
        private String region;

        @SerializedName("country")
        @Expose
        private String country;
    }

    public static class Current{

        @SerializedName("temp_c")
        @Expose
        private double tempC;

        @SerializedName("wind_kph")
        @Expose
        private double windSpeed;

        @SerializedName("condition")
        @Expose
        private Condition condition;


        public static class Condition{
            @SerializedName("text")
            @Expose
            private String text;

        }
    }
}
