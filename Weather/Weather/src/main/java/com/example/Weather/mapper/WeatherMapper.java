package com.example.Weather.mapper;

import com.example.Weather.response.OpenWeatherResponse;
import com.example.Weather.response.WeatherApiResponse;

public class WeatherMapper {

    public static WeatherApiResponse mapToWeatherApiResponse(OpenWeatherResponse openWeatherResponse) {
        WeatherApiResponse weatherApiResponse = new WeatherApiResponse();
        weatherApiResponse.setDt(openWeatherResponse.getCurrent().getDt());
        weatherApiResponse.setTemp(openWeatherResponse.getCurrent().getTemp());
        weatherApiResponse.setFeels_like(openWeatherResponse.getCurrent().getFeels_like());
        weatherApiResponse.setMain(openWeatherResponse.getCurrent().getWeather().get(0).getMain());
        return weatherApiResponse;
    }
}
