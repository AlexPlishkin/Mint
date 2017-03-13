package com.plishkin.alex.mint;


import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.plishkin.alex.mint.Entities.Weather;
import com.plishkin.alex.mint.Tasks.AsyncResponseble;
import com.plishkin.alex.mint.Tasks.LoadWeatherTask;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class WeatherFragment extends Fragment implements AsyncResponseble {

    public WeatherFragment() {

    }

    @BindView(R.id.weather_image)
    ImageView weatherImage;

    @BindView(R.id.weather_city_text_view)
    TextView weatherCityText;

    @BindView(R.id.weather_condition_text)
    TextView weatherConditionText;

    @BindView(R.id.date_weather_text_view)
    TextView weatherDateText;

    @BindView(R.id.feelslike_weather_text_view)
    TextView weatherFeelsLikeText;

    @BindView(R.id.temperature_weather_text_view)
    TextView weatherTemperatureText;

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
        Picasso.with(this.getActivity().getApplicationContext()).
                load("http:" + weather.getCurrent().getCondition().getImageUrl())
                .into(weatherImage);
        weatherCityText.setText(weather.getLocation().getName());
        weatherConditionText.setText(weather.getCurrent().getCondition().getText());
        weatherDateText.setText(weather.getLocation().getDateTime());
        weatherFeelsLikeText.setText(String.valueOf(weather.getFeelsLike()));
        weatherTemperatureText.setText(String.valueOf(weather.getCurrent().getTempC()));
    }

    @OnClick(R.id.load_weather_button)
    public void loadWeather(View v){
        LoadWeatherTask weatherTask = new LoadWeatherTask(v.getContext(), this);
        weatherTask.execute();
    }
}
