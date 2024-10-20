package com.example.Weather.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WeatherSummaryDTO {
    private double averageTemperature;
    private double maximumTemperature;
    private double minimumTemperature;
    private String dominantWeatherCondition;
    private long updateTimestamp;
}
