package com.plishkin.alex.mint.API;


import com.plishkin.alex.mint.Entities.Weather;

import retrofit2.Call;
import retrofit2.http.GET;

public interface WeatherAPI {

    @GET("/weather")
    Call<Weather> loadWeather();

}
