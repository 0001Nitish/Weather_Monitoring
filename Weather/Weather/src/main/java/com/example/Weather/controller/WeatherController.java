package com.example.Weather.controller;

import com.example.Weather.dto.WeatherSummaryDTO;
import com.example.Weather.model.WeatherData;
import com.example.Weather.response.WeatherApiResponse;
import com.example.Weather.service.WeatherConverter;
import com.example.Weather.service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class WeatherController {
    @Autowired
    private WeatherService weatherService;

    @GetMapping("/fetchAllWeather")
    public List<WeatherData> fetchAllWeather() {
        return weatherService.getAllWeatherData();
    }

//    @GetMapping("/getWeatherByCity")
//    public WeatherApiResponse getWeatherByCity(@RequestParam String city) {
//        return weatherService.saveWeatherData(city);
//    }

    @GetMapping("/fetchWeatherByCity")
    public WeatherSummaryDTO fetchWeatherByCity(@RequestParam String city) {
        return WeatherConverter.convertToWeatherSummaryDTO(weatherService.fetchWeatherByCity(city));
    }

}

