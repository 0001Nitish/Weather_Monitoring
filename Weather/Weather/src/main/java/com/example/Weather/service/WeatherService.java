package com.example.Weather.service;

import com.example.Weather.enums.City;
import com.example.Weather.mapper.WeatherMapper;
import com.example.Weather.model.WeatherData;
import com.example.Weather.repository.WeatherDataRepository;
import com.example.Weather.response.OpenWeatherResponse;
import com.example.Weather.response.WeatherApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class WeatherService {

    @Autowired
    private WeatherDataRepository repository;


    private final String API_KEY = "ya226d0732540339421f3f3428105b0ac";
    private final List<String> CITIES = Arrays.asList("Delhi", "Mumbai", "Chennai", "Bangalore", "Kolkata", "Hyderabad");

    private OpenWeatherResponse fetchOpenWeatherResponse(double latitude, double longitude) {
        String url = String.format("https://api.openweathermap.org/data/3.0/onecall?lat=%f&lon=%f&exclude=hourly,minutely&appid=%s",
                latitude, longitude, API_KEY);
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(url, OpenWeatherResponse.class);
    }

    public WeatherApiResponse saveWeatherData(String cityName) {
        City city = getCityFromName(cityName);
        if (city == null) {
            throw new IllegalArgumentException("Invalid city name: " + cityName);
        }

        // Fetch weather data using lat/lon
        OpenWeatherResponse openWeatherResponse = fetchOpenWeatherResponse(city.getLatitude(), city.getLongitude());

        //Testing Data
        /*OpenWeatherResponse openWeatherResponse = new OpenWeatherResponse();
        openWeatherResponse.setLat(33.44);
        openWeatherResponse.setLon(-94.04);
        openWeatherResponse.setTimezone("Delhi");
        openWeatherResponse.setTimezone_offset(-18000);

        OpenWeatherResponse.Current current = new OpenWeatherResponse.Current();
        current.setDt(1684929490);
        current.setSunrise(1684926645);
        current.setSunset(1684977332);
        current.setTemp(292.55);
        current.setFeels_like(292.87);
        current.setPressure(1014);
        current.setHumidity(89);
        current.setDew_point(290.69);
        current.setUvi(0.16);
        current.setClouds(53);
        current.setVisibility(10000);
        current.setWind_speed(3.13);
        current.setWind_deg(93);
        current.setWind_gust(6.71);

        // Weather List
        List<OpenWeatherResponse.Weather> weatherList = new ArrayList<>();
        OpenWeatherResponse.Weather weather = new OpenWeatherResponse.Weather();
        weather.setId(803);
        weather.setMain("Clouds");
        weather.setDescription("broken clouds");
        weather.setIcon("04d");
        weatherList.add(weather);

        current.setWeather(weatherList);
        openWeatherResponse.setCurrent(current);

        // Setting Minutely (example with one object)
        List<OpenWeatherResponse.Minutely> minutelyList = new ArrayList<>();
        OpenWeatherResponse.Minutely minutely = new OpenWeatherResponse.Minutely();
        minutely.setDt(1684929540);
        minutely.setPrecipitation(0);
        minutelyList.add(minutely);
        openWeatherResponse.setMinutely(minutelyList);

        // Setting Hourly (example with one object)
        List<OpenWeatherResponse.Hourly> hourlyList = new ArrayList<>();
        OpenWeatherResponse.Hourly hourly = new OpenWeatherResponse.Hourly();
        hourly.setDt(1684926000);
        hourly.setTemp(292.01);
        hourly.setFeels_like(292.33);
        hourly.setPressure(1014);
        hourly.setHumidity(91);
        hourly.setDew_point(290.51);
        hourly.setUvi(0);
        hourly.setClouds(54);
        hourly.setVisibility(10000);
        hourly.setWind_speed(2.58);
        hourly.setWind_deg(86);
        hourly.setWind_gust(5.88);
        hourly.setPop(0.15);
        hourly.setWeather(weatherList); // Reusing weather list
        hourlyList.add(hourly);
        openWeatherResponse.setHourly(hourlyList);

        // Setting Daily (example with one object)
        List<OpenWeatherResponse.Daily> dailyList = new ArrayList<>();
        OpenWeatherResponse.Daily daily = new OpenWeatherResponse.Daily();
        daily.setDt(1684951200);
        daily.setSunrise(1684926645);
        daily.setSunset(1684977332);
        daily.setMoonrise(1684941060);
        daily.setMoonset(1684905480);
        daily.setMoon_phase(0.16);
        daily.setSummary("Expect a day of partly cloudy with rain");

        // Setting Temperature
        OpenWeatherResponse.Temp temperature = new OpenWeatherResponse.Temp();
        temperature.setDay(299.03);
        temperature.setMin(290.69);
        temperature.setMax(300.35);
        temperature.setNight(291.45);
        temperature.setEve(297.51);
        temperature.setMorn(292.55);
        daily.setTemp(temperature);

        // Setting Feels Like
        OpenWeatherResponse.FeelsLike feelsLike = new OpenWeatherResponse.FeelsLike();
        feelsLike.setDay(299.21);
        feelsLike.setNight(291.37);
        feelsLike.setEve(297.86);
        feelsLike.setMorn(292.87);
        daily.setFeels_like(feelsLike);

        daily.setPressure(1016);
        daily.setHumidity(59);
        daily.setDew_point(290.48);
        daily.setWind_speed(3.98);
        daily.setWind_deg(76);
        daily.setWind_gust(8.92);
        daily.setWeather(weatherList); // Reusing weather list
        daily.setClouds(92);
        daily.setPop(0.47);
        daily.setRain(0.15);
        daily.setUvi(9.23);
        dailyList.add(daily);
        openWeatherResponse.setDaily(dailyList);

        // Setting Alerts (example with one object)
        List<OpenWeatherResponse.Alert> alertsList = new ArrayList<>();
        OpenWeatherResponse.Alert alert = new OpenWeatherResponse.Alert();
        alert.setSender_name("NWS Philadelphia - Mount Holly (New Jersey, Delaware, Southeastern Pennsylvania)");
        alert.setEvent("Small Craft Advisory");
        alert.setStart(1684952747);
        alert.setEnd(1684988747);
        alert.setDescription("...SMALL CRAFT ADVISORY REMAINS IN EFFECT FROM 5 PM THIS\nAFTERNOON TO 3 AM EST FRIDAY...\n* WHAT...North winds 15 to 20 kt with gusts up to 25 kt and seas\n3 to 5 ft expected.\n* WHERE...Coastal waters from Little Egg Inlet to Great Egg\nInlet NJ out 20 nm, Coastal waters from Great Egg Inlet to\nCape May NJ out 20 nm and Coastal waters from Manasquan Inlet\nto Little Egg Inlet NJ out 20 nm.\n* WHEN...From 5 PM this afternoon to 3 AM EST Friday.\n* IMPACTS...Conditions will be hazardous to small craft.");
        alertsList.add(alert);
        openWeatherResponse.setAlerts(alertsList);

        // Print the OpenWeatherResponse object (for verification)
        System.out.println(openWeatherResponse);*/


        /*List<OpenWeatherResponse> weatherResponses = new ArrayList<>();
        Random random = new Random();

        for (int i = 0; i < 10; i++) {
            OpenWeatherResponse openWeatherResponse = new OpenWeatherResponse();
            openWeatherResponse.setLat(28.61);  // Latitude for Delhi
            openWeatherResponse.setLon(77.23);  // Longitude for Delhi
            openWeatherResponse.setTimezone("Delhi");
            openWeatherResponse.setTimezone_offset(-19800); // UTC+5:30 in seconds

            // Current Weather
            OpenWeatherResponse.Current current = new OpenWeatherResponse.Current();
            current.setDt(System.currentTimeMillis() / 1000);
            current.setSunrise(System.currentTimeMillis() / 1000 - random.nextInt(3600));
            current.setSunset(System.currentTimeMillis() / 1000 + random.nextInt(3600));
            current.setTemp(290 + random.nextDouble() * 10); // Random temp between 290 and 300
            current.setFeels_like(290 + random.nextDouble() * 10);
            current.setPressure(1000 + random.nextInt(100)); // Random pressure
            current.setHumidity(70 + random.nextInt(30)); // Random humidity between 70 and 100
            current.setDew_point(290 + random.nextDouble() * 10);
            current.setUvi(random.nextDouble() * 10);
            current.setClouds(random.nextInt(100)); // Random clouds percentage
            current.setVisibility(10000);
            current.setWind_speed(random.nextDouble() * 10); // Random wind speed
            current.setWind_deg(random.nextInt(360)); // Random wind direction

            // Weather List
            List<OpenWeatherResponse.Weather> weatherList = new ArrayList<>();
            OpenWeatherResponse.Weather weather = new OpenWeatherResponse.Weather();
            weather.setId(800 + random.nextInt(3)); // Random weather condition ID
            weather.setMain("Clear");
            weather.setDescription("clear sky");
            weather.setIcon("01d");
            weatherList.add(weather);

            current.setWeather(weatherList);
            openWeatherResponse.setCurrent(current);

            // Setting Minutely (example with random values)
            List<OpenWeatherResponse.Minutely> minutelyList = new ArrayList<>();
            for (int j = 0; j < 5; j++) { // Add 5 minutely objects
                OpenWeatherResponse.Minutely minutely = new OpenWeatherResponse.Minutely();
                minutely.setDt(System.currentTimeMillis() / 1000 + j * 60);
                minutely.setPrecipitation(random.nextDouble() * 10); // Random precipitation
                minutelyList.add(minutely);
            }
            openWeatherResponse.setMinutely(minutelyList);

            // Setting Hourly (example with random values)
            List<OpenWeatherResponse.Hourly> hourlyList = new ArrayList<>();
            for (int j = 0; j < 5; j++) { // Add 5 hourly objects
                OpenWeatherResponse.Hourly hourly = new OpenWeatherResponse.Hourly();
                hourly.setDt(System.currentTimeMillis() / 1000 + j * 3600);
                hourly.setTemp(290 + random.nextDouble() * 10);
                hourly.setFeels_like(290 + random.nextDouble() * 10);
                hourly.setPressure(1000 + random.nextInt(100));
                hourly.setHumidity(70 + random.nextInt(30));
                hourly.setDew_point(290 + random.nextDouble() * 10);
                hourly.setUvi(random.nextDouble() * 10);
                hourly.setClouds(random.nextInt(100));
                hourly.setVisibility(10000);
                hourly.setWind_speed(random.nextDouble() * 10);
                hourly.setWind_deg(random.nextInt(360));
                hourly.setPop(random.nextDouble()); // Random probability of precipitation
                hourly.setWeather(weatherList); // Reusing weather list
                hourlyList.add(hourly);
            }
            openWeatherResponse.setHourly(hourlyList);

            // Setting Daily (example with random values)
            List<OpenWeatherResponse.Daily> dailyList = new ArrayList<>();
            for (int j = 0; j < 5; j++) { // Add 5 daily objects
                OpenWeatherResponse.Daily daily = new OpenWeatherResponse.Daily();
                daily.setDt(System.currentTimeMillis() / 1000 + j * 86400);
                daily.setSunrise(System.currentTimeMillis() / 1000 - random.nextInt(3600));
                daily.setSunset(System.currentTimeMillis() / 1000 + random.nextInt(3600));
                daily.setMoonrise(System.currentTimeMillis() / 1000 + random.nextInt(86400));
                daily.setMoonset(System.currentTimeMillis() / 1000 - random.nextInt(86400));
                daily.setMoon_phase(random.nextDouble());
                daily.setSummary("Weather forecast for day " + (j + 1));

                // Setting Temperature
                OpenWeatherResponse.Temp temperature = new OpenWeatherResponse.Temp();
                temperature.setDay(290 + random.nextDouble() * 10);
                temperature.setMin(290 + random.nextDouble() * 5);
                temperature.setMax(295 + random.nextDouble() * 5);
                temperature.setNight(290 + random.nextDouble() * 5);
                temperature.setEve(290 + random.nextDouble() * 5);
                temperature.setMorn(290 + random.nextDouble() * 5);
                daily.setTemp(temperature);

                // Setting Feels Like
                OpenWeatherResponse.FeelsLike feelsLike = new OpenWeatherResponse.FeelsLike();
                feelsLike.setDay(290 + random.nextDouble() * 10);
                feelsLike.setNight(290 + random.nextDouble() * 5);
                feelsLike.setEve(290 + random.nextDouble() * 5);
                feelsLike.setMorn(290 + random.nextDouble() * 5);
                daily.setFeels_like(feelsLike);

                daily.setPressure(1000 + random.nextInt(100));
                daily.setHumidity(60 + random.nextInt(40));
                daily.setDew_point(290 + random.nextDouble() * 10);
                daily.setWind_speed(random.nextDouble() * 10);
                daily.setWind_deg(random.nextInt(360));
                daily.setWind_gust(random.nextDouble() * 10);
                daily.setWeather(weatherList); // Reusing weather list
                daily.setClouds(random.nextInt(100));
                daily.setPop(random.nextDouble());
                daily.setRain(random.nextDouble());
                daily.setUvi(random.nextDouble() * 10);
                dailyList.add(daily);
            }
            openWeatherResponse.setDaily(dailyList);

            // Setting Alerts (example with random values)
            List<OpenWeatherResponse.Alert> alertsList = new ArrayList<>();
            OpenWeatherResponse.Alert alert = new OpenWeatherResponse.Alert();
            alert.setSender_name("NWS Delhi");
            alert.setEvent("Weather Alert " + (i + 1));
            alert.setStart(System.currentTimeMillis() / 1000 + random.nextInt(86400)); // Random start time
            alert.setEnd(System.currentTimeMillis() / 1000 + random.nextInt(86400) + 86400); // Random end time
            alert.setDescription("Weather alert description for alert " + (i + 1));
            alertsList.add(alert);
            openWeatherResponse.setAlerts(alertsList);

            // Add to the list of responses
            weatherResponses.add(openWeatherResponse);
        }

        // Print all the weather responses
        for (OpenWeatherResponse response : weatherResponses) {
            System.out.println(response);

        }*/
        // Map to WeatherApiResponse
        WeatherApiResponse weatherApiResponse = WeatherMapper.mapToWeatherApiResponse(openWeatherResponse);

        // Save WeatherData to repository if needed
        WeatherData weatherData = new WeatherData();
        weatherData.setCity(cityName);
        weatherData.setTemperature(weatherApiResponse.getTemp() - 273);
        weatherData.setFeelsLike(weatherApiResponse.getFeels_like() - 273);
        weatherData.setMainCondition(weatherApiResponse.getMain());
        weatherData.setTimestamp(weatherApiResponse.getDt());

        repository.save(weatherData);
        System.out.println("Saved weather data for " + cityName);
        return weatherApiResponse; // Return the mapped WeatherApiResponse
    }


    public List<WeatherApiResponse> fetchWeatherByCity(String cityName) {
        System.out.println("Fetching weather for " + cityName);
        return getWeatherApiResponseList(repository.findByCity(cityName));
    }

    public static List<WeatherApiResponse> getWeatherApiResponseList(List<WeatherData> weatherDataList) {
        return weatherDataList.stream()
                .map(weatherData -> {
                    WeatherApiResponse response = new WeatherApiResponse();
                    response.setMain(weatherData.getMainCondition());
                    response.setTemp(weatherData.getTemperature());
                    response.setFeels_like(weatherData.getFeelsLike());
                    response.setDt(weatherData.getTimestamp());
                    return response;
                })
                .collect(Collectors.toList());
    }

    public List<WeatherData> getAllWeatherData() {
        return repository.findAll();
    }

    @Scheduled(fixedRate = 300000) // 5 minutes
    public void fetchWeatherForAllCities() {
        for (String city : CITIES) {
            saveWeatherData(city);
        }
    }

    private City getCityFromName(String cityName) {
        switch (cityName.toUpperCase()) {
            case "DELHI":
                return City.DELHI;
            case "MUMBAI":
                return City.MUMBAI;
            case "CHENNAI":
                return City.CHENNAI;
            case "BANGALORE":
                return City.BANGALORE;
            case "KOLKATA":
                return City.KOLKATA;
            case "HYDERABAD":
                return City.HYDERABAD;
            default:
                return null; // or throw an exception if preferred
        }
    }
}
