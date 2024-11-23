package com.ssafy.gilbut.domain.weather.service;

import com.ssafy.gilbut.domain.weather.model.dto.CurrentWeatherResponse;
import com.ssafy.gilbut.domain.weather.model.dto.ForecastResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;



@FeignClient(name = "weatherClient", url = "${weather.api.base-url}")
public interface WeatherClient {
    @GetMapping("/weather")
    CurrentWeatherResponse getCurrentWeather(
            @RequestParam("lat") String latitude,
            @RequestParam("lon") String longitude,
            @RequestParam("units") String units,
            @RequestParam("appid") String apiKey
    );

    @GetMapping("/forecast")
    ForecastResponse getForecast(
            @RequestParam("lat") String latitude,
            @RequestParam("lon") String longitude,
            @RequestParam("units") String units,
            @RequestParam("appid") String apiKey
    );
}