package com.ssafy.gilbut.domain.weather.service;

import com.ssafy.gilbut.domain.weather.model.dto.WeatherForecastResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;

@Tag(name = "")
public interface WeatherService {
    List<WeatherForecastResponse> getAllCitiesWeatherForecast();
    WeatherForecastResponse getCityWeatherForecast(String cityName);
}
