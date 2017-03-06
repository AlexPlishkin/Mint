package com.plishkin.alex.mint;


import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.plishkin.alex.mint.Entities.Weather;
import com.plishkin.alex.mint.Tasks.AsyncResponseble;
import com.plishkin.alex.mint.Tasks.LoadWeatherTask;

import butterknife.ButterKnife;
import butterknife.OnClick;


public class WeatherFragment extends Fragment implements AsyncResponseble {

    public WeatherFragment() {

    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_weather, container, false);
        ButterKnife.bind(this, view);

        return view;
    }

    @Override
    public void getResponse(Object response) {
        Weather weather = (Weather) response;
        System.out.println(weather);
    }

    @OnClick(R.id.load_weather_button)
    public void loadWeather(View v){
        LoadWeatherTask weatherTask = new LoadWeatherTask(v.getContext(), this);
        weatherTask.execute();
    }
}
