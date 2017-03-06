package com.plishkin.alex.mint;


import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.plishkin.alex.mint.Tasks.AsyncResponseble;
import com.plishkin.alex.mint.Tasks.LoadWeatherTask;

import butterknife.ButterKnife;


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
        ButterKnife.bind(view);

        LoadWeatherTask weatherTask = new LoadWeatherTask(view.getContext(), this);
        weatherTask.execute();

        return view;
    }

    @Override
    public void getResponse(Object response) {

    }
}
