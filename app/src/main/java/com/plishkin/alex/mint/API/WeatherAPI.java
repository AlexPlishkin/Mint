package com.plishkin.alex.mint.API;


import com.plishkin.alex.mint.Entities.Weather;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;

public interface WeatherAPI {

    /*@Headers("Accept: Application/JSON")*/
    @GET("/v1/current.json")
    Call<Weather> loadWeather(@Query("key") String key, @Query("q") String city);

}
