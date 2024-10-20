package com.example.Weather.service;

import com.example.Weather.dto.WeatherSummaryDTO;
import com.example.Weather.response.WeatherApiResponse;

import java.time.Instant;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WeatherConverter {

    public static WeatherSummaryDTO convertToWeatherSummaryDTO(List<WeatherApiResponse> apiResponse) {
        WeatherSummaryDTO dto = new WeatherSummaryDTO();
        dto.setUpdateTimestamp(Instant.now().getEpochSecond());

        double maxTemp = Double.MIN_VALUE;
        double minTemp = Double.MAX_VALUE;
        double totalTemp = 0;
        int count = 0;

        // Map to count occurrences of each weather condition
        Map<String, Integer> weatherCountMap = new HashMap<>();

        // Process daily weather data
        for (WeatherApiResponse weatherApiResponse : apiResponse) {
            // Update max and min temperatures
            maxTemp = Math.max(maxTemp, weatherApiResponse.getTemp() ); // Convert from Kelvin to Celsius
            minTemp = Math.min(minTemp, weatherApiResponse.getTemp() ); // Convert from Kelvin to Celsius
            totalTemp += weatherApiResponse.getTemp(); // Average calculation
            count++;
            weatherCountMap.put(weatherApiResponse.getMain(), weatherCountMap.getOrDefault(weatherApiResponse.getMain(), 0) + 1);
        }

        // Set calculated temperatures in the DTO
        dto.setMaximumTemperature(maxTemp);
        dto.setMinimumTemperature(minTemp);
        dto.setAverageTemperature(totalTemp / count);

        // Determine dominant weather condition using the map
        String dominantCondition = getDominantWeatherCondition(weatherCountMap);
        dto.setDominantWeatherCondition(dominantCondition);

        return dto;
    }

    private static String getDominantWeatherCondition(Map<String, Integer> weatherCountMap) {
        String dominantCondition = null;
        int maxCount = 0;

        for (Map.Entry<String, Integer> entry : weatherCountMap.entrySet()) {
            if (entry.getValue() > maxCount) {
                maxCount = entry.getValue();
                dominantCondition = entry.getKey();
            }
        }

        return dominantCondition;
    }
}
