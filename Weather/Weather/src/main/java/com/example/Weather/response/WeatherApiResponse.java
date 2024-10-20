package com.example.Weather.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WeatherApiResponse {
    private String main;
    private double temp;
    private double feels_like;
    private long dt;

}
