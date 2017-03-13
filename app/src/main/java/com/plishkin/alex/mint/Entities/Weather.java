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

    @SerializedName("feelslike_c")
    @Expose
    private double feelsLike;

    public double getFeelsLike() {
        return feelsLike;
    }

    public Location getLocation() {
        return location;
    }

    public Current getCurrent() {
        return current;
    }


    public static class Location{

        @SerializedName("name")
        @Expose
        private String name;

        @SerializedName("localtime")
        @Expose
        private String dateTime;

        public String getDateTime() {
            return dateTime;
        }

        public String getName() {
            return name;
        }


    }

    public static class Current{

        @SerializedName("temp_c")
        @Expose
        private double tempC;


        @SerializedName("condition")
        @Expose
        private Condition condition;

        public double getTempC() {
            return tempC;
        }

        public Condition getCondition() {
            return condition;
        }

        public static class Condition{
            @SerializedName("text")
            @Expose
            private String text;

            @SerializedName("icon")
            @Expose
            private String imageUrl;

            public String getImageUrl() {
                return imageUrl;
            }

            public String getText() {
                return text;
            }

        }
    }
}
