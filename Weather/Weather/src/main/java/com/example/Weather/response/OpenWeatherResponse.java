package com.example.Weather.response;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class OpenWeatherResponse {
    private double lat;
    private double lon;
    private String timezone;
    private int timezone_offset;
    private Current current;
    private List<Minutely> minutely;
    private List<Hourly> hourly;
    private List<Daily> daily;
    private List<Alert> alerts;

    @Getter
    @Setter
    @ToString
    public static class Current {
        private long dt;
        private long sunrise;
        private long sunset;
        private double temp;
        private double feels_like;
        private int pressure;
        private int humidity;
        private double dew_point;
        private double uvi;
        private int clouds;
        private int visibility;
        private double wind_speed;
        private int wind_deg;
        private double wind_gust;
        private List<Weather> weather;
    }

    @Getter
    @Setter
    @ToString
    public static class Minutely {
        private long dt;
        private double precipitation;
    }

    @Getter
    @Setter
    @ToString
    public static class Hourly {
        private long dt;
        private double temp;
        private double feels_like;
        private int pressure;
        private int humidity;
        private double dew_point;
        private double uvi;
        private int clouds;
        private int visibility;
        private double wind_speed;
        private int wind_deg;
        private double wind_gust;
        private List<Weather> weather;
        private double pop; // Probability of precipitation
    }

    @Getter
    @Setter
    @ToString
    public static class Daily {
        private long dt;
        private long sunrise;
        private long sunset;
        private long moonrise;
        private long moonset;
        private double moon_phase;
        private String summary;
        private Temp temp;
        private FeelsLike feels_like;
        private int pressure;
        private int humidity;
        private double dew_point;
        private double wind_speed;
        private int wind_deg;
        private double wind_gust;
        private List<Weather> weather;
        private int clouds;
        private double pop; // Probability of precipitation
        private double rain; // Rain volume
        private double uvi;
    }

    @Getter
    @Setter
    @ToString
    public static class Temp {
        private double day;
        private double min;
        private double max;
        private double night;
        private double eve;
        private double morn;
    }

    @Getter
    @Setter
    @ToString
    public static class FeelsLike {
        private double day;
        private double night;
        private double eve;
        private double morn;
    }

    @Getter
    @Setter
    @ToString
    public static class Weather {
        private int id;
        private String main;
        private String description;
        private String icon;
    }

    @Getter
    @Setter
    @ToString
    public static class Alert {
        private String sender_name;
        private String event;
        private long start;
        private long end;
        private String description;
        private List<String> tags;
    }
}
