package com.ssafy.gilbut.domain.weather.model.dto;

import com.ssafy.gilbut.domain.weather.model.entity.City;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class WeatherForecastResponse {
    private City city;
    private CurrentWeatherResponse currentWeather;
    private ForecastResponse forecast;
}