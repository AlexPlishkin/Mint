package com.plishkin.alex.mint.Tasks;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;

import com.plishkin.alex.mint.Entities.Weather;

public class LoadWeatherTask extends AsyncTask<Void, Integer, Weather> {

    private volatile Context context = null;
    private ProgressDialog dialog;
    private AsyncResponseble delegate = null;

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
    protected Weather doInBackground(Void... params) {

        return null;
    }

    @Override
    protected void onPostExecute(Weather weather) {
        super.onPostExecute(weather);
        dialog.dismiss();
        delegate.getResponse(weather);
    }
}
