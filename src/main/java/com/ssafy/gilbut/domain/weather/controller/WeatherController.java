package com.ssafy.gilbut.domain.weather.controller;

import com.ssafy.gilbut.domain.weather.model.dto.WeatherForecastResponse;
import com.ssafy.gilbut.domain.weather.service.WeatherService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/weather")
@RequiredArgsConstructor
public class WeatherController {
    private final WeatherService weatherService;

    @GetMapping("/forecast")
    public ResponseEntity<List<WeatherForecastResponse>> getAllCitiesWeatherForecast() {
        return ResponseEntity.ok(weatherService.getAllCitiesWeatherForecast());
    }

    @GetMapping("/forecast/{cityName}")
    public ResponseEntity<WeatherForecastResponse> getCityWeatherForecast(@PathVariable String cityName) {
        return ResponseEntity.ok(weatherService.getCityWeatherForecast(cityName));
    }
}