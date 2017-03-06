package com.plishkin.alex.mint.Tasks;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;

import com.google.gson.GsonBuilder;
import com.plishkin.alex.mint.API.WeatherAPI;
import com.plishkin.alex.mint.Entities.Weather;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class LoadWeatherTask extends AsyncTask<Void, Integer, Weather> {

    private volatile Context context = null;
    private WeatherAPI weatherAPI;
    private ProgressDialog dialog;
    private AsyncResponseble delegate = null;
    private Retrofit retrofit;

    final String REQUEST_KEY = "b4353860f359482a9d7100649170603";
    final String REQUEST_CITY = "Chernivtsi";

    public LoadWeatherTask(Context context, AsyncResponseble asyncResponseble) {
        this.context = context;
        this.delegate = asyncResponseble;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        dialog = ProgressDialog.show(context, "Loading", "Please wait...", true);
    }

    @Override
    protected synchronized Weather doInBackground(Void... params) {

        retrofit = new Retrofit.Builder()
                .baseUrl("http://api.apixu.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        weatherAPI = retrofit.create(WeatherAPI.class);


        Call<Weather> bodyCall = weatherAPI.loadWeather(REQUEST_KEY, REQUEST_CITY);
        final Weather[] weather = new Weather[1];

        bodyCall.enqueue(new Callback<Weather>() {
            @Override
            public void onResponse(Call<Weather> call, Response<Weather> response) {
                weather[0] = response.body();
            }

            @Override
            public void onFailure(Call<Weather> call, Throwable t) {

            }
        });

        System.out.println(weather[0]);
        return weather[0];
    }

    @Override
    protected void onPostExecute(Weather weather) {
        super.onPostExecute(weather);
        dialog.dismiss();
        delegate.getResponse(weather);
    }
}
