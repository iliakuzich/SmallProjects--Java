package ru.habrahabr.arlidor;

import java.util.ArrayList;
import java.util.List;

public class WeatherForecast {

    private String date;
    private List<String> weatherPhenomenon;
    private List<String> temperature;
    private List<String> pressure;
    private List<String> humidity;
    private List<String> wind;

    public WeatherForecast(String date) {
        this.date = date;
        weatherPhenomenon = new ArrayList<>();
        temperature = new ArrayList<>();
        pressure = new ArrayList<>();
        humidity = new ArrayList<>();
        wind = new ArrayList<>();
    }

    public void setForecastData(List<String> data) {
        weatherPhenomenon.add(data.get(0));
    }

}
