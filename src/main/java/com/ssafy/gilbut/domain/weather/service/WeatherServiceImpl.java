package com.ssafy.gilbut.domain.weather.service;

import com.ssafy.gilbut.advice.status.ErrorStatus;
import com.ssafy.gilbut.domain.weather.model.dto.CurrentWeatherResponse;
import com.ssafy.gilbut.domain.weather.model.dto.ForecastResponse;
import com.ssafy.gilbut.domain.weather.model.dto.WeatherForecastResponse;
import com.ssafy.gilbut.domain.weather.model.entity.City;
import com.ssafy.gilbut.exception.handler.GeneralExceptionHandler;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class WeatherServiceImpl implements WeatherService {
    @Value("${weather.api.key}")
    private String apiKey;
    private final WeatherClient weatherClient;

    public List<WeatherForecastResponse> getAllCitiesWeatherForecast() {
        return Arrays.stream(City.values())
                .map(this::getCityForecast)
                .collect(Collectors.toList());
    }

    public WeatherForecastResponse getCityWeatherForecast(String cityName) {
        City city = City.findByName(cityName)
                .orElseThrow(() -> new GeneralExceptionHandler(ErrorStatus._INTERNAL_SERVER_ERROR));
        return getCityForecast(city);
    }

    private WeatherForecastResponse getCityForecast(City city) {
        CurrentWeatherResponse currentWeather = weatherClient.getCurrentWeather(
                city.getLatitude(),
                city.getLongitude(),
                "metric",
                apiKey,
                "kr"

                );
        ForecastResponse forecast = weatherClient.getForecast(
                city.getLatitude(),
                city.getLongitude(),
                "metric",
                apiKey,
                "kr"
        );

        return WeatherForecastResponse.builder()
                .city(city)
                .currentWeather(currentWeather)
                .forecast(forecast)
                .build();
    }
}